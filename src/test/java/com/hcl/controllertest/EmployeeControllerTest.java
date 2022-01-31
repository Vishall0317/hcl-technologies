package com.hcl.controllertest;

import com.hcl.controller.EmployeeController;
import com.hcl.controller.api.EmployeeRequest;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.controller.api.EmployeeResponse;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;
import com.hcl.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        employeeResponseDto=new EmployeeResponseDto();
        employeeResponseDto.setEmployeeId(1);
        employeeResponseDto.setEmployeeName("Raaja");
        employeeResponseDto.setEmployeeEmail("raaja@hcl.com");
        employeeResponseDto.setEmployeePhoneNo("9090909090");
    }

    private List<EmployeeResponseDto> getEmployeeResponseDtos(){
        employeeResponseDtos=new ArrayList<>();
        EmployeeResponseDto employee1=new EmployeeResponseDto();
        employee1.setEmployeeId(1);
        employee1.setEmployeeName("Raaja");
        employee1.setEmployeeEmail("raaja@hcl.com");
        employee1.setEmployeePhoneNo("9090909090");
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
        List<EmployeeResponse> employeeResponse=employeeController.getAllEmployeeDetails();
        //then
        assertNotNull(employeeResponse);
    }

    @Test
    public void testGetAllEmployeesShouldThrowNotFoundExceptionWhenServiceReturnNull() throws NotFoundException{
        //given
        when(employeeService.getAllEmployeeDetails()).thenReturn(null);
        //when
        Exception exception=assertThrows(NotFoundException.class, ()->{
            employeeController.getAllEmployeeDetails();
        });
        //then
        String expectedMessage="No employees are present here!!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetEmployeeDetailsShouldReturnEmployeeDetails() throws NotFoundException{
        //given
        when(employeeService.getEmployeeDetails(1)).thenReturn(employeeResponseDto);
        //when
        EmployeeResponse responseDto=employeeController.getEmployeeDetails(1);
        //then
        assertNotNull(responseDto);
    }

    @Test
    public void testGetEmployeeDetailsShouldThrowNotFoundExceptionWhenServiceReturnNull() throws NotFoundException{
        //given
        when(employeeService.getEmployeeDetails(1)).thenReturn(null);
        //when
        Exception exception=assertThrows(NotFoundException.class, ()->{
            employeeController.getEmployeeDetails(1);
        });
        //then
        String expectedMessage="No employee are present here!!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateEmployeeDetailsShouldReturnUpdatedEmployeeDetails() throws NotFoundException{
        //given
        when(employeeService.updateEmployeeDetails(any(EmployeeRequestDto.class),eq(1))).thenReturn(1);
        //when
        String result=employeeController.updateEmployeeDetails(employeeRequest,1);
        //then
        assertEquals("Employee data updated successfully for id : 1.", result);
    }

    @Test
    public void testUpdateEmployeeDetailsShouldThrowNotFoundExceptionWhenServiceReturnNull() throws NotFoundException{
        //given
        when(employeeService.updateEmployeeDetails(any(EmployeeRequestDto.class),eq(1))).thenReturn(null);
        //when
        Exception exception=assertThrows(NotFoundException.class, ()->{
            employeeController.updateEmployeeDetails(employeeRequest,1);
        });
        //then
        String expectedMessage="Employee data update unsuccessfully!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteEmployeeDetailsShouldDeleteEmployeeDetails() throws NotFoundException{
        //given
        when(employeeService.deleteEmployeeDetails(1)).thenReturn(1);
        //when
        String result=employeeController.deleteEmployeeDetails(1);
        //then
        assertEquals("Employee data deleted successfully for id : 1.", result);
    }

    @Test
    public void testDeleteEmployeeDetailsShouldThrowNotFoundExceptionWhenServiceReturnNull() throws NotFoundException{
        //given
        when(employeeService.deleteEmployeeDetails(1)).thenReturn(null);
        //when
        Exception exception=assertThrows(NotFoundException.class, ()->{
            employeeController.deleteEmployeeDetails(1);
        });
        //then
        String expectedMessage="Employee data delete unsuccessfully!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
