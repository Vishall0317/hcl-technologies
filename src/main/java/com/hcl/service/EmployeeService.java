package com.hcl.service;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    Integer addEmployeeDetails(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployeeDetails();

    EmployeeResponseDto getEmployeeDetails(Integer employeeId);

    Integer updateEmployeeDetails(EmployeeRequestDto employeeRequestDto, Integer employeeId);

    Integer deleteEmployeeDetails(Integer employeeId);
}
