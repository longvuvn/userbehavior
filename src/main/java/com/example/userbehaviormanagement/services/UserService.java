package com.example.userbehaviormanagement.services;


import com.example.userbehaviormanagement.entities.auth.AuthRequest;
import com.example.userbehaviormanagement.entities.auth.AuthResponse;
import com.example.userbehaviormanagement.entities.auth.RefreshTokenRequest;

public interface UserService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
