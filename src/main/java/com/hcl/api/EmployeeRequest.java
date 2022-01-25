package com.hcl.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmployeeRequest {

    @NotEmpty(message="Employee name can not be empty!")
    private String employeeName;

    @NotEmpty(message="Employee email id can not be empty!")
    @Email
    private String employeeEmail;

    @NotEmpty(message="Employee phone number can not be empty!")
    @Size(min=10, max=10, message = "Employee phone number must be equal to 10 digits!")
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
