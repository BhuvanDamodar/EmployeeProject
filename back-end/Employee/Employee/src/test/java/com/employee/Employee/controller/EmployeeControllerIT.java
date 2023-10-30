package com.employee.Employee.controller;

import com.employee.Employee.TestData;
import com.employee.Employee.model.EmployeeEntity;
import com.employee.Employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class EmployeeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testThatEmployeeIsCreated() throws Exception {
        final EmployeeEntity employee = TestData.testEmployee();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String employeeJson =objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/add").contentType(MediaType.APPLICATION_JSON).content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(employee.getEmployeeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(employee.getEmployeeName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeEmail").value(employee.getEmployeeEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employer").value(employee.getEmployer()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(employee.getAge()));
    }

    @Test
    public void testGetAllReturnsAllEmployees() throws Exception{
        final EmployeeEntity employee = TestData.testEmployee();

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeId").value(employee.getEmployeeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeName").value(employee.getEmployeeName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeEmail").value(employee.getEmployeeEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].employer").value(employee.getEmployer()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(employee.getAge()));

    }
}
