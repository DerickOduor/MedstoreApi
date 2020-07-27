package com.derick.mapper.medstore;

import com.derick.domain.Medicine;
import com.derick.dto.medstore.ViewMedicineDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewMedicineMapper {

    @Autowired
    ModelMapper modelMapper;

    public ViewMedicineDto convertToDto(Medicine medicine) throws Exception{
        ViewMedicineDto viewMedicineDto = modelMapper.map(medicine, ViewMedicineDto.class);
        return viewMedicineDto;
    }

    public List<ViewMedicineDto> convertToDto(List<Medicine> medicine) throws Exception{
        List<ViewMedicineDto> viewMedicineDto=new ArrayList<>();
        for(Medicine m:medicine){
            viewMedicineDto.add(convertToDto(m));
        }
        //modelMapper.map(medicine, viewMedicineDto);
        return viewMedicineDto;
    }

    public Medicine convertToEntity(ViewMedicineDto viewMedicineDto) throws ParseException {
        Medicine medicine = modelMapper.map(viewMedicineDto, Medicine.class);
        return medicine;
    }

}
