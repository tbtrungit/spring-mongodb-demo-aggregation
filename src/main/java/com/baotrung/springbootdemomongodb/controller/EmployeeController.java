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

@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee, UriComponentsBuilder uriBuilder) {
        Employee employee1 = employeeRepository.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/employees/{id}").buildAndExpand(employee1.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        if (CollectionUtils.isEmpty(employees)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Employee employee1 = employeeOptional.get();
        employee1.setAge(employee.getAge());
        employee1.setLocation(employee.getLocation());
        employee1.setSalary(employee.getSalary());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        return ResponseEntity.ok(employee1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.delete(employee.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByLocation")
    public ResponseEntity<List<String>> fetchAllLastNameByLocation(@RequestParam String location) {
        return ResponseEntity.ok(employeeRepository.fetchAllLastNameByLocation(location));
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
