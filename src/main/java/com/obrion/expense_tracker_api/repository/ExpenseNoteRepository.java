package com.obrion.expense_tracker_api.repository;

import com.obrion.expense_tracker_api.entity.ExpenseNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseNoteRepository extends JpaRepository<ExpenseNote, Long> {

    List<ExpenseNote> findByExpenseId(Long expenseId);

}
