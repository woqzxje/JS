package com.abc.b16.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.b16.Entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}