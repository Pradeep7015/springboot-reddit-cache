package com.springboot.reddiscache.controller;

import com.springboot.reddiscache.dao.EmployeeDao;
import com.springboot.reddiscache.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @GetMapping(value = "/employees", produces = APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getEmployees() throws InterruptedException {
        return employeeDao.getAllEmployees();
    }

    @GetMapping(value = "/employees/{employeeId}", produces = APPLICATION_JSON_VALUE)
    public EmployeeDto getEmployee(@PathVariable String employeeId) throws InterruptedException {
        return employeeDao.getEmployee(employeeId).get();
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDao.addEmployee(employeeDto));
    }

    @PutMapping(value = "/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String employeeId,
                                                      @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDao.updateEmployee(employeeId, employeeDto));
    }


}
