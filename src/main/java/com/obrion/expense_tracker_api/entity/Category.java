package com.obrion.expense_tracker_api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;
}
