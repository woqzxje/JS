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
            if ("under100".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceLessThan(category_url, 100000.0, pageable);
            } else if ("100to200".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceBetween(category_url, 100000.0, 200000.0, pageable);
            } else if ("200to300".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceBetween(category_url, 200000.0, 300000.0, pageable);
            } else if ("300to400".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceBetween(category_url, 300000.0, 400000.0, pageable);
            } else if ("400to500".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceBetween(category_url, 400000.0, 500000.0, pageable);
            } else if ("above500".equals(price)) {
                return productRepository.findByCategoryUrlAndPriceGreaterThanEqual(category_url, 500000.0, pageable);
            }
        }
        return productRepository.findByCategoryUrl(category_url, pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    
    }

}
