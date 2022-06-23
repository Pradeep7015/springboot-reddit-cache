package com.springboot.reddiscache.dao;

import com.springboot.reddiscache.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.concurrent.TimeUnit.SECONDS;

@Repository
@Slf4j
public class EmployeeDao {
    static int employeeId = 1000;
    private final Map<String, EmployeeDto> empList = new HashMap<>();

    public EmployeeDao() {
        addEmployees();
    }

    private void addEmployees() {
        String id = createAndGetId();
        empList.put(id, EmployeeDto
                .builder().id(id).name("Pradeep Kumar").designation("Software Engineer")
                .build());
        empList.put(id, EmployeeDto
                .builder().id(id).name("Himavanth").designation("Software Engineer")
                .build());
        empList.put(id, EmployeeDto
                .builder().id(id).name("David").designation("Software Engineer")
                .build());
    }

    private String createAndGetId() {
        return "E" + employeeId++;
    }

    @Cacheable("employees")
    public List<EmployeeDto> getAllEmployees() throws InterruptedException {
        log.info("calling service to get employees data...");
        SECONDS.sleep(5);
        return new ArrayList<>(empList.values());
    }

    @CachePut(value = "employees")
    public EmployeeDto addEmployee(EmployeeDto employee) {
        String id = createAndGetId();
        employee.setId(id);
        employee.setName(employee.getName());
        employee.setDesignation(employee.getDesignation());
        return employee;
    }

    @CachePut(value = "employees", key = "#employeeId")
    public EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto) {
        employeeDto.setId(employeeId);
        empList.put(employeeId, employeeDto);
        return employeeDto;
    }

    @Cacheable(value = "employees", key = "#employeeId")
    public Optional<EmployeeDto> getEmployee(String employeeId) {
        log.info("Finding product: " + employeeId);
        sleep(2);
        return Optional.ofNullable(empList.get(employeeId));
    }


    private void sleep(int seconds) {
        try {
            SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
