package com.derick.service.implemetation;

import com.derick.domain.CustomerOrder;
import com.derick.domain.CustomerOrderNumber;
import com.derick.domain.OrderDeliveryInformation;
import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;
import com.derick.mapper.order.CustomerOrderMapper;
import com.derick.mapper.order.DeliveryInformationMapper;
import com.derick.service.ICustomerOrderService;
import com.derick.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    CustomerOrderMapper orderMapper;

    @Autowired
    DeliveryInformationMapper deliveryInformationMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RandomGenerator randomGenerator;

    @Override
    @Transactional
    public CustomerOrderResponse getCustomersOrder(int CustomerId)
    {
        CustomerOrderResponse response=new CustomerOrderResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<CustomerOrder> query=builder.createQuery(CustomerOrder.class);
            Root<CustomerOrder> root=query.from(CustomerOrder.class);
            query.select(root).where(builder.equal(root.get("user.id"),CustomerId));
            Query q=entityManager.createQuery(query);

            List<CustomerOrder> orders=new ArrayList<>();
            orders=q.getResultList();

            response.setCustomerOrderDtos(orderMapper.convertToDto(orders));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
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
        }
        try{
            if (order==null){
                order=orderMapper.convertToEntity(orderDto);
                order.setOrderNumber(orderNo);
                entityManager.persist(order);
                CustomerOrderNumber orderNumber=new CustomerOrderNumber();
                orderNumber.setNumber(orderNo);
                entityManager.persist(orderNumber);

                if(orderDto.getDeliveryInformation()!=null){
                    try{
                        OrderDeliveryInformation deliveryInformation=deliveryInformationMapper.convertToEntity(orderDto.getDeliveryInformation());
                        deliveryInformation.setOrder(order);

                        entityManager.persist(deliveryInformation);
                    }catch (Exception e){
                        e.printStackTrace();
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
                if(order.isApproved()){
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
            e.printStackTrace();
        }
        return response;
    }
}
