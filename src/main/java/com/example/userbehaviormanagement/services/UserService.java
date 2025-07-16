package com.example.userbehaviormanagement.services;


import com.example.userbehaviormanagement.entities.auth.AuthRequest;
import com.example.userbehaviormanagement.entities.auth.RefreshTokenRequest;

public interface UserService {
    AuthRequest login(AuthRequest authRequest);
    RefreshTokenRequest refreshToken(RefreshTokenRequest refreshTokenRequest);
}
