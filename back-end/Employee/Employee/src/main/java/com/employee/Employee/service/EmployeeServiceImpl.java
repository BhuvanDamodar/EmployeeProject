package com.employee.Employee.service;

import com.employee.Employee.model.EmployeeEntity;
import com.employee.Employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public EmployeeEntity create(EmployeeEntity employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public boolean isEmployeeExistById(Long id) {
        return employeeRepo.existsById(id);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<EmployeeEntity> getEmployeeByName(String employeeName) {
        return employeeRepo.findByEmployeeName(employeeName);
    }

    @Override
    public List<EmployeeEntity> getEmployeeByEmployerName(String employer) {
        return employeeRepo.findByEmployer(employer);
    }
}
