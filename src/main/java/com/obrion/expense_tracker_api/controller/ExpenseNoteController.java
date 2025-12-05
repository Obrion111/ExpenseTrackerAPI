package com.obrion.expense_tracker_api.controller;


import com.obrion.expense_tracker_api.dto.ExpenseNoteRequestDTO;
import com.obrion.expense_tracker_api.dto.ExpenseNoteResponseDTO;
import com.obrion.expense_tracker_api.service.ExpenseNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseNoteController {


    private final ExpenseNoteService expenseNoteService;

    public ExpenseNoteController(ExpenseNoteService expenseNoteService){
        this.expenseNoteService = expenseNoteService;
    }



    @PostMapping("/{expenseId}/notes")
    public ExpenseNoteResponseDTO criarNota(
            @PathVariable Long expenseId,
            @RequestBody ExpenseNoteRequestDTO dto
            ){
        return expenseNoteService.criarNota(expenseId,dto);
    }


    @GetMapping("/{expenseId}/notes")
    public List<ExpenseNoteResponseDTO> listarNotas(
            @PathVariable Long expenseId
    ){
        return expenseNoteService.getNotasPorDespesa(expenseId);
    }

}
