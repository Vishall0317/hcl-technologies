package com.hcl.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@SQLDelete(sql = "UPDATE Employee SET deleted = true WHERE employee_id=?")
@Where(clause = "deleted=false")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNo;
    private boolean deleted = Boolean.FALSE;
}
