package com.example.userbehaviormanagement.controllers;

import java.util.List;

import com.example.userbehaviormanagement.services.HeatmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userbehaviormanagement.entities.dto.HeatmapDTO;

@RestController
@RequestMapping("/api/v1/heatmap")
@RequiredArgsConstructor
public class HeatmapController {

    private final HeatmapService heatmapService;

    @GetMapping
    public ResponseEntity<List<HeatmapDTO>> getHeatmap(
            @RequestParam String page,
            @RequestParam String type) {
        List<HeatmapDTO> heatmap = heatmapService.getHeatmap(page, type);
        return ResponseEntity.status(HttpStatus.OK).body(heatmap);
    }

    @PostMapping
    public ResponseEntity<HeatmapDTO> createHeatmap(@RequestBody HeatmapDTO heatmap) {
        HeatmapDTO createdHeatmap = heatmapService.createHeatmap(heatmap);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHeatmap);
    }
}
