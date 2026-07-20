package com.abc.ktckjavaspring.Entity;

import java.beans.Transient;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "color_id", nullable = false)
    private Integer colorId;

    @Column(name = "size_id", nullable = false)
    private Integer sizeId;

    @Column(length = 100)
    private String sku;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "sale_price", precision = 12, scale = 2)
    private BigDecimal salePrice;

    private Integer stock;

    private String image;

    /**
     * Giá bán thực tế
     */
    @Transient
    public BigDecimal getCurrentPrice() {
        return salePrice != null ? salePrice : price;
    }

    /**
     * Còn hàng hay không
     */
    @Transient
    public boolean isInStock() {
        return stock != null && stock > 0;
    }
}
