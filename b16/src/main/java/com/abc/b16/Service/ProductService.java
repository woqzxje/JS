package com.abc.b16.Service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.abc.b16.Entity.Product;

import com.abc.b16.Repository.ProductRepository;

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

    public List<Product> getProductByCategory(String category_url, int limit, int page) {
        Pageable pageable = PageRequest.of(page,limit);
        return productRepository.findByCategoryUrl(category_url, pageable).getContent();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    
    }

}