package com.hcl.controllertest;

import com.hcl.controller.EmployeeController;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    EmployeeRequestDto employeeRequestDto;

    @BeforeEach
    public void setUp(){
        employeeRequestDto=new EmployeeRequestDto();
        employeeRequestDto.setEmployeeName("Raaja");
        employeeRequestDto.setEmployeeEmail("raaja@hcl.com");
        employeeRequestDto.setEmployeePhoneNo("9090909090");
    }

    @Test
    @DisplayName("save employee data : positive")
    void saveEmployeeDataTest_Positive(){

        //context
        when(employeeService.addEmployeeDetails(employeeRequestDto)).thenReturn(true);

        //event
        ResponseEntity<String> result=employeeController.addEmployeeDetails(employeeRequestDto);

        //outcome
        assertEquals("Employee data save successfully!", result.getBody());
        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    @DisplayName("save employee data : negative")
    void saveEmployeeDataTest_Negative(){

        //context
        when(employeeService.addEmployeeDetails(employeeRequestDto)).thenReturn(false);

        //event
        ResponseEntity<String> result=employeeController.addEmployeeDetails(employeeRequestDto);

        //outcome
        assertEquals("Employee data save unsuccessfully!", result.getBody());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }
}
