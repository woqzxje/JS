package com.abc.ktckjavaspring.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.abc.ktckjavaspring.Entity.Product;

import com.abc.ktckjavaspring.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    //Tạo biến có kiểu dữ liệu repository
    private final ProductRepository productRepository;

    //Lấy sản phẩm theo trang có giới hạn số lượng
    public List<Product> takeProduct(int limit, int page) {
        Pageable pageable = PageRequest.of(page,limit);
        return productRepository.findAll(pageable).getContent();
    }

    public Page<Product> getProductByCategoryAndPrice(String category_url, String price, int limit, int page) {
        Pageable pageable = PageRequest.of(page, limit);
        if (price != null) {
            if ("under500".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceLessThan(category_url, 500000.0, pageable);
            } else if ("500to1000".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceBetween(category_url, 500000.0, 1000000.0, pageable);
            }
        }
        return productRepository.findByCategoryUrl(category_url, pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    
    }

}
