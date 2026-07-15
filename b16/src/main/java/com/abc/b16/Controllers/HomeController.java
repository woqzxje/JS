package com.abc.b16.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abc.b16.Entity.Product;
import com.abc.b16.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/") // Trang chủ
    public String index(Model model) {
        List<Product> products = productService.takeProduct(10, 0);
        model.addAttribute("isHome", true);
        model.addAttribute("products", products);
        return "pages/home/index";
    }

    @GetMapping("/gio-hang")
    public String about() {
        return "pages/home/cart";
    }

}
