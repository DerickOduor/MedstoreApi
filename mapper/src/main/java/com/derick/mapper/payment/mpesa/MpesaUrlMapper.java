package com.derick.mapper.payment.mpesa;

import com.derick.domain.MpesaUrl;
import com.derick.dto.payment.mpesa.MpesaUrlDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MpesaUrlMapper {

    @Autowired
    ModelMapper modelMapper;

    public MpesaUrl convertToEntity(MpesaUrlDto mpesaUrlDto) throws ParseException {
        MpesaUrl mpesaUrl = modelMapper.map(mpesaUrlDto, MpesaUrl.class);
        return mpesaUrl;
    }

    public MpesaUrlDto convertToDto(MpesaUrl pharmacy) throws Exception{
        MpesaUrlDto mpesaUrlDto = modelMapper.map(pharmacy, MpesaUrlDto.class);
        return mpesaUrlDto;
    }

    public List<MpesaUrlDto> convertToDto(List<MpesaUrl> mpesaUrls) throws Exception{
        List<MpesaUrlDto> mpesaUrlDtos = new ArrayList<>();

        try{
            for (MpesaUrl mpesaUrl:mpesaUrls){
                mpesaUrlDtos.add(convertToDto(mpesaUrl));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return mpesaUrlDtos;
    }
}
