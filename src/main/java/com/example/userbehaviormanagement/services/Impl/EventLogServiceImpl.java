package com.example.userbehaviormanagement.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.userbehaviormanagement.entities.EventLog;
import com.example.userbehaviormanagement.entities.EventType;
import com.example.userbehaviormanagement.entities.UserSession;
import com.example.userbehaviormanagement.entities.dto.EventLogDTO;
import com.example.userbehaviormanagement.repositories.EventLogRepository;
import com.example.userbehaviormanagement.repositories.EventTypeRepository;
import com.example.userbehaviormanagement.repositories.UserSessionRepository;
import com.example.userbehaviormanagement.services.EventLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventLogServiceImpl implements EventLogService {

    private final EventLogRepository eventLogRepository;
    private final EventTypeRepository eventTypeRepository;
    private final UserSessionRepository userSessionRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public List<EventLogDTO> getAllEventLogs() {
        List<EventLog> eventLogs = eventLogRepository.findAll();
        return eventLogs.stream()
                .map(eventLog -> modelMapper.map(eventLog, EventLogDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventLogDTO getEventLogById(String id) {
        UUID eventLogId = UUID.fromString(id);
        Optional<EventLog> eventLogOptional = eventLogRepository.findById(eventLogId);
        return eventLogOptional.map(eventLog -> modelMapper.map(eventLog, EventLogDTO.class))
                .orElseThrow(() -> new RuntimeException("Event log not found with id: " + id));
    }

    @Override
    public EventLogDTO createEventLog(EventLogDTO eventLogDTO) {
        EventLog eventLog = modelMapper.map(eventLogDTO, EventLog.class);
        EventType eventType = eventTypeRepository.findById(UUID.fromString(eventLogDTO.getEventTypeId()))
                .orElseThrow(() -> new RuntimeException("EventType not found"));
        eventLog.setEventType(eventType);
        UserSession session = userSessionRepository.findById(UUID.fromString(eventLogDTO.getUserSessionId()))
                .orElseThrow(() -> new RuntimeException("UserSession not found"));
        eventLog.setSession(session);

        EventLog savedEventLog = eventLogRepository.save(eventLog);
        EventLogDTO result = modelMapper.map(savedEventLog, EventLogDTO.class);
        messagingTemplate.convertAndSend("/topic/event-log", result);
        return result;
    }

    @Override
    public EventLogDTO updateEventLog(String id, EventLogDTO eventLogDTO) {
        UUID eventLogId = UUID.fromString(id);
        EventLog existingEventLog = eventLogRepository.findById(eventLogId)
                .orElseThrow(() -> new RuntimeException("Event log not found with id: " + id));
        modelMapper.map(eventLogDTO, existingEventLog);
        EventLog updatedEventLog = eventLogRepository.save(existingEventLog);
        return modelMapper.map(updatedEventLog, EventLogDTO.class);
    }

    @Override
    public void deleteEventLog(String id) {
        UUID eventLogId = UUID.fromString(id);
        if (!eventLogRepository.existsById(eventLogId)) {
            throw new RuntimeException("Event log not found with id: " + id);
        }
        eventLogRepository.deleteById(eventLogId);
    }

}
