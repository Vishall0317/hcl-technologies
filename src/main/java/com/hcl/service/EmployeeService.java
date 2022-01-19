package com.hcl.service;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.model.Employee;

import java.util.List;

public interface EmployeeService {

    boolean addEmployeeDetails(EmployeeRequestDto employeeRequestDto);

    List<Employee> getAllEmployeeDetails();

    Employee getEmployeeDetails(Integer employeeId);

    boolean updateEmployeeDetails(Employee employee, Integer employeeId);

    boolean deleteEmployeeDetails(Integer employeeId);
}
