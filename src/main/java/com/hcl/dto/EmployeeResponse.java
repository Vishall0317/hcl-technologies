package com.hcl.dto;

import com.hcl.model.Employee;

import java.util.List;

public class EmployeeResponse {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
