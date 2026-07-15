package com.abc.b16.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.b16.Entity.Product;
import com.abc.b16.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // /quan-jean
    @GetMapping("/{slug}") // slug === quan-jean
    public String index(Model model, @PathVariable String slug) {
        //
        List<Product> products = productService.getProductByCategory(slug, 10, 0);
        model.addAttribute("products", products);
        return "pages/product/shop";
    }
    
    ///ao-thun/bo-the-thao-puma
    ///ao-thun/2
    /// slug = ao-thun
    /// id = 2
    @GetMapping("/{slug}/{id:[^.]+}") // slug === quan-jean
    //[^.]+ id không chứa dấu chấm
    public String detail(Model model, @PathVariable String slug, 
        @PathVariable Long id) {
        //
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "pages/product/single";
    }
}
