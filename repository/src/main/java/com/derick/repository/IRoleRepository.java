package com.derick.repository;

import com.derick.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Serializable> {
}
