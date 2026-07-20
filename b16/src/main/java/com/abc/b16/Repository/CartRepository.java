package com.abc.b16.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.b16.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
    //yêu cầu đăng nhập mới sử dụng được
    Optional<Cart> findByCustomerId(Integer customerId);
}
