package com.derick.dto.payment.mpesa;

import java.util.List;

public class MpesaResponse {
    private String Response;
    private MpesaParameterDto mpesaParameterDto;
    private MpesaUrlDto mpesaUrlDto;
    private List<MpesaParameterDto> mpesaParameterDtos;
    private List<MpesaUrlDto> mpesaUrlDtos;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public MpesaParameterDto getMpesaParameterDto() {
        return mpesaParameterDto;
    }

    public void setMpesaParameterDto(MpesaParameterDto mpesaParameterDto) {
        this.mpesaParameterDto = mpesaParameterDto;
    }

    public MpesaUrlDto getMpesaUrlDto() {
        return mpesaUrlDto;
    }

    public void setMpesaUrlDto(MpesaUrlDto mpesaUrlDto) {
        this.mpesaUrlDto = mpesaUrlDto;
    }

    public List<MpesaParameterDto> getMpesaParameterDtos() {
        return mpesaParameterDtos;
    }

    public void setMpesaParameterDtos(List<MpesaParameterDto> mpesaParameterDtos) {
        this.mpesaParameterDtos = mpesaParameterDtos;
    }

    public List<MpesaUrlDto> getMpesaUrlDtos() {
        return mpesaUrlDtos;
    }

    public void setMpesaUrlDtos(List<MpesaUrlDto> mpesaUrlDtos) {
        this.mpesaUrlDtos = mpesaUrlDtos;
    }
}
