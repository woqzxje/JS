package com.abc.b16.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data //get set
@NoArgsConstructor //Tạo ra hàm khởi tạo không tham số
@AllArgsConstructor //Tạo ra hàm khởi tạo có tham số
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    @JsonBackReference
    private Cart cart;

    private Integer variantId;

    private String name;

    private String image;

    private Integer quantity;
    
    private BigDecimal price;

    private BigDecimal total;
}
