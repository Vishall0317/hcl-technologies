package com.hcl.service;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    Integer addEmployeeDetails(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployeeDetails();

    EmployeeResponseDto getEmployeeDetails(Integer employeeId);

    void updateEmployeeDetails(EmployeeRequestDto employeeRequestDto, Integer employeeId);

    void deleteEmployeeDetails(Integer employeeId);
}
