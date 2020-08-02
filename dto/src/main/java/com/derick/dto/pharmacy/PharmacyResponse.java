package com.derick.dto.pharmacy;

import java.util.List;

public class PharmacyResponse {
    private PharmacyDto pharmacyDto;
    private List<PharmacyDto> pharmacyDtos;
    private String Response;

    public PharmacyDto getPharmacyDto() {
        return pharmacyDto;
    }

    public void setPharmacyDto(PharmacyDto pharmacyDto) {
        this.pharmacyDto = pharmacyDto;
    }

    public List<PharmacyDto> getPharmacyDtos() {
        return pharmacyDtos;
    }

    public void setPharmacyDtos(List<PharmacyDto> pharmacyDtos) {
        this.pharmacyDtos = pharmacyDtos;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
