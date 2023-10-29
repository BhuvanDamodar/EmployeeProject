package com.employee.Employee.controller;

import com.employee.Employee.exception.ResourceNotFoundException;
import com.employee.Employee.model.EmployeeEntity;
import com.employee.Employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity employee){
        final EmployeeEntity savedEmployee = employeeService.create(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
        List<EmployeeEntity> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployeeById(@PathVariable Long id){
        EmployeeEntity employee = employeeService.getEmployeeById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:" + id));

        employeeService.deleteEmployeeById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted successfully", Boolean.TRUE);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{employeeName}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByName(@PathVariable String employeeName){
        final List<EmployeeEntity> foundEmployees = employeeService.getEmployeeByName(employeeName);
        return new ResponseEntity<>(foundEmployees, HttpStatus.OK);
    }

    @GetMapping("/employer/{employer}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByEmployerName(@PathVariable String employer){
        final List<EmployeeEntity> employerList = employeeService.getEmployeeByEmployerName(employer);
        return new ResponseEntity<>(employerList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> getUpdatedEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employeeDetails){
        EmployeeEntity employee = employeeService.getEmployeeById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" + id));


        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeEmail(employeeDetails.getEmployeeEmail());
        employee.setAge(employeeDetails.getAge());
        employee.setEmployer(employeeDetails.getEmployer());

        EmployeeEntity updatedEmployee = employeeService.create(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
