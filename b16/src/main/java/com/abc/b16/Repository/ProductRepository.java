package com.abc.b16.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.b16.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
   
    Page<Product> findByCategoryUrl(String categoryUrl, Pageable pageable);

    //List<Product> findByCategoryUrl(String category_url);
}
