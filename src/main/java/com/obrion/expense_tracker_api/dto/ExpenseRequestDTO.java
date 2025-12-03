package com.obrion.expense_tracker_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



public record ExpenseRequestDTO(
        String title,
        Double amount,
        LocalDate date,
        String imageUrl,
        Long categoryId,
        Long userId

) {}
