package com.abc.ktckjavaspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abc.ktckjavaspring.Entity.Category;

//interface
//extends
//JpaRepository
//Categories
//Long
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
