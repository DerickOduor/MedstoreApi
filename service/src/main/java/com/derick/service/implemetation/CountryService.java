package com.derick.service.implemetation;

import com.derick.domain.Country;
import com.derick.repository.CountryRepository;
import com.derick.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class CountryService implements ICountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> getAll(){
        return countryRepository.getAll();
    }
}
