package com.hcl.dto;

import lombok.Data;

@Data
public class EmployeeRequestDto {
    private Integer employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNo;
}
