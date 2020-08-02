package com.derick.service;

import com.derick.dto.payment.mpesa.MpesaResponse;
import com.derick.dto.payment.mpesa.MpesaUrlDto;

public interface IMpesaUrlService {
    public MpesaResponse getUrl(String Name);
    public MpesaResponse getUrl(int Id);
    public MpesaResponse addUrl(MpesaUrlDto mpesaUrlDto);
    public MpesaResponse updateUrl(MpesaUrlDto mpesaUrlDto);
}
