package com.baotrung.springbootdemomongodb.repository;

import com.baotrung.springbootdemomongodb.dto.EmployeeDto;
import com.baotrung.springbootdemomongodb.dto.EmployeeResult;

import java.util.List;
import java.util.Set;

public interface EmployeeRepositoryCustom {

    List<String> fetchAllLastNameByLocation(String location);
    Set<String> fetchAllLastNameByLocationUsedCursor(String location);
    List<EmployeeResult> countTotalUserByLocation(String location);
    List<EmployeeDto> calculateSalaryByAgeAndLocation(int age,String location);
}
