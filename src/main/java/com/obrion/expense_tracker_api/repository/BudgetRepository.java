package com.obrion.expense_tracker_api.repository;

import com.obrion.expense_tracker_api.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {}
