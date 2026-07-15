package com.abc.b16.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abc.b16.Entity.Category;

//interface
//extends
//JpaRepository
//Categories
//Long
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
