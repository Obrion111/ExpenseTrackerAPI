package com.obrion.expense_tracker_api.controller;

import com.obrion.expense_tracker_api.entity.Category;
import com.obrion.expense_tracker_api.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAll(){
        return service.getAll();
    }






}
