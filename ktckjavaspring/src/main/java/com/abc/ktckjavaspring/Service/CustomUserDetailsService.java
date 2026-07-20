package com.abc.ktckjavaspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.ktckjavaspring.Entity.Employee;
import com.abc.ktckjavaspring.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private EmployeeRepository employeeRepository;
    
    //Hàm đăng nhập
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository
                .findByUsername(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Không tìm thấy tài khoản"));
        //java spring sẽ tự động so sánh username và mật khẩu/mật khẩu đã mã hóa
        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPasswordHash()) // mật khẩu đã mã hóa
                .roles(employee.getRole().toString())
                .build();
    }
}
