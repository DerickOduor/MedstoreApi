package com.derick.repository;

import com.derick.domain.Pharmacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPharmacyRepository extends CrudRepository<Pharmacy, Integer> {
}
