package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Heatmap;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HeatmapRepository extends JpaRepository<Heatmap, UUID> {
    @Query("SELECT e.x as x, e.y as y, COUNT(e.id) as count " +
            "FROM EventLog e " +
            "WHERE e.url = :url AND e.eventType.name = :type " +
            "GROUP BY e.x, e.y")
    List<Map<String, Object>> getHeatmapData(@Param("url") String url, @Param("type") String type);
}
