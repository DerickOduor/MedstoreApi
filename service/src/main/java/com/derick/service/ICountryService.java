package com.derick.service;

import com.derick.domain.Country;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface ICountryService {
    public List<Country> getAll();
}
