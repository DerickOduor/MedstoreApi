package com.derick.service.implemetation;

import com.derick.domain.OrderDeliveryType;
import com.derick.dto.delivery.OrderDeliveryResponse;
import com.derick.dto.delivery.OrderDeliveryTypeDto;
import com.derick.mapper.order.DeliveryTypeMapper;
import com.derick.service.IOrderDeliveryTypeService;
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
public class OrderDeliveryTypeService implements IOrderDeliveryTypeService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DeliveryTypeMapper deliveryTypeMapper;

    @Override
    @Transactional
    public OrderDeliveryResponse getDeliveryTypes() {
        OrderDeliveryResponse response=new OrderDeliveryResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<OrderDeliveryType> query=builder.createQuery(OrderDeliveryType.class);
            Root<OrderDeliveryType> root=query.from(OrderDeliveryType.class);
            query.select(root);
            Query q=entityManager.createQuery(query);
            List<OrderDeliveryType> deliveryTypes=new ArrayList<>();

            deliveryTypes=q.getResultList();

            response.setDeliveryTypeDtos(deliveryTypeMapper.convertToDto(deliveryTypes));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public OrderDeliveryResponse getDeliveryType(int Id) {
        OrderDeliveryResponse response=new OrderDeliveryResponse();
        response.setResponse("failed");
        try{
            OrderDeliveryType deliveryType=entityManager.find(OrderDeliveryType.class,Id);
            response.setDeliveryTypeDto(deliveryTypeMapper.convertToDto(deliveryType));
            response.setResponse("success");

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public OrderDeliveryResponse addDeliveryType(OrderDeliveryTypeDto deliveryTypeDto) {
        OrderDeliveryResponse response=new OrderDeliveryResponse();
        response.setResponse("failed");
        OrderDeliveryType deliveryType=null;
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<OrderDeliveryType> query=builder.createQuery(OrderDeliveryType.class);
            Root<OrderDeliveryType> root=query.from(OrderDeliveryType.class);
            query.select(root).where(builder.equal(root.get("Name"),deliveryTypeDto.getName()));
            Query q=entityManager.createQuery(query);

            List<OrderDeliveryType> deliveryTypes=new ArrayList<>();
            deliveryTypes=q.getResultList();

            if(deliveryTypes.size()==0){
                deliveryType=deliveryTypeMapper.convertToEntity(deliveryTypeDto);
                entityManager.persist(deliveryType);

                response.setResponse("success");
                response.setDeliveryTypeDto(deliveryTypeMapper.convertToDto(deliveryType));
            }else{
                response.setResponse(deliveryTypeDto.getName()+" already exists.");
            }
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    @Transactional
    public OrderDeliveryResponse updateDeliveryType(OrderDeliveryTypeDto deliveryTypeDto) {
        OrderDeliveryResponse response=new OrderDeliveryResponse();
        response.setResponse("failed");
        OrderDeliveryType deliveryType=null;
        try{
            deliveryType=entityManager.find(OrderDeliveryType.class,deliveryTypeDto.getId());
            if(deliveryType!=null){
                deliveryType=deliveryTypeMapper.convertToEntity(deliveryTypeDto);
                entityManager.persist(deliveryType);

                response.setResponse("success");
                response.setDeliveryTypeDto(deliveryTypeMapper.convertToDto(deliveryType));
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
