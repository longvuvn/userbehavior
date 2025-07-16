package com.example.userbehaviormanagement.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"admin\"")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    private String department;
}
