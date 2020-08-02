package com.derick.mapper.order;

import com.derick.domain.OrderDeliveryType;
import com.derick.dto.delivery.OrderDeliveryTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeliveryTypeMapper {
    @Autowired
    ModelMapper modelMapper;

    public OrderDeliveryType convertToEntity(OrderDeliveryTypeDto deliveryTypeDto) throws ParseException {
        OrderDeliveryType orderItem = modelMapper.map(deliveryTypeDto, OrderDeliveryType.class);
        return orderItem;
    }

    public OrderDeliveryTypeDto convertToDto(OrderDeliveryType deliveryType) throws Exception{
        OrderDeliveryTypeDto deliveryTypeDto = modelMapper.map(deliveryType, OrderDeliveryTypeDto.class);
        return deliveryTypeDto;
    }

    public List<OrderDeliveryTypeDto> convertToDto(List<OrderDeliveryType> deliveryTypes) throws Exception{
        List<OrderDeliveryTypeDto> deliveryTypeDtos = new ArrayList<>();

        try{
            for (OrderDeliveryType deliveryType:deliveryTypes){
                deliveryTypeDtos.add(convertToDto(deliveryType));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return deliveryTypeDtos;
    }
}
