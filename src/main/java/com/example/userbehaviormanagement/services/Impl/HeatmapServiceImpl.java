package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.dto.HeatmapDTO;
import com.example.userbehaviormanagement.services.HeatmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeatmapServiceImpl implements HeatmapService {

    @Override
    public List<HeatmapDTO> getHeatmap(String url, String type) {
        return List.of();
    }
}
