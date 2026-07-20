package com.abc.ktckjavaspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.ktckjavaspring.Entity.Customer;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
