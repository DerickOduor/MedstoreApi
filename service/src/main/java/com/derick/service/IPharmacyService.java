package com.derick.service;


import com.derick.dto.pharmacy.LocationDto;
import com.derick.dto.pharmacy.PharmacyDto;
import com.derick.dto.pharmacy.PharmacyResponse;

public interface IPharmacyService {
    public PharmacyResponse getPharmacies();
    public PharmacyResponse getPharmacy(int PharmacyId);
    public PharmacyResponse getPharmacy(String PharmacyName);
    public PharmacyResponse savePharmacy(PharmacyDto pharmacyDto);
    public PharmacyResponse updatePharmacy(PharmacyDto pharmacyDto);
    public PharmacyResponse updatePharmacyMobuileToken(PharmacyDto pharmacyDto);
    public PharmacyResponse updatePharmacyImage(PharmacyDto pharmacyDto);
    public PharmacyResponse deletePharmacy(int PharmacyId);
    public PharmacyResponse getPharmaciesByLocation(LocationDto locationDto);
}
