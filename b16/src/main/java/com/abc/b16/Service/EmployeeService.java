package com.abc.b16.Service;

import org.springframework.stereotype.Service;

import com.abc.b16.Entity.Employee;
import com.abc.b16.Repository.EmployeeRepository;

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
