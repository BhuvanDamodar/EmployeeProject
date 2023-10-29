package com.employee.Employee.service;

import com.employee.Employee.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeEntity create(EmployeeEntity employee);

    List<EmployeeEntity> getAllEmployees();

    Optional<EmployeeEntity> getEmployeeById(Long id);

    boolean isEmployeeExistById(Long id);

    void deleteEmployeeById(Long id);

    List<EmployeeEntity> getEmployeeByName(String employeeName);

    List<EmployeeEntity> getEmployeeByEmployerName(String employer);
}
