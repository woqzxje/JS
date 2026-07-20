package com.abc.ktckjavaspring.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.ktckjavaspring.Entity.Product;
import com.abc.ktckjavaspring.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // /quan-jean
    @GetMapping("/{slug}") // slug === quan-jean
    public String index(
            Model model,
            @PathVariable String slug,
            @org.springframework.web.bind.annotation.RequestParam(name = "page", defaultValue = "1") int page,
            @org.springframework.web.bind.annotation.RequestParam(name = "price", required = false) String price) {

        // limit = 6 (more than 3-4 items)
        // page is 1-based from frontend, but Spring Data uses 0-based
        int pageSize = 6;
        int currentPage = page - 1;
        if (currentPage < 0)
            currentPage = 0;

        org.springframework.data.domain.Page<Product> productPage = productService.getProductByCategoryAndPrice(slug,
                price, pageSize, currentPage);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPrice", price == null ? "all" : price);
        model.addAttribute("slug", slug);

        return "pages/product/shop";
    }

    /// ao-thun/bo-the-thao-puma
    /// ao-thun/2
    /// slug = ao-thun
    /// id = 2
    @GetMapping("/{slug}/{id:[^.]+}") // slug === quan-jean
    // [^.]+ id không chứa dấu chấm
    public String detail(Model model, @PathVariable String slug,
            @PathVariable Long id) {
        //
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "pages/product/single";
    }
}
