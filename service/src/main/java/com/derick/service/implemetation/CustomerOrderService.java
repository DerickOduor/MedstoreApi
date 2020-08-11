package com.derick.service.implemetation;

import com.derick.domain.*;
import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;
import com.derick.dto.order.OrderItemDto;
import com.derick.dto.order.OrderSlipDto;
import com.derick.mapper.order.CustomerOrderMapper;
import com.derick.mapper.order.DeliveryInformationMapper;
import com.derick.mapper.order.OrderItemMapper;
import com.derick.mapper.order.OrderSlipMapper;
import com.derick.repository.IOrderDeliveryInformationRepository;
import com.derick.service.ICustomerOrderService;
import com.derick.utils.LogFile;
import com.derick.utils.RandomGenerator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    CustomerOrderMapper orderMapper;

    @Autowired
    IOrderDeliveryInformationRepository deliveryInformationRepository;

    @Autowired
    DeliveryInformationMapper deliveryInformationMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RandomGenerator randomGenerator;

    @Autowired
    OrderSlipMapper orderSlipMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public CustomerOrderResponse getCustomersOrder(int CustomerId)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        try{
            User user=entityManager.find(User.class,CustomerId);
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<CustomerOrder> query=builder.createQuery(CustomerOrder.class);
            Root<CustomerOrder> root=query.from(CustomerOrder.class);
            query.select(root).where(builder.equal(root.get("user"),user));
            Query q=entityManager.createQuery(query);

            List<CustomerOrder> orders=new ArrayList<>();
            orders=q.getResultList();

            response.setCustomerOrderDtos(orderMapper.convertToDto(orders));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CustomerOrderResponse getCustomerOrderById(int OrderId)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        CustomerOrderNumber orderNumber=null;
        try{
            CustomerOrder order=entityManager.find(CustomerOrder.class,OrderId);
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<CustomerOrderNumber> query=builder.createQuery(CustomerOrderNumber.class);
            Root<CustomerOrderNumber> root=query.from(CustomerOrderNumber.class);
            query.select(root).where(builder.equal(root.get("Number"),order.getOrderNumber()));
            Query q=entityManager.createQuery(query);

            List<CustomerOrderNumber> orderNumbers=new ArrayList<>();
            orderNumbers=q.getResultList();
            try{
                orderNumber=orderNumbers.get(0);
            }catch (Exception e){
                e.printStackTrace();
            }

            if(orderNumber!=null){
                response.setResponse("success");
                response.setCustomerOrderDto(orderMapper.convertToDto(order));
            }else {
                response.setResponse("Invalid order!");
            }

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CustomerOrderResponse addCustomerOrder(CustomerOrderDto orderDto)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        if(orderDto.getDeliveryInformation()==null){
            response.setResponse("Deliver information not available!");

            return response;
        }
        CustomerOrder order=null;
        String orderNo=randomGenerator.generateOrderNumber();
        try{
            order=entityManager.find(CustomerOrder.class,orderDto.getId());
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        try{
            if (order==null){
                OrderDeliveryInformation deliveryInformation=null;
                if(orderDto.getDeliveryInformation()!=null){
                    System.out.println("\n==DELIVERY INFO not NULL==\n");
                    try{
                        deliveryInformation=deliveryInformationMapper.convertToEntity(orderDto.getDeliveryInformation());
                        //deliveryInformation.setOrder(order);
                        deliveryInformationRepository.save(deliveryInformation);
                        //entityManager.persist(deliveryInformation);
                        //entityManager.getTransaction().commit();
                    }catch (Exception e){
                        e.printStackTrace();
                        logFile.error(e);
                    }
                }else{
                    System.out.println("\n==DELIVERY INFO NULL==\n");
                }
                order=orderMapper.convertToEntity(orderDto);
                order.setOrderNumber(orderNo);
                order.setApproved(false);
                order.setDateOrdered(new Date());
                order.setDeliveryInformation(deliveryInformation);
                try{
                    System.out.println("\n==order==\n"+new Gson().toJson(order)+"\n==end order==\n");
                }catch (Exception e){
                    e.printStackTrace();
                    logFile.error(e);
                }
                entityManager.persist(order);
                CustomerOrderNumber orderNumber=new CustomerOrderNumber();
                orderNumber.setNumber(orderNo);
                entityManager.persist(orderNumber);

                if(!orderDto.getOrderSlips().isEmpty()){
                    try{
                        Set<OrderSlip> orderSlips=new HashSet<>();
                        for(OrderSlipDto orderSlipDto:orderDto.getOrderSlips()){
                            OrderSlip orderSlip=orderSlipMapper.convertToEntity(orderSlipDto);
                            orderSlip.setCustomerOrder(order);
                            orderSlip.setApproved(false);
                            orderSlip.setSlipDate(new Date());
                            Pharmacy pharmacy=entityManager.find(Pharmacy.class,orderSlipDto.getPharmacy().getId());
                            orderSlip.setPharmacy(pharmacy);
                            entityManager.persist(orderSlip);
                            Set<OrderItem> orderItems=new HashSet<>();

                            for(OrderItemDto orderItemDto:orderSlipDto.getOrder_slip_items()){
                                OrderItem orderItem=orderItemMapper.convertToEntity(orderItemDto);
                                orderItem.setOrderSlip(orderSlip);
                                Medicine medicine=entityManager.find(Medicine.class,orderItemDto.getMedicine().getId());
                                orderItem.setMedicine(medicine);
                                entityManager.persist(orderItem);
                                orderItems.add(orderItem);
                            }
                            orderSlip.setOrder_slip_items(orderItems);
                            orderSlips.add(orderSlip);
                        }
                        order.setOrderSlips(orderSlips);
                    }catch (Exception e){
                        e.printStackTrace();
                        logFile.error(e);
                    }
                }

                response.setResponse("success");
                response.setCustomerOrderDto(orderMapper.convertToDto(order));

                return response;
            }else{
                response.setResponse("");
            }
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CustomerOrderResponse deleteCustomerOrder(int Id)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        CustomerOrder order=null;
        try{
            order=entityManager.find(CustomerOrder.class,Id);

            CustomerOrderNumber orderNumber=null;
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<CustomerOrderNumber> query=builder.createQuery(CustomerOrderNumber.class);
            Root<CustomerOrderNumber> root=query.from(CustomerOrderNumber.class);
            query.select(root).where(builder.equal(root.get("Number"),order.getOrderNumber()));
            Query q=entityManager.createQuery(query);

            List<CustomerOrderNumber> orderNumbers=new ArrayList<>();
            orderNumbers=q.getResultList();
            try{
                orderNumber=orderNumbers.get(0);
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
            }

            if(orderNumber!=null){
                entityManager.remove(order);
                response.setResponse("success");
            }else{
                response.setResponse("Invalid order!");
            }

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CustomerOrderResponse approveCustomerOrder(CustomerOrderDto orderDto)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        CustomerOrder order=null;
        try{
            order=entityManager.find(CustomerOrder.class,orderDto.getId());
            if(order!=null){
                if(order.isPaid()){
                    CustomerOrderNumber orderNumber=null;
                    CriteriaBuilder builder=entityManager.getCriteriaBuilder();
                    CriteriaQuery<CustomerOrderNumber> query=builder.createQuery(CustomerOrderNumber.class);
                    Root<CustomerOrderNumber> root=query.from(CustomerOrderNumber.class);
                    query.select(root).where(builder.equal(root.get("Number"),order.getOrderNumber()));
                    Query q=entityManager.createQuery(query);

                    List<CustomerOrderNumber> orderNumbers=new ArrayList<>();
                    orderNumbers=q.getResultList();
                    try{
                        orderNumber=orderNumbers.get(0);
                    }catch (Exception e){
                        e.printStackTrace();
                        logFile.error(e);
                    }
                    if(orderNumber!=null){
                        order.setApproved(true);
                        entityManager.persist(order);
                        response.setCustomerOrderDto(orderMapper.convertToDto(order));
                        response.setResponse("success");
                    }else {
                        response.setResponse("Invalid order!");
                    }
                }else{
                    response.setResponse("Order not paid.");
                }
            }else {
                response.setResponse("failed");
            }
            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public CustomerOrderResponse payCustomerOrder(CustomerOrderDto orderDto)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        CustomerOrder order=null;
        try{
            order=entityManager.find(CustomerOrder.class,orderDto.getId());
            if(order!=null){
                CustomerOrderNumber orderNumber=null;
                CriteriaBuilder builder=entityManager.getCriteriaBuilder();
                CriteriaQuery<CustomerOrderNumber> query=builder.createQuery(CustomerOrderNumber.class);
                Root<CustomerOrderNumber> root=query.from(CustomerOrderNumber.class);
                query.select(root).where(builder.equal(root.get("Number"),order.getOrderNumber()));
                Query q=entityManager.createQuery(query);

                List<CustomerOrderNumber> orderNumbers=new ArrayList<>();
                orderNumbers=q.getResultList();
                try{
                    orderNumber=orderNumbers.get(0);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(orderNumber!=null){
                    order.setPaid(true);
                    entityManager.persist(order);

                    response.setCustomerOrderDto(orderMapper.convertToDto(order));
                    response.setResponse("success");
                }else {
                    response.setResponse("Invalid order!");
                }
            }else {
                response.setResponse("failed");
            }

            return response;

        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }
        return response;
    }
}
