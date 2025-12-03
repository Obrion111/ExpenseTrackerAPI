package com.obrion.expense_tracker_api.repository;

import com.obrion.expense_tracker_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
