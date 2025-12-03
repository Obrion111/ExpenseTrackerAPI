package com.obrion.expense_tracker_api.controller;

import com.obrion.expense_tracker_api.dto.ExpenseRequestDTO;
import com.obrion.expense_tracker_api.dto.ExpenseResponseDTO;
import com.obrion.expense_tracker_api.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponseDTO create(@RequestBody ExpenseRequestDTO dto) {
        return expenseService.createExpense(dto);
    }


    @GetMapping
    public List<ExpenseResponseDTO> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        return expenseService.getFilteredExpenses(userId, period, startDate, endDate);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        expenseService.deleteExpense(id);

    }



    @PutMapping("/{id}")
    public ExpenseResponseDTO update(@PathVariable Long id, @RequestBody ExpenseRequestDTO body){
        return expenseService.updateExpense(id,body);
    }


    @GetMapping("/{id}")
    public ExpenseResponseDTO getById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }


}
