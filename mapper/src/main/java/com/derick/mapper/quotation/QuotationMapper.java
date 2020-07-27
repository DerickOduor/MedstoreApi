package com.derick.mapper.quotation;

import com.derick.domain.PrescriptionQuotation;
import com.derick.domain.UploadPrescription;
import com.derick.dto.prescription.PresciptionDto;
import com.derick.dto.quotation.AddQuotationDto;
import com.derick.dto.quotation.QuotationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuotationMapper {

    @Autowired
    ModelMapper modelMapper;

    public PrescriptionQuotation convertToEntity(QuotationDto quotationDto) throws ParseException {
        PrescriptionQuotation quotation = modelMapper.map(quotationDto, PrescriptionQuotation.class);
        return quotation;
    }

    public QuotationDto convertToDto(PrescriptionQuotation prescriptionQuotation) throws Exception{
        QuotationDto quotationDto = modelMapper.map(prescriptionQuotation, QuotationDto.class);
        return quotationDto;
    }

    public List<QuotationDto> convertToDto(List<PrescriptionQuotation> prescriptionQuotations) throws Exception{
        List<QuotationDto> quotationDto = new ArrayList<>();

        try{
            for (PrescriptionQuotation quotation:prescriptionQuotations){
                quotationDto.add(convertToDto(quotation));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return quotationDto;
    }
}
