package com.abc.b16.Service;

import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abc.b16.Entity.Category;
import com.abc.b16.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    //Tạo biến có kiểu dữ liệu repository
    private final CategoryRepository categoriesRepository;

    //lấy tất cả dữ liệu trong bảng categories
    public List<Category> findAll() {
        Pageable pageable = PageRequest.of(
            0,
            //số lượng trên 1 trang 
            6, 
            //order by sort DESC, ID ASC
            Sort.by("sort").descending().and(Sort.by("id").ascending()));
        return categoriesRepository.findAll(pageable).getContent();
    }
}
