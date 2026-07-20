package com.abc.b16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.b16.Entity.Customer;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
