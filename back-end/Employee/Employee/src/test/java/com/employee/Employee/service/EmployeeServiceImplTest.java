package com.employee.Employee.service;

import com.employee.Employee.TestData;
import com.employee.Employee.model.EmployeeEntity;
import com.employee.Employee.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl underTest;

    @Test
    public void testForEmployeeCreated(){
        final EmployeeEntity employee = TestData.testEmployee();

        when(employeeRepo.save(eq(employee))).thenReturn(employee);
        final EmployeeEntity result = underTest.create(employee);

        assertEquals(employee,result);
    }

    @Test
    public void testForFindAllHasNoEmployees(){
        when(employeeRepo.findAll()).thenReturn(new ArrayList<EmployeeEntity>());
        final List<EmployeeEntity> result = underTest.getAllEmployees();
        assertEquals(0, result.size());
    }

    @Test
    public void testForFindAllHasEmployees(){
        final EmployeeEntity employee = TestData.testEmployee();

        when(employeeRepo.findAll()).thenReturn(List.of(employee));
        final List<EmployeeEntity> result = underTest.getAllEmployees();
        assertEquals(1, result.size());
    }

    @Test
    public void testForFindByIdHasEmployee(){
        final EmployeeEntity employee = TestData.testEmployee();

        when(employeeRepo.findById(eq(employee.getEmployeeId()))).thenReturn(Optional.of(employee));
        final Optional<EmployeeEntity> result = underTest.getEmployeeById(employee.getEmployeeId());
        assertEquals(Optional.of(employee), result);
    }

    @Test
    public void testForFindByIdHasNoEmployee(){
        final Long id = 3434L;
        when(employeeRepo.findById(any())).thenReturn(null);
        final Optional<EmployeeEntity> result = underTest.getEmployeeById(id);
        assertEquals(null, result);
    }

}
