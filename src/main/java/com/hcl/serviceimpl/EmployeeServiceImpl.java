package com.hcl.serviceimpl;

import com.hcl.dto.EmployeeRequestDto;
import com.hcl.exception.NotFoundException;
import com.hcl.model.Employee;
import com.hcl.repository.EmployeeRepository;
import com.hcl.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean addEmployeeDetails(EmployeeRequestDto employeeRequestDto) {
        var employee=new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        var savedEmployee=employeeRepository.save(employee);
        if(ObjectUtils.isEmpty(savedEmployee)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Employee> getAllEmployeeDetails() {

        List<Employee> employeeList=new ArrayList<>();
        var iterator=employeeRepository.findAll().iterator();

        while (iterator.hasNext()){
            var employee=new Employee();
            //iterator.next() is a source and employee is a target.
            BeanUtils.copyProperties(iterator.next(), employee);
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeDetails(Integer employeeId) {

        var employee=new Employee();
        Optional<Employee> optionalEmployee=employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()){
            throw new NotFoundException("Employee doesn't exist for the id "
                    +employeeId+"!! or you enter wrong id please enter proper id");
        }

        BeanUtils.copyProperties(optionalEmployee.get(), employee);
        return employee;
    }

    @Override
    public boolean updateEmployeeDetails(Employee employee, Integer employeeId) {

        var updatedEmployee=employeeRepository.findById(employeeId).orElseThrow(()->
                new NotFoundException("Employee doesn't exist for the id "+employeeId));
        employee.setEmployeeId(employeeId);
        employeeRepository.save(employee);
        if(ObjectUtils.isEmpty(updatedEmployee)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean deleteEmployeeDetails(Integer employeeId) {
        var deletedEmployee=employeeRepository.findById(employeeId).orElseThrow(()->
                new NotFoundException("Employee doesn't exist for the id "+employeeId));

        employeeRepository.findById(employeeId).ifPresent(Employee -> employeeRepository.delete(Employee));

        if(ObjectUtils.isEmpty(deletedEmployee)){
            return false;
        }else {
            return true;
        }
    }
}
