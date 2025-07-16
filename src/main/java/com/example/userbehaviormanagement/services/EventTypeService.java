package com.example.userbehaviormanagement.services;

import java.util.List;

import com.example.userbehaviormanagement.entities.dto.EventTypeDTO;

public interface EventTypeService {
    List<EventTypeDTO> getAllEventTypes();

    EventTypeDTO getEventTypeById(String id);

    EventTypeDTO createEventType(EventTypeDTO eventTypeDTO);

    EventTypeDTO updateEventType(String id, EventTypeDTO eventTypeDTO);

    void deleteEventType(String id);
}
