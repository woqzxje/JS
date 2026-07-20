package com.abc.b16.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateCartRequest {
    
    private Integer customerId;

    private Integer variantId;

    private Integer quantity;

    private BigDecimal price;
}
