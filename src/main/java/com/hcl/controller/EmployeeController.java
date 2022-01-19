package com.hcl.controller;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.model.Employee;
import com.hcl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<String> addEmployeeDetails(@RequestBody EmployeeRequestDto employeeRequestDto){

        boolean response=employeeService.addEmployeeDetails(employeeRequestDto);
        if(response){
            return new ResponseEntity<>("Employee data save successfully!", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Employee data save unsuccessfully!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployeeDetails(){
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeDetails(@PathVariable Integer employeeId){
        return employeeService.getEmployeeDetails(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> updateEmployeeDetails(@PathVariable Integer employeeId, @RequestBody Employee employee){
        boolean response=employeeService.updateEmployeeDetails(employee, employeeId);
        if(response){
            return new ResponseEntity<>("Employee data updated successfully!", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Employee data update unsuccessfully!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployeeDetails(@PathVariable Integer employeeId){
        boolean response=employeeService.deleteEmployeeDetails(employeeId);
        if(response){
            return new ResponseEntity<>("Employee data deleted successfully!", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Employee data delete unsuccessfully!", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
