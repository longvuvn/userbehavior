package com.example.userbehaviormanagement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userbehaviormanagement.entities.dto.EventLogDTO;
import com.example.userbehaviormanagement.services.EventLogService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/event-logs")
@RequiredArgsConstructor
public class EventLogController {

    private final EventLogService eventLogService;

    @GetMapping
    public ResponseEntity<List<EventLogDTO>> getAll() {
        List<EventLogDTO> eventLogs = eventLogService.getAllEventLogs();
        return ResponseEntity.status(200).body(eventLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventLogDTO> getById(@PathVariable String id) {
        EventLogDTO eventLog = eventLogService.getEventLogById(id);
        return ResponseEntity.status(200).body(eventLog);
    }

    @PostMapping
    public ResponseEntity<EventLogDTO> create(@RequestBody EventLogDTO eventLogDTO) {
        EventLogDTO createdEventLog = eventLogService.createEventLog(eventLogDTO);
        return ResponseEntity.status(201).body(createdEventLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventLogDTO> update(@PathVariable String id, EventLogDTO eventLogDTO) {
        EventLogDTO updatedEventLog = eventLogService.updateEventLog(id, eventLogDTO);
        return ResponseEntity.status(200).body(updatedEventLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        eventLogService.deleteEventLog(id);
        return ResponseEntity.status(204).build();
    }
}
