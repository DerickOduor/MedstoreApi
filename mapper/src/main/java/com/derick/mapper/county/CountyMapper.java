package com.derick.mapper.county;

import com.derick.domain.County;
import com.derick.dto.county.CountyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountyMapper {

    @Autowired
    ModelMapper modelMapper;

    public County convertToEntity(CountyDto countyDto) throws ParseException {
        County county = modelMapper.map(countyDto, County.class);
        return county;
    }

    public CountyDto convertToDto(County county) throws Exception{
        CountyDto countyDto = modelMapper.map(county, CountyDto.class);
        return countyDto;
    }

    public List<CountyDto> convertToDto(List<County> counties) throws Exception{
        List<CountyDto> countyDtos = new ArrayList<>();

        try{
            for (County county:counties){
                countyDtos.add(convertToDto(county));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return countyDtos;
    }
}
