package com.derick.service;

import com.derick.dto.county.CountyDto;
import com.derick.dto.county.CountyResponse;

public interface ICountyService {
    public CountyResponse getCounties();
    public CountyResponse getCounty(int CountyId);
    public CountyResponse getCounty(String CountyName);
    public CountyResponse saveCounty(CountyDto countyDto);
    public CountyResponse updateCounty(CountyDto countyDto);
    public CountyResponse deleteCounty(int CountyId);
}
