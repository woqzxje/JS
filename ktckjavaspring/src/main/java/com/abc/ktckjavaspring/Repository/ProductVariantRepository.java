package com.abc.ktckjavaspring.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.ktckjavaspring.Entity.ProductVariant;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {

    List<ProductVariant> findByProductId(Integer productId);

    Optional<ProductVariant> findByProductIdAndColorIdAndSizeId(
            Integer productId,
            Integer colorId,
            Integer sizeId
    );

    List<ProductVariant> findByStockGreaterThan(Integer stock);

}
