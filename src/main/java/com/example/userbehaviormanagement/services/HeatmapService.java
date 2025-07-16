package com.example.userbehaviormanagement.services;

import java.util.List;

import com.example.userbehaviormanagement.entities.dto.HeatmapDTO;

public interface HeatmapService {
    List<HeatmapDTO> getHeatmap(String url, String type);
}
