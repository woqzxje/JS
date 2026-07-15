package com.abc.b16.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.b16.DTO.AddCartRequest;
import com.abc.b16.DTO.ApiResponse;
import com.abc.b16.DTO.UpdateCartRequest;
import com.abc.b16.Entity.Cart;
import com.abc.b16.Service.CartService;
import com.abc.b16.Service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart") // Trả về dạng json không có giao diện
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

        private final CartService cartService;
        private final EmployeeService employeeService;

        /**
         * Thêm sản phẩm vào giỏ hàng
         */
        @PostMapping("/add")
        // ResponseEntity là một kiểu dữ liệu dạng json
        // @RequestBody AddCartRequest request gửi dữ liệu dạng json
        public ResponseEntity<ApiResponse<Void>> addToCart(
                        @RequestBody AddCartRequest request, Authentication authentication) {
                // Lấy tên đăng nhập
                String usename = authentication.getName();
                // Lấy id
                Integer id = employeeService.findByUsername(usename).getId();

                request.setCustomerId(id);
                // Thêm dữ liệu vào giỏ hàng
                cartService.addToCart(request);

                return ResponseEntity.ok(new ApiResponse<>(
                                true,
                                "Thêm sản phẩm vào giỏ hàng thành công",
                                null));
        }

        /**
         * Cập nhật số lượng sản phẩm
         */
        @PutMapping("/update")
        public ResponseEntity<ApiResponse<Void>> updateQuantity(
                        @RequestBody UpdateCartRequest request, Authentication authentication) {

                // Lấy tên đăng nhập
                String usename = authentication.getName();
                // Lấy id
                Integer id = employeeService.findByUsername(usename).getId();

                request.setCustomerId(id);

                cartService.updateQuantity(request);

                return ResponseEntity.ok(new ApiResponse<>(
                                true,
                                "Cập nhật giỏ hàng thành công",
                                null));
        }

        /**
         * Xóa một sản phẩm khỏi giỏ hàng
         */
        @DeleteMapping("/remove")
        public ResponseEntity<ApiResponse<Void>> removeItem(
                        @RequestParam Integer variantId,
                        Authentication authentication) {

                String username = authentication.getName();
                Integer customerId = employeeService.findByUsername(username).getId();

                cartService.removeItem(customerId, variantId);

                return ResponseEntity.ok(new ApiResponse<>(
                                true,
                                "Đã xóa sản phẩm khỏi giỏ hàng",
                                null));
        }

        /**
         * Xóa toàn bộ giỏ hàng
         */
        @DeleteMapping("/clear")
        public ResponseEntity<ApiResponse<Void>> clearCart(Authentication authentication) {

                String username = authentication.getName();
                Integer customerId = employeeService.findByUsername(username).getId();

                cartService.clearCart(customerId);

                return ResponseEntity.ok(new ApiResponse<>(
                                true,
                                "Đã xóa toàn bộ giỏ hàng",
                                null));
        }

        /**
         * Lấy thông tin giỏ hàng
         */
        @GetMapping("/index")
        public ResponseEntity<ApiResponse<Cart>> getCart(Authentication authentication) {

                // Lấy tên đăng nhập
                String usename = authentication.getName();
                // Lấy id
                Integer id = employeeService.findByUsername(usename).getId();
                // Lấy giỏ hàng
                Cart cart = cartService.getCart(id);
                // Trả về dạng json
                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Lấy giỏ hàng thành công",
                                                cart));
        }

}
