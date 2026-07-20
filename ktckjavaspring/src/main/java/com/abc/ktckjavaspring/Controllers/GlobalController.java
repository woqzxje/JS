package com.abc.ktckjavaspring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.abc.ktckjavaspring.Entity.Category;
import com.abc.ktckjavaspring.Service.CategoryService;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {
    
    private final CategoryService categoriesService;

    @ModelAttribute("categories")
    public List<Category> getMenus(){
        return categoriesService.findAll();
    }
}
