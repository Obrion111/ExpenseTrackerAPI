package com.obrion.expense_tracker_api.service;

import com.obrion.expense_tracker_api.entity.Category;
import com.obrion.expense_tracker_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<Category> getAll(){
        return repository.findAll();
    }







}
