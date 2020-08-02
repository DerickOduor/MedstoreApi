package com.derick.mapper.order;

import com.derick.domain.OrderDeliveryInformation;
import com.derick.dto.order.DeliveryInformationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeliveryInformationMapper {

    @Autowired
    ModelMapper modelMapper;

    public OrderDeliveryInformation convertToEntity(DeliveryInformationDto deliveryInformationDto) throws ParseException {
        OrderDeliveryInformation deliveryInformation = modelMapper.map(deliveryInformationDto, OrderDeliveryInformation.class);
        return deliveryInformation;
    }

    public DeliveryInformationDto convertToDto(OrderDeliveryInformation deliveryInformation) throws Exception{
        DeliveryInformationDto deliveryInformationDto = modelMapper.map(deliveryInformation, DeliveryInformationDto.class);
        return deliveryInformationDto;
    }

    public List<DeliveryInformationDto> convertToDto(List<OrderDeliveryInformation> deliveryInformations) throws Exception{
        List<DeliveryInformationDto> deliveryInformationDtos = new ArrayList<>();

        try{
            for (OrderDeliveryInformation deliveryInformation:deliveryInformations){
                deliveryInformationDtos.add(convertToDto(deliveryInformation));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return deliveryInformationDtos;
    }
}
