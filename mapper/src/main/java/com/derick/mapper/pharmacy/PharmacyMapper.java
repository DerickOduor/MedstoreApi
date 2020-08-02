package com.derick.mapper.pharmacy;

import com.derick.domain.Pharmacy;
import com.derick.dto.pharmacy.PharmacyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PharmacyMapper {

    @Autowired
    ModelMapper modelMapper;

    public Pharmacy convertToEntity(PharmacyDto pharmacyDto) throws ParseException {
        Pharmacy pharmacy = modelMapper.map(pharmacyDto, Pharmacy.class);
        return pharmacy;
    }

    public PharmacyDto convertToDto(Pharmacy pharmacy) throws Exception{
        PharmacyDto pharmacyDto = modelMapper.map(pharmacy, PharmacyDto.class);
        return pharmacyDto;
    }

    public List<PharmacyDto> convertToDto(List<Pharmacy> pharmacies) throws Exception{
        List<PharmacyDto> pharmacyDtos = new ArrayList<>();

        try{
            for (Pharmacy pharmacy:pharmacies){
                pharmacyDtos.add(convertToDto(pharmacy));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return pharmacyDtos;
    }
}
