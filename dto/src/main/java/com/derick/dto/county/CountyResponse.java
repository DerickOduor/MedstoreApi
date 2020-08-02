package com.derick.dto.county;

import java.util.List;

public class CountyResponse {
    private CountyDto countyDto;
    private List<CountyDto> countyDtos;
    private String Response;

    public CountyDto getCountyDto() {
        return countyDto;
    }

    public void setCountyDto(CountyDto countyDto) {
        this.countyDto = countyDto;
    }

    public List<CountyDto> getCountyDtos() {
        return countyDtos;
    }

    public void setCountyDtos(List<CountyDto> countyDtos) {
        this.countyDtos = countyDtos;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
