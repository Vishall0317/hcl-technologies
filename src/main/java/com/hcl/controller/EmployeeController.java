package com.hcl.controller;

import com.hcl.controller.api.EmployeeRequest;
import com.hcl.dto.EmployeeRequestDto;
import com.hcl.controller.api.EmployeeResponse;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;
import com.hcl.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployeeDetails(@Valid @RequestBody EmployeeRequest employeeRequest) {
        log.info("Received add employee details request");
        var employeeRequestDto = new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        Integer employeeId = employeeService.addEmployeeDetails(employeeRequestDto);
        if (employeeId != null) {
            log.info("responded employeeService.addEmployeeDetails for add operation");
            return "Employee data saved successfully for id : " + employeeId + ".";
        } else {
            log.error("employeeService.addEmployeeDetails response was null");
            return "Employee data save unsuccessfully!";
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getAllEmployeeDetails() throws NotFoundException {
        log.info("Received request to get all employees details");
        List<EmployeeResponseDto> employeeResponseDtoList = employeeService.getAllEmployeeDetails();
        if (employeeResponseDtoList == null) {
            log.error("employeeService.getAllEmployeeDetails response was null");
            throw new NotFoundException("No employees are present here!!");
        }
        List<EmployeeResponse> employeeResponseList = employeeResponseDtoList.stream().map(employeeResponseDto -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            BeanUtils.copyProperties(employeeResponseDto, employeeResponse);
            return employeeResponse;
        }).collect(Collectors.toList());
        log.info("responding with employees for get all operation");
        return employeeResponseList;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeDetails(@PathVariable Integer employeeId) throws NotFoundException {
        log.info("Received request to get employee details");
        EmployeeResponseDto employeeResponseDto=employeeService.getEmployeeDetails(employeeId);
        if (employeeResponseDto == null) {
            log.error("employeeService.getEmployeeDetails response was null");
            throw new NotFoundException("No employee are present here!!");
        }
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employeeResponseDto, employeeResponse);
        log.info("responding with employee for get operation");
        return employeeResponse;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateEmployeeDetails(@RequestBody EmployeeRequest employeeRequest,
                                        @PathVariable Integer employeeId) throws NotFoundException {
        log.info("Received request to update employees details");
        var employeeRequestDto = new EmployeeRequestDto();
        BeanUtils.copyProperties(employeeRequest, employeeRequestDto);
        Integer employeeId1=employeeService.updateEmployeeDetails(employeeRequestDto, employeeId);
        if (employeeId1 != null) {
            log.info("responded employeeService.updateEmployeeDetails for update operation");
            return "Employee data updated successfully for id : " + employeeId + ".";
        } else {
            log.info("employeeService.updateEmployeeDetails response was null");
            throw new NotFoundException("Employee data update unsuccessfully!");
        }
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteEmployeeDetails(@PathVariable Integer employeeId) throws NotFoundException{
        log.info("Received request to delete employees details");
        Integer employeeId1=employeeService.deleteEmployeeDetails(employeeId);
        if (employeeId1!= null) {
            log.info("responded employeeService.deleteEmployeeDetails for delete operation");
            return "Employee data deleted successfully for id : " + employeeId + ".";
        } else {
            log.info("employeeService.deleteEmployeeDetails response was null");
            throw new NotFoundException("Employee data delete unsuccessfully!");
        }
    }
}





