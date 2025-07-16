package com.example.userbehaviormanagement.services.Impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.userbehaviormanagement.entities.UserSession;
import com.example.userbehaviormanagement.entities.dto.UserSessionDTO;
import com.example.userbehaviormanagement.repositories.UserSessionRepository;
import com.example.userbehaviormanagement.services.UserSessionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public List<UserSessionDTO> getAllUserSessions() {
        List<UserSession> userSessions = userSessionRepository.findAll();
        return userSessions.stream()
                .map(userSession -> modelMapper.map(userSession, UserSessionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserSessionDTO getUserSessionById(String id) {
        UUID sessionId = UUID.fromString(id);
        Optional<UserSession> userSessionOptional = userSessionRepository.findById(sessionId);
        return userSessionOptional.map(userSession -> modelMapper.map(userSession, UserSessionDTO.class))
                .orElseThrow(() -> new RuntimeException("User session not found with id: " + id));

    }

    @Override
    public UserSessionDTO createUserSession(UserSessionDTO userSessionDTO) {
        UserSession userSession = modelMapper.map(userSessionDTO, UserSession.class);

        // Tự động set thời gian tạo nếu chưa có (thực tế @CreatedDate sẽ tự set)
        if (userSession.getCreatedAt() == null) {
            userSession.setCreatedAt(Instant.now());
        }

        UserSession savedUserSession = userSessionRepository.save(userSession);
        UserSessionDTO result = modelMapper.map(savedUserSession, UserSessionDTO.class);

        // Gửi thông báo realtime về session mới
        messagingTemplate.convertAndSend("/topic/user-session", result);

        return result;
    }

    @Override
    public UserSessionDTO updateUserSession(String id, UserSessionDTO userSessionDTO) {
        UUID sessionId = UUID.fromString(id);
        UserSession existingUserSession = userSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("User session not found with id: " + id));
        modelMapper.map(userSessionDTO, existingUserSession);
        UserSession updatedUserSession = userSessionRepository.save(existingUserSession);
        return modelMapper.map(updatedUserSession, UserSessionDTO.class);
    }

    @Override
    public void deleteUserSession(String id) {
        UUID sessionId = UUID.fromString(id);
        if (!userSessionRepository.existsById(sessionId)) {
            throw new RuntimeException("User session not found with id: " + id);
        }
        userSessionRepository.deleteById(sessionId);
    }

}
