package com.employee.Employee;

import com.employee.Employee.model.EmployeeEntity;

public class TestData {

    private TestData(){}

    public static EmployeeEntity testEmployee(){
        return EmployeeEntity.builder()
                .employeeId(1)
                .employeeName("John")
                .employer("infosys")
                .employeeEmail("john@infosys.com")
                .age(34)
                .build();
    }
}
