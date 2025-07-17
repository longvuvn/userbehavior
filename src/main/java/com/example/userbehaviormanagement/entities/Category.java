package com.example.userbehaviormanagement.entities;


import com.example.userbehaviormanagement.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"categories\"")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> product;
}
