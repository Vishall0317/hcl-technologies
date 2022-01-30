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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    static final String GET_ALL_EMPLOYEES_ERR_MSG = "employeeService.getAllEmployeeDetails response was null";

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    EmployeeRequestDto employeeRequestDto;

    EmployeeRequest employeeRequest;

    EmployeeResponseDto employeeResponseDto;

    List<EmployeeResponseDto> employeeResponseDtos;

    @BeforeEach
    public void setUp(){
        employeeRequest=new EmployeeRequest();
        employeeRequest.setEmployeeName("Raaja");
        employeeRequest.setEmployeeEmail("raaja@hcl.com");
        employeeRequest.setEmployeePhoneNo("9090909090");

        employeeResponseDtos=getEmployeeResponseDtos();

        employeeResponseDto=new EmployeeResponseDto(1,"Raaja","raaja@hcl.com","9090909090");

    }

    private List<EmployeeResponseDto> getEmployeeResponseDtos(){
        employeeResponseDtos=new ArrayList<>();
        EmployeeResponseDto employee1=new EmployeeResponseDto(1,"Raaja","raaja@hcl.com","9090909090");
        employeeResponseDtos.add(employee1);
        return employeeResponseDtos;
    }

    @Test
    void testAddEmployeeDetailsShouldSaveEmployeeDetails(){
        //given
        when(employeeService.addEmployeeDetails(any(EmployeeRequestDto.class))).thenReturn(1);
        //when
        String result=employeeController.addEmployeeDetails(employeeRequest);
        //then
        assertEquals("Employee data saved successfully for id : 1.", result);
    }

    @Test
    void testAddEmployeeDetailsShouldFailedToSaveEmployeeDetails(){
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
        Exception exception=assertThrows(NotFoundException.class, ()->{
            employeeController.getAllEmployeeDetails();
        });
        //then
        String expectedMessage=EmployeeController.GET_ALL_EMPLOYEES_ERR_MSG;
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetEmployeeDetailsShouldReturnEmployeeDetails(){
        //given
        when(employeeService.getEmployeeDetails(1)).thenReturn(employeeResponseDto);
        //when
        EmployeeResponseDto responseDto=employeeController.getEmployeeDetails(1);
        //then
        assertNotNull(responseDto);
    }

    @Test
    public void testUpdateEmployeeDetailsShouldReturnUpdatedEmployeeDetails(){
        //given
        when(employeeService.updateEmployeeDetails(any(EmployeeRequestDto.class),eq(1))).thenReturn(1);
        //when
        String result=employeeController.updateEmployeeDetails(employeeRequest,1);
        //then
        assertEquals("Employee data updated successfully for id : 1.", result);
    }

    @Test
    public void testDeleteEmployeeDetailsShouldDeleteEmployeeDetails(){
        //given
        when(employeeService.deleteEmployeeDetails(1)).thenReturn(1);
        //when
        String result=employeeController.deleteEmployeeDetails(1);
        //then
        assertEquals("Employee data deleted successfully for id : 1.", result);
    }
}
