package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Heatmap;
import com.example.userbehaviormanagement.projections.HeatmapProjection;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HeatmapRepository extends JpaRepository<Heatmap, UUID> {
    @Query("SELECT h.x AS x, h.y AS y, SUM(h.count) AS count FROM Heatmap h WHERE h.pageUrl = :url AND h.eventType.name = :type GROUP BY h.x, h.y")
    List<HeatmapProjection> getHeatmapData(@Param("url") String url, @Param("type") String type);

}
