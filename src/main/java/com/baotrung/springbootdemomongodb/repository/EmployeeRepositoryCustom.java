package com.baotrung.springbootdemomongodb.repository;

import com.baotrung.springbootdemomongodb.dto.EmployeeDto;
import com.baotrung.springbootdemomongodb.dto.EmployeeResult;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<String> fetchAllLastNameByLocation(String location);
    List<EmployeeResult> countTotalUserByLocation(String location);
    List<EmployeeDto> calculateSalaryByAgeAndLocation(int age,String location);
}
