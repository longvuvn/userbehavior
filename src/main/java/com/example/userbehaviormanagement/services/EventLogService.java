package com.example.userbehaviormanagement.services;

import java.util.List;

import com.example.userbehaviormanagement.entities.dto.EventLogDTO;

public interface EventLogService {
    List<EventLogDTO> getAllEventLogs();

    EventLogDTO getEventLogById(String id);

    EventLogDTO createEventLog(EventLogDTO eventLogDTO);

    EventLogDTO updateEventLog(String id, EventLogDTO eventLogDTO);

    void deleteEventLog(String id);
}
