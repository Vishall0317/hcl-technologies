package com.hcl.controllertest;

import com.hcl.controller.EmployeeController;
import com.hcl.api.EmployeeRequest;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponse;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;
import com.hcl.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    EmployeeRequestDto employeeRequestDto;

    EmployeeRequest employeeRequest;

    List<EmployeeResponseDto> employeeResponseDtos;

    @BeforeEach
    public void setUp(){
        employeeRequest=new EmployeeRequest();
        employeeRequest.setEmployeeName("Raaja");
        employeeRequest.setEmployeeEmail("raaja@hcl.com");
        employeeRequest.setEmployeePhoneNo("9090909090");

        employeeResponseDtos=getEmployeeResponseDtos();
    }

    @Test
    @DisplayName("save employee data : positive")
    void saveEmployeeDataTest_Positive(){
        //context
        when(employeeService.addEmployeeDetails(any(EmployeeRequestDto.class))).thenReturn(1);
        //event
        String result=employeeController.addEmployeeDetails(employeeRequest);
        //outcome
        assertEquals("Employee data saved successfully for id : 1.", result);
    }

    @Test
    @DisplayName("save employee data : negative")
    void saveEmployeeDataTest_Negative(){
        //context
       when(employeeService.addEmployeeDetails(any(EmployeeRequestDto.class))).thenReturn(null);
        //event
        String result=employeeController.addEmployeeDetails(employeeRequest);
        //outcome
       assertEquals("Employee data save unsuccessfully!", result);

    }

    @Test
    public void testGetAllEmployeesShouldReturnAListOfEmployees() throws NotFoundException{
        //given
        when(employeeService.getAllEmployeeDetails()).thenReturn(employeeResponseDtos);
        //when
        EmployeeResponse employeeResponse=employeeController.getAllEmployeeDetails();
        //then
        assertNotNull(employeeResponse);
    }

    @Test
    public void testGetEmployeesShouldThrowNotFoundExceptionWhenServiceReturnNull() throws NotFoundException{
        //given
        when(employeeService.getAllEmployeeDetails()).thenReturn(null);

        //when
       // Exception exception=assertThrows(NotFoundException.class)

    }

    private List<EmployeeResponseDto> getEmployeeResponseDtos(){
        employeeResponseDtos=new ArrayList<>();
        EmployeeResponseDto employee1=new EmployeeResponseDto();
        employee1.setEmployeeId(1);
        employee1.setEmployeeName("Raaja");
        employee1.setEmployeeEmail("raaja@hcl.com");;
        employee1.setEmployeePhoneNo("9090909090");
        employeeResponseDtos.add(employee1);
        return employeeResponseDtos;
    }
}
