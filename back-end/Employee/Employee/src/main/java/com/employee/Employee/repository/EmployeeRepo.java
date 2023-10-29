package com.employee.Employee.repository;

import com.employee.Employee.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByEmployeeName(String employeeName);

    List<EmployeeEntity> findByEmployer(String employer);

//    EmployeeEntity findByEmployeeId(Long id);
}
