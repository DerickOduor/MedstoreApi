package com.derick.repository;

import com.derick.domain.UploadPrescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IUploadPrescriptionRepository extends CrudRepository<UploadPrescription, Serializable> {
}
