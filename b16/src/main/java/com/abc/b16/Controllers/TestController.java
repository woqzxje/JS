package com.abc.b16.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final com.abc.b16.Repository.EmployeeRepository employeeRepository;

    public TestController(com.abc.b16.Repository.EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/encode")
    public String encode() {
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        return encoder.encode("123456");
    }

    @GetMapping("/fix-passwords")
    public String fixPasswords() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        java.util.List<com.abc.b16.Entity.Employee> employees = employeeRepository.findAll();
        int count = 0;
        for (com.abc.b16.Entity.Employee emp : employees) {
            // Check if password is not hashed (bcrypt hashes start with $2a$)
            if (emp.getPasswordHash() != null && !emp.getPasswordHash().startsWith("$2a$")) {
                emp.setPasswordHash(encoder.encode(emp.getPasswordHash()));
                employeeRepository.save(emp);
                count++;
            }
        }
        return "Đã cập nhật (mã hóa BCrypt) thành công cho " + count + " tài khoản bị lỗi mật khẩu plain-text!";
    }
}
