package com.derick.repository;

import com.derick.domain.PrescriptionQuotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IPrescriptionQuotationRepository extends CrudRepository<PrescriptionQuotation, Serializable> {
}
