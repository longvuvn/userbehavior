package com.example.userbehaviormanagement.entities.dto;


import lombok.Data;

@Data
public class HeatmapDTO {
    private int x;
    private int y;
    private double value;
    private int count;
}
