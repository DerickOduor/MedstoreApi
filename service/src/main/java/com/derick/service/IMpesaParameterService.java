package com.derick.service;

import com.derick.dto.payment.mpesa.MpesaParameterDto;
import com.derick.dto.payment.mpesa.MpesaResponse;

public interface IMpesaParameterService {
    public MpesaResponse getParameter(String Name);
    public MpesaResponse getParameter(int Id);
    public MpesaResponse addParameter(MpesaParameterDto mpesaParameterDto);
    public MpesaResponse updateParameter(MpesaParameterDto mpesaParameterDto);
}
