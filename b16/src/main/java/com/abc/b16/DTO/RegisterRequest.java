package com.abc.b16.DTO;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;

    private String username;

    private String passwordHash;
}
