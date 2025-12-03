package com.obrion.expense_tracker_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "expense_notes")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class ExpenseNote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;


}
