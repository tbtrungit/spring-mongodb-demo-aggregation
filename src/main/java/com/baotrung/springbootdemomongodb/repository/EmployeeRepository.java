package com.baotrung.springbootdemomongodb.repository;

import com.baotrung.springbootdemomongodb.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,String>, EmployeeRepositoryCustom {

}
