package com.obrion.expense_tracker_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseNoteResponseDTO (
        Long id,
        String text,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

){
}
