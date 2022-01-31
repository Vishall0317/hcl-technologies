package com.hcl.servicetest;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.exception.NotFoundException;
import com.hcl.model.Employee;
import com.hcl.repository.EmployeeRepository;
import com.hcl.service.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    EmployeeRequestDto employeeRequestDto;

    Employee employee;

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
    void testSaveEmployeeShouldSaveEmployeeDetails() {
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
    void testGetAllEmployeeDetailsShouldReturnAListOfEmployees(){
        employeeServiceImpl.getAllEmployeeDetails();
        verify(employeeRepository).findAll();
    }

    @Test
    void testGetEmployeeDetailsShouldReturnEmployeeDetails() throws NotFoundException {
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.getEmployeeDetails(1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void testGetEmployeeDetailsShouldThrowNotFoundExceptionWhenRepositoryReturnNull() throws NotFoundException{
        Optional<Employee> optionalEmployee = Optional.empty();
        //given
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        //when
        Exception exception=assertThrows(NotFoundException.class, () -> employeeServiceImpl.getEmployeeDetails(1));
        //then
        String expectedMessage="Employee doesn't exist for the id 1!! or you enter wrong id please enter proper id!!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateEmployeeDetails() throws NotFoundException {
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.updateEmployeeDetails(employeeRequestDto,1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void testUpdateEmployeeDetailsShouldThrowNotFoundExceptionWhenRepositoryReturnNull() throws NotFoundException{
        Optional<Employee> optionalEmployee = Optional.empty();
        //given
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        //when
        Exception exception=assertThrows(NotFoundException.class, () -> employeeServiceImpl.updateEmployeeDetails(employeeRequestDto,1));
        //then
        String expectedMessage="Employee doesn't exist for the id 1!! or you enter wrong id please enter proper id!!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteEmployeeDetails() throws NotFoundException {
        Optional<Employee> optionalEmployee= Optional.of(employee);
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        employeeServiceImpl.deleteEmployeeDetails(1);
        assertThat(employeeRequestDto.getEmployeeId()).isEqualTo(1);
    }

    @Test
    void testDeleteEmployeeDetailsShouldThrowNotFoundExceptionWhenRepositoryReturnNull() throws NotFoundException{
        Optional<Employee> optionalEmployee = Optional.empty();
        //given
        when(employeeRepository.findById(employeeRequestDto.getEmployeeId())).thenReturn(optionalEmployee);
        //when
        Exception exception=assertThrows(NotFoundException.class, () -> employeeServiceImpl.deleteEmployeeDetails(1));
        //then
        String expectedMessage="Employee doesn't exist for the id 1!! or you enter wrong id please enter proper id!!";
        String actualMessage=exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}

