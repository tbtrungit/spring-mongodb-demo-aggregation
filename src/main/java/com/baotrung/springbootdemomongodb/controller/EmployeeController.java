package com.baotrung.springbootdemomongodb.controller;

import com.baotrung.springbootdemomongodb.domain.Employee;
import com.baotrung.springbootdemomongodb.dto.EmployeeDto;
import com.baotrung.springbootdemomongodb.dto.EmployeeResult;
import com.baotrung.springbootdemomongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/findByLocation")
    public ResponseEntity<List<String>> fetchAllLastNameByLocation(@RequestParam String location) {
        return ResponseEntity.ok(employeeRepository.fetchAllLastNameByLocation(location));
    }

    @GetMapping("/findByLocationUseCursor")
    public ResponseEntity<Set<String>> fetchAllLastNameByLocationUsedCursor(@RequestParam String location) {
        return ResponseEntity.ok(employeeRepository.fetchAllLastNameByLocationUsedCursor(location));
    }

    @GetMapping("/calculateTotalUser")
    public ResponseEntity<List<EmployeeResult>> countTotalEmployeeByLocation(@RequestParam String location) {
        return ResponseEntity.ok(employeeRepository.countTotalUserByLocation(location));
    }

    @GetMapping("/calculateSalary")
    public ResponseEntity<List<EmployeeDto>> calculateSalaryByAgeAndLocation(@RequestParam int age, @RequestParam String location) {
        return ResponseEntity.ok(employeeRepository.calculateSalaryByAgeAndLocation(age, location));
    }
}
