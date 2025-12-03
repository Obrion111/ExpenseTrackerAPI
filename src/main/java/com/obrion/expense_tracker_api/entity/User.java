package com.obrion.expense_tracker_api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    private String profileImageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user")
    private List<Budget> budgets;

}
