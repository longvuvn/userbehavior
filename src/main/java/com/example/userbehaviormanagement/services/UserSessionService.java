package com.example.userbehaviormanagement.services;

import java.util.List;

import com.example.userbehaviormanagement.entities.dto.UserSessionDTO;

public interface UserSessionService {
    List<UserSessionDTO> getAllUserSessions();

    UserSessionDTO getUserSessionById(String id);

    UserSessionDTO createUserSession(UserSessionDTO userSessionDTO);

    UserSessionDTO updateUserSession(String id, UserSessionDTO userSessionDTO);

    void deleteUserSession(String id);
}
