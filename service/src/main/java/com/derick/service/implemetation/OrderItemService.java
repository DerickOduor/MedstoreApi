package com.derick.service.implemetation;

import com.derick.domain.OrderItem;
import com.derick.domain.PaymentOption;
import com.derick.dto.order.OrderItemResponse;
import com.derick.mapper.order.OrderItemMapper;
import com.derick.service.IOrderItemService;
import com.derick.utils.LogFile;
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
public class OrderItemService implements IOrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    LogFile logFile;

    @Override
    @Transactional
    public OrderItemResponse getItems(int OrderId) {
        OrderItemResponse response=new OrderItemResponse();
        response.setResponse("failed");
        try{
            CriteriaBuilder builder=entityManager.getCriteriaBuilder();
            CriteriaQuery<OrderItem> query=builder.createQuery(OrderItem.class);
            Root<OrderItem> root=query.from(OrderItem.class);
            query.select(root).where(builder.equal(root.get("order.id"),OrderId));
            Query q=entityManager.createQuery(query);

            List<OrderItem> orderItems=new ArrayList<>();
            orderItems=q.getResultList();

            response.setResponse("success");
            response.setOrderItemDtos(orderItemMapper.convertToDto(orderItems));

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }

    @Override
    @Transactional
    public OrderItemResponse getItem(int id) {
        OrderItemResponse response=new OrderItemResponse();
        response.setResponse("failed");
        try{
            OrderItem item=entityManager.find(OrderItem.class,id);
            response.setResponse("success");
            response.setOrderItemDto(orderItemMapper.convertToDto(item));

            return response;
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return response;
    }
}
