-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2026 at 12:29 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ktckjavaspring`
--

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`id`, `name`, `logo`, `created_at`) VALUES
(1, 'Lindt & Sprüngli', 'lindt.png', '2026-06-15 01:21:26'),
(2, 'Godiva', 'godiva.png', '2026-06-15 01:21:26'),
(3, 'Guylian', 'guylian.png', '2026-06-15 01:21:26'),
(4, 'Valrhona', 'valrhona.png', '2026-06-15 01:21:26'),
(5, 'Royce''', 'royce.png', '2026-06-15 01:21:26'),
(6, 'Ferrero Rocher', 'ferrero.png', '2026-06-15 01:21:26'),
(7, 'Toblerone', 'toblerone.png', '2026-06-15 01:21:26'),
(8, 'Cadbury', 'cadbury.png', '2026-06-15 01:21:26'),
(9, 'Hershey''s', 'hersheys.png', '2026-06-15 01:21:26'),
(10, 'M&M''s', 'mms.png', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `id` bigint(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `total_quantity` int(11) NOT NULL DEFAULT 1,
  `total_price` decimal(38,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `carts`
--


-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `id` bigint(20) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  `variant_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_items`
--


-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sort` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `url`, `sort`, `description`, `created_at`) VALUES
(1, 'Socola Lindor', 'socola-den', '0', 'Socola đen nguyên chất', '2026-06-15 01:21:26'),
(2, 'Socola Godiva', 'socola-sua', '0', 'Socola sữa ngọt ngào', '2026-06-15 01:21:26'),
(3, 'Socola Ghylian', 'socola-trang', '0', 'Socola trắng béo ngậy', '2026-06-15 01:21:26'),
(4, 'Socola Valrhona', 'socola-hat', '0', 'Socola nhân các loại hạt', '2026-06-15 01:21:26'),
(5, 'Socola Royce', 'socola-tuoi', '0', 'Nama chocolate Nhật Bản', '2026-06-15 01:21:26'),
(6, 'Kẹo Socola', 'keo-socola', '0', 'Các loại kẹo socola', '2026-06-15 01:21:26'),
(11, 'Trang chủ', '', '1', 'Trang chủ', '2026-06-15 01:46:14');

-- --------------------------------------------------------

--
-- Table structure for table `colors`
--

CREATE TABLE `colors` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `color_code` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `colors`
--

INSERT INTO `colors` (`id`, `name`, `color_code`) VALUES
(1, 'Đen', '#000000'),
(2, 'Trắng', '#FFFFFF'),
(3, 'Đỏ', '#FF0000'),
(4, 'Xanh dương', '#0000FF'),
(5, 'Xanh lá', '#00FF00'),
(6, 'Vàng', '#FFFF00'),
(7, 'Xám', '#808080'),
(8, 'Hồng', '#FFC0CB'),
(9, 'Nâu', '#8B4513'),
(10, 'Cam', '#FFA500');

-- --------------------------------------------------------

--
-- Table structure for table `coupons`
--

CREATE TABLE `coupons` (
  `id` int(11) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  `max_discount` decimal(12,2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `coupons`
--

INSERT INTO `coupons` (`id`, `code`, `discount_percent`, `max_discount`, `start_date`, `end_date`, `quantity`) VALUES
(1, 'SALE10', 10.00, 100000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(2, 'SALE15', 15.00, 150000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(3, 'SALE20', 20.00, 200000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(4, 'VIP10', 10.00, 100000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 50),
(5, 'VIP15', 15.00, 150000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 50),
(6, 'NEWUSER', 5.00, 50000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 200),
(7, 'FREESHIP', 3.00, 30000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 150),
(8, 'SUMMER', 12.00, 120000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(9, 'WINTER', 18.00, 180000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 100),
(10, 'SPECIAL', 25.00, 250000.00, '2026-01-01 00:00:00', '2026-12-31 00:00:00', 30);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `full_name`, `phone`, `email`, `password_hash`, `address`, `created_at`) VALUES
(1, 'Nguyễn Văn A', '0900000001', 'a@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(2, 'Trần Văn B', '0900000002', 'b@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(3, 'Lê Văn C', '0900000003', 'c@gmail.com', '123456', 'Đà Nẵng', '2026-06-15 01:21:26'),
(4, 'Phạm Văn D', '0900000004', 'd@gmail.com', '123456', 'Hà Nội', '2026-06-15 01:21:26'),
(5, 'Hoàng Văn E', '0900000005', 'e@gmail.com', '123456', 'Cần Thơ', '2026-06-15 01:21:26'),
(6, 'Nguyễn Thị F', '0900000006', 'f@gmail.com', '123456', 'HCM', '2026-06-15 01:21:26'),
(7, 'Trần Thị G', '0900000007', 'g@gmail.com', '123456', 'Đồng Nai', '2026-06-15 01:21:26'),
(8, 'Lê Thị H', '0900000008', 'h@gmail.com', '123456', 'Bình Dương', '2026-06-15 01:21:26'),
(9, 'Phạm Thị I', '0900000009', 'i@gmail.com', '123456', 'Long An', '2026-06-15 01:21:26'),
(10, 'Hoàng Thị K', '0900000010', 'k@gmail.com', '123456', 'Vũng Tàu', '2026-06-15 01:21:26');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','STAFF') DEFAULT 'STAFF',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `full_name`, `username`, `password_hash`, `role`, `created_at`) VALUES
(1, 'Admin System', 'admin', '123456', 'ADMIN', '2026-06-15 01:21:26'),
(2, 'Manager 1', 'manager1', '123456', 'MANAGER', '2026-06-15 01:21:26'),
(3, 'Staff 1', 'staff1', '123456', 'STAFF', '2026-06-15 01:21:26'),
(4, 'Staff 2', 'staff2', '123456', 'STAFF', '2026-06-15 01:21:26'),
(5, 'Staff 3', 'staff3', '123456', 'STAFF', '2026-06-15 01:21:26'),
(6, 'Staff 4', 'staff4', '123456', 'STAFF', '2026-06-15 01:21:26'),
(7, 'Staff 5', 'staff5', '123456', 'STAFF', '2026-06-15 01:21:26'),
(8, 'Staff 6', 'staff6', '123456', 'STAFF', '2026-06-15 01:21:26'),
(9, 'Staff 7', 'staff7', '123456', 'STAFF', '2026-06-15 01:21:26'),
(10, 'Staff 8', 'staff8', '123456', 'STAFF', '2026-06-15 01:21:26'),
(11, 'staff9', 'staff9', '$2a$10$CG67uSLLdOaNq1o1nL3AK.lP2qfg6DI6exBVPy7q.0iIT/xGdpjbS', 'STAFF', '2026-07-02 07:58:42');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT current_timestamp(),
  `total_amount` decimal(12,2) DEFAULT NULL,
  `discount_amount` decimal(12,2) DEFAULT NULL,
  `final_amount` decimal(12,2) DEFAULT NULL,
  `status` enum('PENDING','CONFIRMED','SHIPPING','COMPLETED','CANCELLED') DEFAULT 'PENDING',
  `shipping_address` text DEFAULT NULL,
  `note` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--


-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `variant_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `payment_method` enum('COD','BANKING','MOMO','ZALOPAY','VNPAY') DEFAULT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `status` enum('PENDING','PAID','FAILED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--


-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `material` varchar(100) DEFAULT NULL,
  `gender` enum('Nam','Nữ','Unisex') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `category_id`, `brand_id`, `name`, `description`, `material`, `gender`, `status`, `created_at`) VALUES
(1, 1, 1, 'Viên socola Lindor nhân tan chảy', 'Thương hiệu Thụy Sĩ nổi tiếng với dòng sản phẩm viên bọc tròn Lindor có nhân tan chảy mịn màng tuyệt hảo.', 'Cacao', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(2, 2, 2, 'Hộp vàng socola hoàng gia Godiva', 'Biểu tượng chocolate hoàng gia của Bỉ với thiết kế bao gói hộp vàng sang trọng và các viên chocolate bọc nhân ganache, praline tinh tế.', 'Cacao, Ganache', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(3, 3, 3, 'Socola tạo hình vỏ sò nhân hạt dẻ rang', 'Thương hiệu Bỉ lừng danh thế giới với dòng sản phẩm chocolate tạo hình vỏ sò độc đáo hòa quyện cùng nhân hạt dẻ rang.', 'Hạt dẻ, Cacao', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(4, 4, 4, 'Socola Valrhona chuyên dụng làm bánh', 'Thương hiệu Pháp đỉnh cao chuyên cung cấp chocolate cao cấp cho các đầu bếp bánh chuyên nghiệp.', 'Cacao', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(5, 5, 5, 'Nama Chocolate', 'Đại diện lừng danh đến từ Nhật Bản với dòng chocolate tươi (Nama Chocolate) mềm mịn, béo ngậy.', 'Kem tươi, Cacao', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(6, 6, 6, 'Viên socola bọc giấy vàng Ferrero Rocher', 'Thương hiệu Ý kinh điển với viên chocolate bọc giấy vàng gồm nhiều lớp bánh xốp, kem chocolate và hạt dẻ giòn rụm.', 'Hạt phỉ, Cacao', 'Unisex', 'ACTIVE', '2026-06-15 01:21:26'),
(11, 1, 1, 'Socola Đen Nguyên Chất 85%', 'Hương vị đắng nhẹ, thơm nồng từ hạt cacao nguyên chất 85%.', 'Cacao 85%', 'Unisex', 'ACTIVE', '2026-07-18 10:00:00'),
(12, 2, 2, 'Socola Sữa Hạt Điều', 'Sự kết hợp hoàn hảo giữa socola sữa ngọt ngào và hạt điều bùi béo.', 'Cacao, Sữa, Hạt Điều', 'Unisex', 'ACTIVE', '2026-07-18 10:00:00'),
(13, 3, 3, 'Socola Trắng Matcha', 'Vị ngọt thanh của socola trắng hòa quyện cùng bột trà xanh matcha Nhật Bản.', 'Bơ cacao, Sữa, Matcha', 'Unisex', 'ACTIVE', '2026-07-18 10:00:00'),
(14, 4, 4, 'Socola Hạnh Nhân Phủ Cacao', 'Hạt hạnh nhân Mỹ giòn rụm bọc trong lớp socola và bột cacao đắng nhẹ.', 'Cacao, Hạnh nhân', 'Unisex', 'ACTIVE', '2026-07-18 10:00:00'),
(15, 5, 5, 'Nama Chocolate Vị Dâu Tây', 'Socola tươi mịn màng quyện với vị chua ngọt tự nhiên của dâu tây.', 'Kem tươi, Bơ cacao, Dâu tây', 'Unisex', 'ACTIVE', '2026-07-18 10:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `product_images`
--

CREATE TABLE `product_images` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_images`
--

INSERT INTO `product_images` (`id`, `product_id`, `image_url`) VALUES
(1, 1, '/images/lindt.png'),
(2, 2, '/images/godiva.png'),
(3, 3, '/images/guylian.png'),
(4, 4, '/images/valrhona.png'),
(5, 5, '/images/royce.png'),
(6, 6, '/images/ferrero.png'),
(11, 11, '/images/socola-den-85.png'),
(12, 12, '/images/socola-sua-hat-dieu.png'),
(13, 13, '/images/socola-trang-matcha.png'),
(14, 14, '/images/socola-hanh-nhan.png'),
(15, 15, '/images/nama-dau-tay.png');

-- --------------------------------------------------------

--
-- Table structure for table `product_variants`
--

CREATE TABLE `product_variants` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `color_id` int(11) NOT NULL,
  `size_id` int(11) NOT NULL,
  `sku` varchar(100) DEFAULT NULL,
  `price` decimal(12,2) NOT NULL,
  `sale_price` decimal(12,2) DEFAULT NULL,
  `stock` int(11) DEFAULT 0,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_variants`
--

INSERT INTO `product_variants` (`id`, `product_id`, `color_id`, `size_id`, `sku`, `price`, `sale_price`, `stock`, `image`) VALUES
(1, 1, 1, 1, 'LINDT-01', 120000.00, 100000.00, 100, '/images/lindt.png'),
(2, 2, 2, 2, 'GODIVA-01', 220000.00, 200000.00, 80, '/images/godiva.png'),
(3, 3, 3, 2, 'GUYLIAN-01', 85000.00, 75000.00, 50, '/images/guylian.png'),
(4, 4, 4, 7, 'VALRHONA-01', 250000.00, 220000.00, 60, '/images/valrhona.png'),
(5, 5, 5, 2, 'ROYCE-01', 150000.00, 130000.00, 40, '/images/royce.png'),
(6, 6, 6, 8, 'FERRERO-01', 500000.00, 450000.00, 30, '/images/ferrero.png'),
(11, 11, 1, 2, 'DEN-85', 180000.00, 160000.00, 50, '/images/socola-den-85.png'),
(12, 12, 9, 2, 'SUA-DIEU', 150000.00, 140000.00, 60, '/images/socola-sua-hat-dieu.png'),
(13, 13, 2, 2, 'TRANG-MAT', 200000.00, 190000.00, 40, '/images/socola-trang-matcha.png'),
(14, 14, 1, 3, 'HAT-HANH', 250000.00, 220000.00, 80, '/images/socola-hanh-nhan.png'),
(15, 15, 8, 2, 'NAMA-DAU', 190000.00, 175000.00, 30, '/images/nama-dau-tay.png');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `rating` int(11) DEFAULT NULL CHECK (`rating` between 1 and 5),
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--


-- --------------------------------------------------------

--
-- Table structure for table `sizes`
--

CREATE TABLE `sizes` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sizes`
--

INSERT INTO `sizes` (`id`, `name`) VALUES
(1, '50g'),
(2, '100g'),
(3, '150g'),
(4, '200g'),
(5, '250g'),
(6, '500g'),
(7, 'Hộp 6 viên'),
(8, 'Hộp 12 viên'),
(9, 'Hộp 24 viên'),
(10, 'Hộp lớn');

-- --------------------------------------------------------

--
-- Stand-in structure for view `vw_product_list`
-- (See below for the actual view)
--
CREATE TABLE `vw_product_list` (
`id` int(11)
,`name` varchar(255)
,`category_id` int(11)
,`category_url` varchar(255)
,`image` varchar(255)
,`price` decimal(12,2)
,`stock` decimal(32,0)
,`status` enum('ACTIVE','INACTIVE')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Structure for view `vw_product_list`
--
DROP TABLE IF EXISTS `vw_product_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_product_list`  AS SELECT `p`.`id` AS `id`, `p`.`name` AS `name`, `c`.`id` AS `category_id`, `c`.`url` AS `category_url`, (select `pi`.`image_url` from `product_images` `pi` where `pi`.`product_id` = `p`.`id` limit 1) AS `image`, min(coalesce(`pv`.`sale_price`,`pv`.`price`)) AS `price`, sum(`pv`.`stock`) AS `stock`, `p`.`status` AS `status`, `p`.`created_at` AS `created_at` FROM ((`products` `p` left join `product_variants` `pv` on(`p`.`id` = `pv`.`product_id`)) join `categories` `c` on(`p`.`category_id` = `c`.`id`)) GROUP BY `p`.`id` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `variant_id` (`variant_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coupons`
--
ALTER TABLE `coupons`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `coupon_id` (`coupon_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `variant_id` (`variant_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `brand_id` (`brand_id`);

--
-- Indexes for table `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_variants`
--
ALTER TABLE `product_variants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sku` (`sku`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `color_id` (`color_id`),
  ADD KEY `size_id` (`size_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `sizes`
--
ALTER TABLE `sizes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `carts`
--
ALTER TABLE `carts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `colors`
--
ALTER TABLE `colors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `coupons`
--
ALTER TABLE `coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `product_variants`
--
ALTER TABLE `product_variants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sizes`
--
ALTER TABLE `sizes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`);

--
-- Constraints for table `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_variants`
--
ALTER TABLE `product_variants`
  ADD CONSTRAINT `product_variants_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_variants_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  ADD CONSTRAINT `product_variants_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
