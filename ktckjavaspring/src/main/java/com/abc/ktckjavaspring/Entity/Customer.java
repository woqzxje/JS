package com.abc.ktckjavaspring.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String phone;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
