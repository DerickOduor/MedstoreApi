package com.derick.repository;

import com.derick.domain.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IMedicineRepository extends CrudRepository<Medicine, Integer> {
}
