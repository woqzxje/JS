package com.abc.b16.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.b16.Entity.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long>{

    //lấy chi tiết giỏ hàng theo id giỏ hàng và id sản phẩm
    Optional<CartDetail> findByCartIdAndVariantId(Long cartId, Integer variantId);

    //lấy chi tiết giỏ hàng theo id giỏ hàng
    List<CartDetail> findByCartId(Long cartId);

}
