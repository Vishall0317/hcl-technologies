package com.hcl.servicetest;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.model.Employee;
import com.hcl.repository.EmployeeRepository;
import com.hcl.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    EmployeeRequestDto employeeRequestDto;

    Employee employee;

    Employee savedEmployee;

    @BeforeEach
    public void setUp() {
        employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setEmployeeName("Raaja");
        employeeRequestDto.setEmployeeEmail("raaja@hcl.com");
        employeeRequestDto.setEmployeePhoneNo("9090909090");
    }

    @Test
    @DisplayName("save employee data : positive")
    void saveEmployeeDataTest_Positive() {

        //context
        when(employeeRepository.save(any(Employee.class))).thenAnswer(i -> {
            Employee employee = i.getArgument(0);
            employee.setEmployeeId(1);
            return employee;
        });

        //event
        boolean result = employeeServiceImpl.addEmployeeDetails(employeeRequestDto);

        //outcome
        assertTrue(result);
    }

    @Test
    @DisplayName("save employee data : negative")
    void saveEmployeeDataTest_Negative() {

        //context
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        //event
        boolean result = employeeServiceImpl.addEmployeeDetails(employeeRequestDto);

        //outcome
        assertFalse(result);
    }
}
