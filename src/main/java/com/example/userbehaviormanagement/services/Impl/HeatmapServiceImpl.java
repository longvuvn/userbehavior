package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Heatmap;
import com.example.userbehaviormanagement.entities.dto.HeatmapDTO;
import com.example.userbehaviormanagement.projections.HeatmapProjection;
import com.example.userbehaviormanagement.repositories.HeatmapRepository;
import com.example.userbehaviormanagement.services.HeatmapService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeatmapServiceImpl implements HeatmapService {

    private final HeatmapRepository heatmapRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<HeatmapDTO> getHeatmap(String url, String type) {
        List<HeatmapProjection> projections = heatmapRepository.getHeatmapData(url, type);
        return projections.stream()
                .map(p -> {
                    HeatmapDTO dto = new HeatmapDTO();
                    dto.setX(p.getX());
                    dto.setY(p.getY());
                    dto.setCount(p.getCount());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HeatmapDTO createHeatmap(HeatmapDTO heatmapDTO) {
        Heatmap heatmap = modelMapper.map(heatmapDTO, Heatmap.class);
        Heatmap savedHeatmap = heatmapRepository.save(heatmap);
        return modelMapper.map(savedHeatmap, HeatmapDTO.class);
    }
}
