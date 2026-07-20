package com.abc.ktckjavaspring.Service;

import org.springframework.stereotype.Service;

import com.abc.ktckjavaspring.Entity.Employee;
import com.abc.ktckjavaspring.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username).orElse(null);    
    }

}
