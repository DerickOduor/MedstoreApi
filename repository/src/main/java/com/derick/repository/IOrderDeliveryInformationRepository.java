package com.derick.repository;

import com.derick.domain.OrderDeliveryInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDeliveryInformationRepository extends CrudRepository<OrderDeliveryInformation,Integer> {
}
