package com.obrion.expense_tracker_api.repository;

import com.obrion.expense_tracker_api.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

}
