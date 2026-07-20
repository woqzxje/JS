// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();



// slick slider
$('.chocolate_container').slick({
    infinite: true,
    center: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    responsive: [{
            breakpoint: 991,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 1
            }
        },
        {
            breakpoint: 576,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
        }

    ]
});

/** google_map js **/

function myMap() {
    var mapProp = {
        center: new google.maps.LatLng(40.712775, -74.005973),
        zoom: 18,
    };
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
}

// Thêm vào giỏ hàng AJAX
$(document).ready(function() {
    // Cấu hình Toastr
    if (typeof toastr !== 'undefined') {
        toastr.options = {
            "closeButton": true,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "timeOut": "3000"
        };
    }

    // Kiểm tra URL parameters để hiển thị thông báo (Đăng nhập/Đăng ký)
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('login_success')) {
        toastr.success('Đăng nhập thành công!');
        window.history.replaceState({}, document.title, window.location.pathname);
    } else if (urlParams.has('logout_success')) {
        toastr.success('Đăng xuất thành công!');
        window.history.replaceState({}, document.title, window.location.pathname);
    } else if (urlParams.has('register_success')) {
        toastr.success('Đăng ký tài khoản thành công! Vui lòng đăng nhập.');
        window.history.replaceState({}, document.title, window.location.pathname);
    }

    $('.btnAddToCart').click(function(e) {
        e.preventDefault();
        
        var btn = $(this);
        var variantId = btn.data('id');
        var price = btn.data('price');
        
        // Quantity: if on single page, read from #quantity, else default to 1
        var quantityInput = $('#quantity');
        var quantity = quantityInput.length > 0 ? parseInt(quantityInput.val()) : 1;
        
        var request = {
            variantId: variantId,
            quantity: quantity,
            price: price
        };
        
        // Fetch CSRF token first
        $.get('/api/csrf', function(csrfData) {
            $.ajax({
                url: '/api/cart/add',
                type: 'POST',
                contentType: 'application/json',
                headers: {
                    [csrfData.headerName]: csrfData.token
                },
                data: JSON.stringify(request),
                success: function(response) {
                    if(response.success === true) {
                        toastr.success('Thêm vào giỏ hàng thành công!');
                    } else if (response.success === false) {
                        toastr.error('Lỗi: ' + response.message);
                    } else {
                        toastr.warning('Vui lòng đăng nhập để sử dụng tính năng này.');
                        setTimeout(function() { window.location.href = '/dang-nhap'; }, 1500);
                    }
                },
                error: function(xhr, status, error) {
                    if (xhr.status === 403 || xhr.status === 401) {
                         toastr.warning("Vui lòng đăng nhập để thêm vào giỏ hàng.");
                         setTimeout(function() { window.location.href = '/dang-nhap'; }, 1500);
                    } else {
                         toastr.error("Đã xảy ra lỗi khi thêm vào giỏ hàng.");
                    }
                }
            });
        }).fail(function() {
            toastr.error("Lỗi cấu hình bảo mật (CSRF).");
        });
    });

    // Hàm định dạng tiền tệ
    function formatPrice(price) {
        if (!price) return '0 VNĐ';
        return price.toLocaleString('vi-VN') + ' VNĐ';
    }

    // Tải giỏ hàng
    function loadCartItems() {
        $.get('/api/csrf', function(csrfData) {
            $.ajax({
                url: '/api/cart/index',
                type: 'GET',
                headers: {
                    [csrfData.headerName]: csrfData.token
                },
                success: function(response) {
                    if (response.success === true && response.data) {
                        var cart = response.data;
                        var tbody = $('#cart-items');
                        tbody.empty();
                        
                        if (!cart.details || cart.details.length === 0) {
                            tbody.append('<tr><td colspan="5" class="text-center py-4">Giỏ hàng của bạn đang trống.</td></tr>');
                            $('#subtotal').text('0 VNĐ');
                            $('#total').text('0 VNĐ');
                            return;
                        }
                        
                        cart.details.forEach(function(item) {
                            var tr = $('<tr></tr>');
                            var imageSrc = (item.image && item.image.trim() !== '') ? item.image : '/images/chocolate1.png';
                            tr.append('<td class="align-middle text-left"><img src="' + imageSrc + '" alt="" style="width: 50px; margin-right: 10px;"> ' + item.name + '</td>');
                            tr.append('<td class="align-middle">' + formatPrice(item.price) + '</td>');
                            
                            var qtyTd = $('<td class="align-middle"></td>');
                            var qtyWrapper = $('<div class="input-group quantity mx-auto" style="width: 130px;"></div>');
                            var minusBtn = $('<div class="input-group-prepend"><button class="btn btn-outline-secondary btn-cart-minus" data-id="' + item.variantId + '"><i class="fa fa-minus"></i></button></div>');
                            var qtyInput = $('<input type="text" class="form-control text-center" value="' + item.quantity + '" readonly>');
                            var plusBtn = $('<div class="input-group-append"><button class="btn btn-outline-secondary btn-cart-plus" data-id="' + item.variantId + '"><i class="fa fa-plus"></i></button></div>');
                            
                            qtyWrapper.append(minusBtn).append(qtyInput).append(plusBtn);
                            qtyTd.append(qtyWrapper);
                            tr.append(qtyTd);
                            
                            tr.append('<td class="align-middle">' + formatPrice(item.total) + '</td>');
                            tr.append('<td class="align-middle"><button class="btn btn-sm btn-danger btn-remove" data-id="' + item.variantId + '"><i class="fa fa-times"></i></button></td>');
                            
                            tbody.append(tr);
                        });
                        
                        $('#subtotal').text(formatPrice(cart.totalPrice));
                        $('#total').text(formatPrice(cart.totalPrice));
                    }
                }
            });
        });
    }

    if ($('#cart-items').length > 0) {
        loadCartItems();
    }

    // Xóa sản phẩm khỏi giỏ hàng
    $(document).on('click', '.btn-remove', function() {
        var variantId = $(this).data('id');
        if(confirm('Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng?')) {
            $.get('/api/csrf', function(csrfData) {
                $.ajax({
                    url: '/api/cart/remove?variantId=' + variantId,
                    type: 'DELETE',
                    headers: {
                        [csrfData.headerName]: csrfData.token
                    },
                    success: function(response) {
                        if(response.success === true) {
                            toastr.success('Đã xóa sản phẩm khỏi giỏ hàng.');
                            loadCartItems(); // Tải lại giỏ hàng
                        } else if (response.success === false) {
                            toastr.error('Lỗi: ' + response.message);
                        } else {
                            toastr.warning('Vui lòng đăng nhập để sử dụng tính năng này.');
                            setTimeout(function() { window.location.href = '/dang-nhap'; }, 1500);
                        }
                    }
                });
            });
        }
    });

    // Cập nhật số lượng
    function updateCartQuantity(variantId, quantity) {
        $.get('/api/csrf', function(csrfData) {
            var request = {
                variantId: variantId,
                quantity: quantity
            };
            $.ajax({
                url: '/api/cart/update',
                type: 'PUT',
                contentType: 'application/json',
                headers: {
                    [csrfData.headerName]: csrfData.token
                },
                data: JSON.stringify(request),
                success: function(response) {
                    if(response.success === true) {
                        loadCartItems();
                    } else if (response.success === false) {
                        toastr.error('Lỗi: ' + response.message);
                    } else {
                        toastr.warning('Vui lòng đăng nhập để sử dụng tính năng này.');
                        setTimeout(function() { window.location.href = '/dang-nhap'; }, 1500);
                    }
                }
            });
        });
    }

    $(document).on('click', '.btn-cart-minus', function() {
        var variantId = $(this).data('id');
        var input = $(this).closest('.quantity').find('input');
        var qty = parseInt(input.val());
        if(qty > 1) {
            updateCartQuantity(variantId, qty - 1);
        } else {
            // Nếu bằng 1 thì hỏi xem có muốn xóa không
            if(confirm('Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng?')) {
                $(this).closest('tr').find('.btn-remove').click();
            }
        }
    });

    $(document).on('click', '.btn-cart-plus', function() {
        var variantId = $(this).data('id');
        var input = $(this).closest('.quantity').find('input');
        var qty = parseInt(input.val());
        updateCartQuantity(variantId, qty + 1);
    });
});