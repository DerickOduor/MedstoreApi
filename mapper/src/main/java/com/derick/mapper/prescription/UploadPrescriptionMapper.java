package com.derick.mapper.prescription;

import com.derick.domain.UploadPrescription;
import com.derick.domain.User;
import com.derick.dto.prescription.NewPrescriptionDto;
import com.derick.dto.prescription.PresciptionDto;
import com.derick.dto.user.UserDto;
import com.derick.mapper.user.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadPrescriptionMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserMapper userMapper;

    public PresciptionDto convertToDto(UploadPrescription uploadPrescription) throws Exception{
        PresciptionDto presciptionDto = modelMapper.map(uploadPrescription, PresciptionDto.class);
        return presciptionDto;
    }

    public List<PresciptionDto> convertToDto(List<UploadPrescription> prescriptions) throws Exception{
        List<PresciptionDto> presciptionDtos =new ArrayList<>();
        for(UploadPrescription p:prescriptions){
            try{
                PresciptionDto presciptionDto=convertToDto(p);
               // presciptionDto.setUser(userMapper.convertToDto(p.getUser()));
                presciptionDtos.add(presciptionDto);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return presciptionDtos;
    }

    public UploadPrescription convertToEntity(PresciptionDto presciptionDto) throws ParseException {
        UploadPrescription uploadPrescription = modelMapper.map(presciptionDto, UploadPrescription.class);
        return uploadPrescription;
    }

    public UploadPrescription convertToEntity(NewPrescriptionDto presciptionDto) throws ParseException {
        UploadPrescription uploadPrescription = modelMapper.map(presciptionDto, UploadPrescription.class);
        //uploadPrescription.setUser(userMapper.convertToEntity(presciptionDto.getUser()));
        return uploadPrescription;
    }

}
