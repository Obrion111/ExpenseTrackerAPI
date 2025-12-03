package com.obrion.expense_tracker_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Budget {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer month;
    private Integer year;

    private Double limitAmount;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;





}
