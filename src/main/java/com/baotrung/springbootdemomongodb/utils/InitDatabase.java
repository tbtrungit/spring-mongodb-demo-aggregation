package com.baotrung.springbootdemomongodb.utils;

import com.baotrung.springbootdemomongodb.domain.Employee;
import com.baotrung.springbootdemomongodb.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDatabase.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initDatabase() {
        LOGGER.info("*****************Begin init database**************");
        Employee employee = new Employee();
        employee.setFirstName("A");
        employee.setLastName("Nguyen Van");
        employee.setAge(25);
        employee.setSalary(22.2);
        employee.setLocation("DN");

        Employee employee1 = new Employee();
        employee1.setFirstName("B");
        employee1.setLastName("Tran Van");
        employee1.setAge(27);
        employee1.setSalary(22.7);
        employee1.setLocation("HN");

        Employee employee2 = new Employee();
        employee2.setFirstName("C");
        employee2.setLastName("Le Van");
        employee2.setAge(28);
        employee2.setSalary(80.2);
        employee2.setLocation("DN");

        Employee employee3 = new Employee();
        employee3.setFirstName("F");
        employee3.setLastName("Tran Van");
        employee3.setAge(30);
        employee3.setSalary(22.8);
        employee3.setLocation("DN");

        Employee[] arr = { employee, employee1, employee2, employee2 };

        List<Employee> employees = Arrays.asList(arr);
        employees.stream().forEach(emp -> employeeRepository.save(emp));
        LOGGER.info("*****************End init database**************");

    }
}
