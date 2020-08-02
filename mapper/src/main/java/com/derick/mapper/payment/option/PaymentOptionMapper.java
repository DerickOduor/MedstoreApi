package com.derick.mapper.payment.option;

import com.derick.domain.PaymentOption;
import com.derick.dto.payment.option.PaymentOptionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentOptionMapper {

    @Autowired
    ModelMapper modelMapper;

    public PaymentOption convertToEntity(PaymentOptionDto paymentOptionDto) throws ParseException {
        PaymentOption paymentOption = modelMapper.map(paymentOptionDto, PaymentOption.class);
        return paymentOption;
    }

    public PaymentOptionDto convertToDto(PaymentOption paymentOption) throws Exception{
        PaymentOptionDto paymentOptionDto = modelMapper.map(paymentOption, PaymentOptionDto.class);
        return paymentOptionDto;
    }

    public List<PaymentOptionDto> convertToDto(List<PaymentOption> paymentOptions) throws Exception{
        List<PaymentOptionDto> paymentOptionDtos = new ArrayList<>();

        try{
            for (PaymentOption paymentOption:paymentOptions){
                paymentOptionDtos.add(convertToDto(paymentOption));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return paymentOptionDtos;
    }
}
