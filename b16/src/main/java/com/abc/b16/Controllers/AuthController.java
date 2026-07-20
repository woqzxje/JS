package com.abc.b16.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.b16.DTO.LoginRequest;
import com.abc.b16.DTO.RegisterRequest;
import com.abc.b16.Entity.Employee;
import com.abc.b16.Entity.Role;
import com.abc.b16.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final EmployeeRepository employeeRepository;

    private final BCryptPasswordEncoder encoder;


    @GetMapping("/dang-nhap")//Giao diện đăng nhập
    //sử dụng thư viện security
    public String login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            model.addAttribute("loginRequest", new LoginRequest());
            return "pages/auth/login";
        }
        return "redirect:/";

        //xử lý đăng nhập trong file CustomUserDetailService

    }

    @GetMapping("/dang-ky")//Giao diện đăng ký
    public String register(Model model) {
        //th:object="${RegisterRequest} == RegisterRequest
        model.addAttribute("registerRequest", new RegisterRequest());
        return "pages/auth/register";
    }

    @PostMapping("/dang-ky")
    //sau khi nhấn nút đăng ký
    //dữ liệu đăng ký sẽ lưu vào biến registerRequest
    public String register(Model model,@ModelAttribute("registerRequest") RegisterRequest registerRequest) {
        //isPresent = true  đã tồn tại username
        //isPresent = false chưa tồn tại username
        if (employeeRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            //Thông báo lỗi
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "pages/auth/register";
        }
        else{
            //đăng ký thành công
            Employee employee = new Employee();
            employee.setUsername(registerRequest.getUsername());
            employee.setPasswordHash(encoder.encode(registerRequest.getPasswordHash()));
            employee.setFullName(registerRequest.getFullName());
            employee.setRole(Role.STAFF);
            employeeRepository.save(employee);
            return "redirect:/dang-nhap";//đăng ký thành công thì chuyển về trang đăng nhập
        }
    }

    @GetMapping("/tai-khoan")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String username = authentication.getName();
            employeeRepository.findByUsername(username).ifPresent(employee -> {
                model.addAttribute("user", employee);
            });
            return "pages/account/profile";
        }
        return "redirect:/dang-nhap";
    }
}
