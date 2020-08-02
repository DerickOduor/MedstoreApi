package com.derick.service;

import com.derick.dto.medstore.AddMedicineDto;
import com.derick.dto.medstore.AddMedicineResponse;
import com.derick.dto.medstore.ViewMedicineDto;
import com.derick.dto.medstore.ViewMedicineResponse;
import javassist.NotFoundException;

public interface IMedicineService {
    public ViewMedicineResponse getMedicine(int id) throws NotFoundException;
    public ViewMedicineResponse getMedicine() throws NotFoundException;
    public ViewMedicineResponse getPharmacyMedicine(int PharmacyId) throws NotFoundException;
    public ViewMedicineResponse getMedicine(String key) throws NotFoundException;
    public ViewMedicineResponse addMedicine(AddMedicineDto medicineDto) throws NotFoundException;
    public AddMedicineResponse updateMedicine(ViewMedicineDto medicineDto) throws NotFoundException;
}
