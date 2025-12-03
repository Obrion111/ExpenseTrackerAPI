package com.obrion.expense_tracker_api.dto;

import java.time.LocalDate;

public record ExpenseResponseDTO(
        Long id,
        String title,
        Double amount,
        LocalDate date,
        String category,
        String userName
) {}
