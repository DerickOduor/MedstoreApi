package com.derick.mapper.order;

import com.derick.domain.OrderSlip;
import com.derick.dto.order.OrderSlipDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderSlipMapper {

    @Autowired
    ModelMapper modelMapper;

    public OrderSlip convertToEntity(OrderSlipDto orderSlipDto) throws ParseException {
        OrderSlip orderSlip = modelMapper.map(orderSlipDto, OrderSlip.class);
        return orderSlip;
    }

    public OrderSlipDto convertToDto(OrderSlip orderSlip) throws Exception{
        OrderSlipDto orderSlipDto = modelMapper.map(orderSlip, OrderSlipDto.class);
        return orderSlipDto;
    }

    public List<OrderSlipDto> convertToDto(List<OrderSlip> orderSlips) throws Exception {
        List<OrderSlipDto> orderSlipDtos = new ArrayList<>();

        try {
            for (OrderSlip orderSlip : orderSlips) {
                orderSlipDtos.add(convertToDto(orderSlip));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSlipDtos;
    }

    public List<OrderSlip> convertToEntity(List<OrderSlipDto> orderSlips) throws Exception {
        List<OrderSlip> orderSlipDtos = new ArrayList<>();

        try {
            for (OrderSlipDto orderSlip : orderSlips) {
                orderSlipDtos.add(convertToEntity(orderSlip));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSlipDtos;
    }
}
