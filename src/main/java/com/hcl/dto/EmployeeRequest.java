package com.hcl.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeRequest {

    @NotEmpty(message="Employee name can not be empty!")
    private String employeeName;

    @NotEmpty(message="Employee email id can not be empty!")
    private String employeeEmail;

    @NotEmpty(message="Employee phone number can not be empty!")
    private String employeePhoneNo;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhoneNo() {
        return employeePhoneNo;
    }

    public void setEmployeePhoneNo(String employeePhoneNo) {
        this.employeePhoneNo = employeePhoneNo;
    }
}
