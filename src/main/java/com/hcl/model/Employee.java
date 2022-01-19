package com.hcl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNo;

    public Employee() {
    }

    public Employee(Integer employeeId, String employeeName, String employeeEmail, String employeePhoneNo) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNo = employeePhoneNo;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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
