package com.hcl.service;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    boolean addEmployeeDetails(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployeeDetails();

    EmployeeResponseDto getEmployeeDetails(Integer employeeId);

    boolean updateEmployeeDetails(EmployeeRequestDto employeeRequestDto, Integer employeeId);

    boolean deleteEmployeeDetails(Integer employeeId);
}
