package com.abc.b16.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddCartRequest {

    private Integer customerId;

    //id sản phẩm
    private Integer variantId;

    //số lượng
    private Integer quantity;

    //giá
    private BigDecimal price;
}