package com.example.userbehaviormanagement.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userbehaviormanagement.entities.dto.EventTypeDTO;
import com.example.userbehaviormanagement.services.EventTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/event-types")
@RequiredArgsConstructor
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @GetMapping
    public ResponseEntity<List<EventTypeDTO>> getAll() {
        List<EventTypeDTO> eventTypes = eventTypeService.getAllEventTypes();
        return ResponseEntity.status(200).body(eventTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> getById(String id) {
        EventTypeDTO eventType = eventTypeService.getEventTypeById(id);
        return ResponseEntity.status(200).body(eventType);
    }

    @PostMapping
    public ResponseEntity<EventTypeDTO> create(@RequestBody EventTypeDTO eventTypeDTO) {
        EventTypeDTO createdEventType = eventTypeService.createEventType(eventTypeDTO);
        return ResponseEntity.status(201).body(createdEventType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventTypeDTO> update(String id, EventTypeDTO eventTypeDTO) {
        EventTypeDTO updatedEventType = eventTypeService.updateEventType(id, eventTypeDTO);
        return ResponseEntity.status(200).body(updatedEventType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        eventTypeService.deleteEventType(id);
        return ResponseEntity.status(204).build();
    }
}
