package com.hcl.servicetest;

import com.hcl.controller.EmployeeController;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.exception.NotFoundException;
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


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    static final String UPDATE_EMPLOYEES_ERR_MSG = "employeeService.updateEmployeeDetails response was null";

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    EmployeeRequestDto employeeRequestDto;

    Employee employee;

    Employee savedEmployee;

    @BeforeEach
    public void setUp() {
        employee=new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Raaja");
        employee.setEmployeeEmail("raaja@hcl.com");
        employee.setEmployeePhoneNo("9090909090");

        employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setEmployeeId(1);
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
        Integer result = employeeServiceImpl.addEmployeeDetails(employeeRequestDto);
        //outcome
        assertEquals(1, result);
    }

    @Test
    @DisplayName("save employee data : negative")
    void saveEmployeeDataTest_Negative() throws NotFoundException{

        //context
        Optional<Employee> optionalEmployee = Optional.empty();
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        // when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        Exception exception=assertThrows(NotFoundException.class, () -> employeeServiceImpl.addEmployeeDetails(employeeRequestDto));
        String expectedMessage= EmployeeController.UPDATE_EMPLOYEES_ERR_MSG;
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        //event
        //Integer result = employeeServiceImpl.addEmployeeDetails(employeeRequestDto);

        //outcome
        // assertEquals(0, result);
    }

    @Test
    void testGetEmployeeDetails(){
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.getEmployeeDetails(1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void getAllEmployeeDetailsTest(){
        employeeServiceImpl.getAllEmployeeDetails();
        verify(employeeRepository).findAll();
    }

    @Test
    void testUpdateEmployeeDetails(){
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.updateEmployeeDetails(employeeRequestDto,1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void testDeleteteEmployeeDetails(){
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.deleteEmployeeDetails(1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

}

