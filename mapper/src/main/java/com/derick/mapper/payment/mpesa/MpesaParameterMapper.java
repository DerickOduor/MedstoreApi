package com.derick.mapper.payment.mpesa;

import com.derick.domain.MpesaParameter;
import com.derick.dto.payment.mpesa.MpesaParameterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MpesaParameterMapper {

    @Autowired
    ModelMapper modelMapper;

    public MpesaParameter convertToEntity(MpesaParameterDto mpesaUrlDto) throws ParseException {
        MpesaParameter mpesaUrl = modelMapper.map(mpesaUrlDto, MpesaParameter.class);
        return mpesaUrl;
    }

    public MpesaParameterDto convertToDto(MpesaParameter pharmacy) throws Exception{
        MpesaParameterDto mpesaUrlDto = modelMapper.map(pharmacy, MpesaParameterDto.class);
        return mpesaUrlDto;
    }

    public List<MpesaParameterDto> convertToDto(List<MpesaParameter> mpesaUrls) throws Exception{
        List<MpesaParameterDto> mpesaUrlDtos = new ArrayList<>();

        try{
            for (MpesaParameter mpesaUrl:mpesaUrls){
                mpesaUrlDtos.add(convertToDto(mpesaUrl));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return mpesaUrlDtos;
    }
}
