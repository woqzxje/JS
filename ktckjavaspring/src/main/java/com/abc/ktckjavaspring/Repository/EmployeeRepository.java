package com.abc.ktckjavaspring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.ktckjavaspring.Entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
