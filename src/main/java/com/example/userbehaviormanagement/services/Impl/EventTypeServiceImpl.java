package com.example.userbehaviormanagement.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.userbehaviormanagement.entities.EventType;
import com.example.userbehaviormanagement.entities.dto.EventTypeDTO;
import com.example.userbehaviormanagement.repositories.EventTypeRepository;
import com.example.userbehaviormanagement.services.EventTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EventTypeDTO> getAllEventTypes() {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        return eventTypes.stream()
                .map(eventType -> modelMapper.map(eventType, EventTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventTypeDTO getEventTypeById(String id) {
        UUID eventTypeId = UUID.fromString(id);
        Optional<EventType> eventTypeOptional = eventTypeRepository.findById(eventTypeId);
        return eventTypeOptional.map(eventType -> modelMapper.map(eventType, EventTypeDTO.class))
                .orElseThrow(() -> new RuntimeException("Event type not found with id: " + id));
    }

    @Override
    public EventTypeDTO createEventType(EventTypeDTO eventTypeDTO) {
        EventType eventType = modelMapper.map(eventTypeDTO, EventType.class);
        EventType savedEventType = eventTypeRepository.save(eventType);
        return modelMapper.map(savedEventType, EventTypeDTO.class);
    }

    @Override
    public EventTypeDTO updateEventType(String id, EventTypeDTO eventTypeDTO) {
        UUID eventTypeId = UUID.fromString(id);
        EventType existingEventType = eventTypeRepository.findById(eventTypeId)
                .orElseThrow(() -> new RuntimeException("Event type not found with id: " + id));
        modelMapper.map(eventTypeDTO, existingEventType);
        EventType updatedEventType = eventTypeRepository.save(existingEventType);
        return modelMapper.map(updatedEventType, EventTypeDTO.class);
    }

    @Override
    public void deleteEventType(String id) {
        UUID eventTypeId = UUID.fromString(id);
        if (!eventTypeRepository.existsById(eventTypeId)) {
            throw new RuntimeException("Event type not found with id: " + id);
        }
        eventTypeRepository.deleteById(eventTypeId);
    }

}
