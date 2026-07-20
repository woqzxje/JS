package com.abc.ktckjavaspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Sử dụng permitAll để Spring Security vẫn quản lý request này
                //tất cả yêu cầu phải giống /dang-ky thì yêu cầu phải đăng nhập
                //authenticated yêu cầu phải đăng nhập mới được truy cập
                //.requestMatchers("/dang-ky").authenticated()
                //anyRequest tất cả yêu cầu - permitAll được truy cập bình thường
                .requestMatchers("/api/cart/*", "/gio-hang").authenticated()
                .anyRequest().permitAll()
            )
            // Đảm bảo không tắt csrf
            .formLogin(form -> form
            .loginPage("/dang-nhap") // Chỉ định trang GET hiển thị form
            .loginProcessingUrl("/dang-nhap") // Bắt Spring Security xử lý POST tại đây
            .defaultSuccessUrl("/", true) // Đăng nhập thành công thì về trang chủ
            .failureUrl("/dang-nhap?error") // Đăng nhập thất bại thì về đây
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/dang-xuat") // Đường dẫn để đăng xuất
            .logoutSuccessUrl("/") // Đăng xuất thành công về trang chủ
            .invalidateHttpSession(true) // Xóa session
            .deleteCookies("JSESSIONID") // Xóa cookie
            .permitAll()
        );
            
        return http.build();
    }
}
