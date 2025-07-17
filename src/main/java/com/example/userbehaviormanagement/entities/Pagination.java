package com.example.userbehaviormanagement.entities;

import lombok.Data;

import java.util.List;

@Data
public class Pagination<T> {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> content;
}
