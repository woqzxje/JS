package com.abc.b16.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/encode")
    public String encode() {
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        return encoder.encode("123456");
    }
}
