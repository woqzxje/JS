package com.abc.b16.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abc.b16.DTO.AddCartRequest;
import com.abc.b16.DTO.UpdateCartRequest;
import com.abc.b16.Entity.Cart;
import com.abc.b16.Entity.CartDetail;
import com.abc.b16.Entity.Product;
import com.abc.b16.Entity.ProductVariant;
import com.abc.b16.Repository.CartDetailRepository;
import com.abc.b16.Repository.CartRepository;
import com.abc.b16.Repository.ProductRepository;
import com.abc.b16.Repository.ProductVariantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductVariantRepository variantRepository;

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    public void addToCart(AddCartRequest request) {

        // Tìm giỏ hàng
        Cart cart = cartRepository.findByCustomerId(request.getCustomerId())
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setCustomerId(request.getCustomerId());
                    c.setTotalPrice(BigDecimal.ZERO);
                    c.setTotalQuantity(0);
                    c.setCreatedAt(LocalDateTime.now());
                    return cartRepository.save(c);
                });

        // Lấy thông tin sản phẩm
        ProductVariant variant = variantRepository.findById(request.getVariantId())
            .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        
        Product product = productRepository.findById(request.getVariantId().longValue())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        
        // Kiểm tra sản phẩm đã có trong giỏ chưa
        CartDetail detail = cartDetailRepository
                .findByCartIdAndVariantId(cart.getId(), request.getVariantId())
                .orElse(null);

        if (detail == null) {

            detail = new CartDetail();
            detail.setCart(cart);
            detail.setVariantId(request.getVariantId());
            detail.setName(product.getName());
            detail.setImage(product.getImage());
            detail.setQuantity(request.getQuantity());

            // Lưu giá tại thời điểm thêm vào giỏ
            detail.setPrice(variant.getPrice());

        } else {

            detail.setQuantity(detail.getQuantity() + request.getQuantity());

        }

        detail.setTotal(
                detail.getPrice()
                        .multiply(BigDecimal.valueOf(detail.getQuantity()))
        );

        cartDetailRepository.save(detail);

        updateCart(cart);
    }

    /**
     * Cập nhật số lượng
     */
    public void updateQuantity(UpdateCartRequest request) {

        Cart cart = cartRepository.findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        CartDetail detail = cartDetailRepository
                .findByCartIdAndVariantId(cart.getId(), request.getVariantId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        //Cập nhật số lượng sản phẩm trong giỏ hàng
        detail.setQuantity(request.getQuantity());

        //Tính lại thành tiền
        detail.setTotal(
                detail.getPrice()
                        .multiply(BigDecimal.valueOf(request.getQuantity()))
        );

        //Cập nhật bảng chi tiết giỏ hàng
        cartDetailRepository.save(detail);
        //Tính lại tổng tiền và tổng số lượng
        updateCart(cart);
    }

    /**
     * Xóa 1 sản phẩm khỏi giỏ
     */
    public void removeItem(Integer customerId, Integer variantId) {
        //Tìm giỏ hàng theo id khách hàng
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));
        //Tìm chi tiết sản phẩm trong giỏ hàng theo id sản phẩm
        CartDetail detail = cartDetailRepository
                .findByCartIdAndVariantId(cart.getId(), variantId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        //Xóa chi tiết sản phẩm trong giỏ hàng
        cartDetailRepository.delete(detail);
        //Cập nhật lại tổng tiền và tổng số lượng
        updateCart(cart);
    }

    /**
     * Xóa toàn bộ giỏ hàng
     */
    public void clearCart(Integer customerId) {
        //Tìm giỏ hàng theo id khách hàng
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));
        //lấy danh sách chi tiết sản phẩm trong giỏ hàng theo id giỏ hàng
        List<CartDetail> details = cartDetailRepository.findByCartId(cart.getId());
        //Xóa danh sách chi tiết sản phẩm trong giỏ hàng
        cartDetailRepository.deleteAll(details);
        //cập nhật lại tổng tiền và tổng số lượng
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setTotalQuantity(0);
        //Lưu lại giỏ hàng
        cartRepository.save(cart);
    }

    /**
     * Lấy giỏ hàng
     */
    @Transactional
    public Cart getCart(Integer customerId) {
        //Tìm giỏ hàng theo id khách hàng
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));
    }

    /**
     * Tính lại tổng tiền và tổng số lượng
     */
    private void updateCart(Cart cart) {

        List<CartDetail> details = cartDetailRepository.findByCartId(cart.getId());

        int totalQuantity = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartDetail item : details) {

            totalQuantity += item.getQuantity();
            totalPrice = totalPrice.add(item.getTotal());

        }

        cart.setTotalQuantity(totalQuantity);
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);
    }

}
