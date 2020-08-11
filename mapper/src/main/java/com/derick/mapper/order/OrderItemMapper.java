package com.derick.mapper.order;

import com.derick.domain.OrderItem;
import com.derick.dto.order.OrderItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    @Autowired
    ModelMapper modelMapper;

    public OrderItem convertToEntity(OrderItemDto orderItemDto) throws ParseException {
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        return orderItem;
    }

    public OrderItemDto convertToDto(OrderItem orderItem) throws Exception{
        OrderItemDto orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
        return orderItemDto;
    }

    public List<OrderItemDto> convertToDto(List<OrderItem> orderItems) throws Exception{
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        try{
            for (OrderItem orderItem:orderItems){
                orderItemDtos.add(convertToDto(orderItem));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return orderItemDtos;
    }

    public List<OrderItem> convertToEntity(List<OrderItemDto> orderItems) throws Exception{
        List<OrderItem> orderItemDtos = new ArrayList<>();

        try{
            for (OrderItemDto orderItem:orderItems){
                orderItemDtos.add(convertToEntity(orderItem));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return orderItemDtos;
    }
}
