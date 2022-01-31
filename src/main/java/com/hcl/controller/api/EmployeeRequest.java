package com.hcl.controller.api;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EmployeeRequest {
    @NotEmpty(message="Employee name can not be empty!")
    private String employeeName;

    @NotEmpty(message="Employee email id can not be empty!")
    @Email
    private String employeeEmail;

    @NotEmpty(message="Employee phone number can not be empty!")
    @Size(min=10, max=10, message = "Employee phone number must be equal to 10 digits!")
    private String employeePhoneNo;
}
