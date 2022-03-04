package com.sprint03.repository;

import com.sprint03.model.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {


}
