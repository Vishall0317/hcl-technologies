package com.hcl.controller;

import com.hcl.api.EmployeeRequest;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponse;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;
import com.hcl.model.Employee;
import com.hcl.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Validated
@RequestMapping("/employees")
public class EmployeeController {

    public static final String GET_ALL_EMPLOYEES_ERR_MSG = "employeeService.getAllEmployeeDetails response was null";
    public static final String UPDATE_EMPLOYEES_ERR_MSG = "Employee doesn't exist for the id 1";

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployeeDetails(@Valid @RequestBody EmployeeRequest employeeRequest) {
        log.info("Received add employee request");
        var employeeRequestDto = new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        Integer employeeId = employeeService.addEmployeeDetails(employeeRequestDto);
        if (employeeId != null) {
            log.info("Employee data saved successfully for id : " + employeeId + ".");
            return "Employee data saved successfully for id : " + employeeId + ".";
        } else {
            return "Employee data save unsuccessfully!";
        }
    }

    @GetMapping("/getAll")
    public EmployeeResponse getAllEmployeeDetails() throws NotFoundException {
        log.info("Received request to get all users");
        List<EmployeeResponseDto> employeeResponseDtos = employeeService.getAllEmployeeDetails();
        if (employeeResponseDtos == null) {
            log.error(GET_ALL_EMPLOYEES_ERR_MSG);
            throw new NotFoundException(GET_ALL_EMPLOYEES_ERR_MSG);
        }
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployees(employeeResponseDtos.stream().map(employeeResponseDto -> {
            Employee employee = new Employee();
            mapEmployeeResponseDto(employeeResponseDto, employee);
            log.debug("copied data: " + employeeResponseDto);
            return employee;
        }).collect(Collectors.toList()));
        log.info("responding with employees for get all operation");
        return employeeResponse;
    }

    private void mapEmployeeResponseDto(EmployeeResponseDto employeeResponseDto, Employee employee) {

        employee.setEmployeeId(employeeResponseDto.getEmployeeId());
        employee.setEmployeeName(employeeResponseDto.getEmployeeName());
        employee.setEmployeeEmail(employeeResponseDto.getEmployeeEmail());
        employee.setEmployeePhoneNo(employeeResponseDto.getEmployeePhoneNo());
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDto getEmployeeDetails(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeDetails(employeeId);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateEmployeeDetails(@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer employeeId) {
        var employeeRequestDto = new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        Integer employeeId1=employeeService.updateEmployeeDetails(employeeRequestDto, employeeId);
        if (employeeId1 != null) {
            log.info("Employee data updated successfully for id : " + employeeId + ".");
            return "Employee data updated successfully for id : " + employeeId + ".";
        } else {
            return "Employee data update unsuccessfully!";
        }
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteEmployeeDetails(@PathVariable Integer employeeId) {
        Integer employeeId1=employeeService.deleteEmployeeDetails(employeeId);
        if (employeeId1!= null) {
            log.info("Employee data deleted successfully for id : " + employeeId + ".");
            return "Employee data deleted successfully for id : " + employeeId + ".";
        } else {
            return "Employee data delete unsuccessfully!";
        }
    }
}





