package com.derick.mapper.medstore;

import com.derick.domain.Medicine;
import com.derick.dto.medstore.AddMedicineDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class AddMedicineMapper {

    @Autowired
    ModelMapper modelMapper;

    public AddMedicineDto convertToDto(Medicine medicine) throws Exception{
        AddMedicineDto addMedicineDto = modelMapper.map(medicine, AddMedicineDto.class);
        return addMedicineDto;
    }

    public Medicine convertToEntity(AddMedicineDto addMedicineDto) throws ParseException {
        Medicine medicine = modelMapper.map(addMedicineDto, Medicine.class);
        return medicine;
    }
}
