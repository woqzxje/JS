package com.abc.ktckjavaspring.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_product_list")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_url")
    private String categoryUrl;
	private String image;
    @Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;
    @Column(nullable = true, precision = 32, scale = 0)
	private BigDecimal stock;
	private String status;
    @Column(name = "created_at")
	private LocalDateTime created_at;
}
