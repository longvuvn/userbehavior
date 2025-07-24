package com.example.userbehaviormanagement.entities;


import com.example.userbehaviormanagement.enums.ReviewStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "\"review\"")
@Data
public class Review extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String comment;

    @Min(1)
    @Max(5)
    @NotNull
    private int rating;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
