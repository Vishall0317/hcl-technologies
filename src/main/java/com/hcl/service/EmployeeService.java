package com.hcl.service;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;

import java.util.List;

public interface EmployeeService {

    Integer addEmployeeDetails(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployeeDetails();

    EmployeeResponseDto getEmployeeDetails(Integer employeeId) throws NotFoundException;

    Integer updateEmployeeDetails(EmployeeRequestDto employeeRequestDto, Integer employeeId) throws NotFoundException;

    Integer deleteEmployeeDetails(Integer employeeId) throws NotFoundException;
}
