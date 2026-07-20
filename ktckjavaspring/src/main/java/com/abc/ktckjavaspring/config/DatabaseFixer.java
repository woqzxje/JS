package com.abc.ktckjavaspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFixer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            jdbcTemplate.execute("ALTER TABLE `carts` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;");
            System.out.println("Fixed AUTO_INCREMENT for carts table.");
        } catch (Exception e) {
            System.out.println("carts table already has AUTO_INCREMENT or error: " + e.getMessage());
        }
        try {
            jdbcTemplate.execute("ALTER TABLE `carts` DROP FOREIGN KEY `carts_ibfk_1`;");
            System.out.println("Dropped FOREIGN KEY carts_ibfk_1 from carts table.");
        } catch (Exception e) {
            System.out.println("carts_ibfk_1 already dropped or error: " + e.getMessage());
        }
        try {
            jdbcTemplate.execute("ALTER TABLE `cart_items` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;");
            System.out.println("Fixed AUTO_INCREMENT for cart_items table.");
        } catch (Exception e) {
            System.out.println("cart_items table already has AUTO_INCREMENT or error: " + e.getMessage());
        }
    }
}
