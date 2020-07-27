package com.derick.dto.prescription;

import java.util.List;

public class PrescriptionResponse {
    private String Response;

    private PresciptionDto presciption;

    private List<PresciptionDto> presciptions;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public PresciptionDto getPresciption() {
        return presciption;
    }

    public void setPresciption(PresciptionDto presciption) {
        this.presciption = presciption;
    }

    public List<PresciptionDto> getPresciptions() {
        return presciptions;
    }

    public void setPresciptions(List<PresciptionDto> presciptions) {
        this.presciptions = presciptions;
    }
}
