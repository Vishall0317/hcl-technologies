package com.hcl.controller.api;
;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Integer employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNo;
}
