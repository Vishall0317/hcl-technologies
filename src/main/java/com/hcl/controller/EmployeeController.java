package com.hcl.controller;

import com.hcl.dto.EmployeeRequest;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
//@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployeeDetails(@Valid @RequestBody EmployeeRequest employeeRequest){

        var employeeRequestDto=new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        boolean response=employeeService.addEmployeeDetails(employeeRequestDto);
        if(response) {
            return "Employee data save successfully!";
        }else {
            return "Employee data save unsuccessfully!";
        }
    }

    @GetMapping("")
    public List<EmployeeResponseDto> getAllEmployeeDetails(){
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseDto getEmployeeDetails(@PathVariable Integer employeeId){
        return employeeService.getEmployeeDetails(employeeId);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateEmployeeDetails(@PathVariable Integer employeeId,
                                        @RequestBody EmployeeRequest employeeRequest){
        var employeeRequestDto=new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        boolean response=employeeService.updateEmployeeDetails(employeeRequestDto, employeeId);
        if(response){
            return "Employee data updated successfully!";
        }else{
            return "Employee data update unsuccessfully!";
        }
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteEmployeeDetails(@PathVariable Integer employeeId){
        boolean response=employeeService.deleteEmployeeDetails(employeeId);
        if(response){
            return "Employee data deleted successfully!";
        }else{
            return "Employee data delete unsuccessfully!";
        }
    }
}
