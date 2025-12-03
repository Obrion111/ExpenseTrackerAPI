package com.obrion.expense_tracker_api.service;

import com.obrion.expense_tracker_api.dto.ExpenseRequestDTO;
import com.obrion.expense_tracker_api.dto.ExpenseResponseDTO;
import com.obrion.expense_tracker_api.entity.Category;
import com.obrion.expense_tracker_api.entity.Expense;
import com.obrion.expense_tracker_api.entity.User;
import com.obrion.expense_tracker_api.repository.CategoryRepository;
import com.obrion.expense_tracker_api.repository.ExpenseRepository;
import com.obrion.expense_tracker_api.repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setTitle(dto.title());
        expense.setAmount(dto.amount());
        expense.setDate(dto.date());
        expense.setUser(user);
        expense.setCategory(category);

        expenseRepository.save(expense);

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                category.getName(),
                user.getName()
        );



    }


    private ExpenseResponseDTO mapToDto(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory().getName(),
                expense.getUser().getName()
        );
    }



    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }



    public List<ExpenseResponseDTO> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    public void deleteExpense(Long id){
        if(!expenseRepository.existsById(id)){
            throw new RuntimeException("Expense not found");
        }
        expenseRepository.deleteById(id);
    }


    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));




        // atualizar os campos
        expense.setTitle(dto.title());
        expense.setAmount(dto.amount());
        expense.setDate(dto.date());
        expense.setImageUrl(dto.imageUrl());

        // Atualizar categoria
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        expense.setCategory(category);

        // Atualizar user
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);

        expenseRepository.save(expense);

        return mapToDto(expense);


    }


    public ExpenseResponseDTO getExpenseById(Long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return mapToDto(expense);
    }


    public List<ExpenseResponseDTO> getFilteredExpenses(Long userId, String period, String startDate, String endDate){

        // 1 Sem filtros -> devolve tudo ou filtra por user
        if (period == null && startDate == null && endDate == null){
            if(userId != null){
                return getExpensesByUser(userId);
            }
            return getAllExpenses();
        }

        LocalDate now = LocalDate.now();
        LocalDate start = null;
        LocalDate endD = now;

        //2. Filtros predefinidos
        if (period != null){
            switch (period) {
                case "past_week" -> start = now.minusWeeks(1);
                case "last_month" -> start = now.minusMonths(1);
                case "last_3_months" -> start = now.minusMonths(3);
            }
        }

        // 3. Filtro custom
        if (startDate != null && endDate != null){
            start = LocalDate.parse(startDate);
            endD = LocalDate.parse(endDate);
        }



        // 4. Procurar despesas filtradas
        List<Expense> expenses;

        if(userId != null){
            expenses = expenseRepository.findByUserIdAndDateBetween(userId, start, endD);
        } else {
            expenses = expenseRepository.findByDateBetween(start,   endD);
        }

        return expenses.stream()
                .map(this::mapToDto)
                .toList();


    }

}
