package com.derick.repository;

import com.derick.domain.County;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountyRepository extends CrudRepository<County,Integer> {
}
