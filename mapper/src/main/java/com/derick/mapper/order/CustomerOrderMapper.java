package com.derick.mapper.order;

import com.derick.domain.CustomerOrder;
import com.derick.dto.order.CustomerOrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {

    @Autowired
    ModelMapper modelMapper;

    public CustomerOrder convertToEntity(CustomerOrderDto orderDto) throws ParseException {
        CustomerOrder customerOrder = modelMapper.map(orderDto, CustomerOrder.class);
        return customerOrder;
    }

    public CustomerOrderDto convertToDto(CustomerOrder customerOrder) throws Exception{
        CustomerOrderDto orderDto = modelMapper.map(customerOrder, CustomerOrderDto.class);
        return orderDto;
    }

    public List<CustomerOrderDto> convertToDto(List<CustomerOrder> customerOrders) throws Exception{
        List<CustomerOrderDto> orderDtos = new ArrayList<>();

        try{
            for (CustomerOrder customerOrder:customerOrders){
                orderDtos.add(convertToDto(customerOrder));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return orderDtos;
    }
}
