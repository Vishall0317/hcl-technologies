package com.hcl.serviceimpl;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.dto.EmployeeResponseDto;
import com.hcl.exception.NotFoundException;
import com.hcl.model.Employee;
import com.hcl.repository.EmployeeRepository;
import com.hcl.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Integer addEmployeeDetails(EmployeeRequestDto employeeRequestDto) {
        var employee=new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        var savedEmployee=employeeRepository.save(employee);
        return employee.getEmployeeId();
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployeeDetails() {

        List<EmployeeResponseDto> employeeList=new ArrayList<>();
        var iterator=employeeRepository.findAll().iterator();

        while (iterator.hasNext()){
            var employeeResponseDto=new EmployeeResponseDto();
            BeanUtils.copyProperties(iterator.next(), employeeResponseDto);
            employeeList.add(employeeResponseDto);
        }
        return employeeList;
    }

    @Override
    public EmployeeResponseDto getEmployeeDetails(Integer employeeId) {

        var employeeResponseDto=new EmployeeResponseDto();
        Optional<Employee> optionalEmployee=employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()){
            throw new NotFoundException("Employee doesn't exist for the id "
                    +employeeId+"!! or you enter wrong id please enter proper id");
        }

        BeanUtils.copyProperties(optionalEmployee.get(), employeeResponseDto);
        return employeeResponseDto;
    }

    @Override
    public Integer updateEmployeeDetails(EmployeeRequestDto employeeRequestDto, Integer employeeId) {

        employeeRepository.findById(employeeId).orElseThrow(()->
                new NotFoundException("Employee doesn't exist for the id "+employeeId));
        employeeRequestDto.setEmployeeId(employeeId);
        var employee=new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employeeRepository.save(employee);
        return employee.getEmployeeId();
    }

    @Override
    public Integer deleteEmployeeDetails(Integer employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(()->
                new NotFoundException("Employee doesn't exist for the id "+employeeId));

        employeeRepository.findById(employeeId).ifPresent(Employee -> employeeRepository.delete(Employee));
        return employeeId;
    }
}
