package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.auth.AuthRequest;
import com.example.userbehaviormanagement.entities.auth.RefreshTokenRequest;
import com.example.userbehaviormanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public AuthRequest login(AuthRequest authRequest) {
        return null;
    }

    @Override
    public RefreshTokenRequest refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }
}
