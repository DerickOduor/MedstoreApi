package com.derick.repository;

import com.derick.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IUserRepository extends CrudRepository<User, Serializable> {
}
