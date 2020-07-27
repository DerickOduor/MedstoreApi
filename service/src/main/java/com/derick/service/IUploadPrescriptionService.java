package com.derick.service;

import com.derick.domain.UploadPrescription;
import com.derick.dto.prescription.NewPrescriptionDto;
import com.derick.dto.prescription.PrescriptionResponse;

import java.util.List;
import java.util.Optional;

public interface IUploadPrescriptionService {

    PrescriptionResponse saveUploadPrescription(NewPrescriptionDto uploadPrescription) throws Exception;
    PrescriptionResponse getAllUploadPrescription(int CustomerId) throws Exception;
    PrescriptionResponse getUploadPrescription(int UploadPrescriptionId) throws Exception;
    PrescriptionResponse deleteUploadPrescription(int UploadPrescriptionId) throws Exception;

}
