package com.obrion.expense_tracker_api.service;


import com.obrion.expense_tracker_api.dto.ExpenseNoteRequestDTO;
import com.obrion.expense_tracker_api.dto.ExpenseNoteResponseDTO;
import com.obrion.expense_tracker_api.entity.Expense;
import com.obrion.expense_tracker_api.entity.ExpenseNote;
import com.obrion.expense_tracker_api.repository.ExpenseNoteRepository;
import com.obrion.expense_tracker_api.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseNoteService {

    private final ExpenseNoteRepository expenseNoteRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseNoteService (ExpenseNoteRepository expenseNoteRepository, ExpenseRepository expenseRepository){
        this.expenseNoteRepository = expenseNoteRepository;
        this.expenseRepository = expenseRepository;
    }




    public ExpenseNoteResponseDTO criarNota(Long expenseId, ExpenseNoteRequestDTO dto){

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow();

        if (dto.text().isEmpty()){
            throw new RuntimeException("Nota vazia");
        }

        ExpenseNote nova = new ExpenseNote();
        nova.setText(dto.text());
        nova.setCreatedAt(LocalDateTime.now());
        nova.setUpdatedAt(LocalDateTime.now());
        nova.setExpense(expense);


        ExpenseNote guardada = expenseNoteRepository.save(nova);

        return new ExpenseNoteResponseDTO(
                guardada.getId(),
                guardada.getText(),
                guardada.getCreatedAt(),
                guardada.getUpdatedAt()

        );
    }


    public List<ExpenseNoteResponseDTO> getNotasPorDespesa(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow();

        if (expense.getNotes() == null){
            throw new RuntimeException("Nao existem notas para esta despesa");
        }


        return expense.getNotes()
                .stream()
                .map(nota -> new ExpenseNoteResponseDTO(
                        nota.getId(),
                        nota.getText(),
                        nota.getCreatedAt(),
                        nota.getUpdatedAt()
                )).toList();


    }




}
