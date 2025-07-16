package com.example.userbehaviormanagement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.userbehaviormanagement.entities.dto.UserSessionDTO;
import com.example.userbehaviormanagement.services.UserSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user-sessions")
@RequiredArgsConstructor
public class UserSessionController {

    private final UserSessionService userSessionService;

    @GetMapping
    public ResponseEntity<List<UserSessionDTO>> getAll() {
        List<UserSessionDTO> userSessions = userSessionService.getAllUserSessions();
        return ResponseEntity.status(200).body(userSessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSessionDTO> getById(String id) {
        UserSessionDTO userSession = userSessionService.getUserSessionById(id);
        return ResponseEntity.status(200).body(userSession);
    }

    @PostMapping
    public ResponseEntity<UserSessionDTO> create(@RequestBody UserSessionDTO userSessionDTO) {
        UserSessionDTO createdUserSession = userSessionService.createUserSession(userSessionDTO);
        return ResponseEntity.status(201).body(createdUserSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSessionDTO> update(String id, UserSessionDTO userSessionDTO) {
        UserSessionDTO updatedUserSession = userSessionService.updateUserSession(id, userSessionDTO);
        return ResponseEntity.status(200).body(updatedUserSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        userSessionService.deleteUserSession(id);
        return ResponseEntity.status(204).build();
    }
}
