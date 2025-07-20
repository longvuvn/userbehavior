package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.config.CustomUserDetailsService;
import com.example.userbehaviormanagement.entities.User;
import com.example.userbehaviormanagement.entities.auth.AuthRequest;
import com.example.userbehaviormanagement.entities.auth.AuthResponse;
import com.example.userbehaviormanagement.entities.auth.RefreshTokenRequest;
import com.example.userbehaviormanagement.services.UserService;
import com.example.userbehaviormanagement.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String accessToken = jwtUtil.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
        User user = customUserDetailsService.getUserByUsername(userDetails.getUsername());
        String userId = user.getId().toString();
        String fullName = user.getFullName();
        String avatar = user.getAvatar();

        return new AuthResponse(accessToken, refreshToken, userId, fullName, avatar);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        if (!jwtUtil.validateToken(refreshToken)) {
            return new AuthResponse("không hợp lệ", "không hợp lệ", "Không hợp lệ", "Không hợp lệ", "Không hợp lệ");
        } else {
            String username = jwtUtil.extractUsername(refreshToken);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            String accessToken = jwtUtil.generateAccessToken(userDetails.getUsername());
            User user = customUserDetailsService.getUserByUsername(userDetails.getUsername());
            String userId = user.getId().toString();
            String fullName = user.getFullName();
            String avatar = user.getAvatar();
            return new AuthResponse(accessToken, refreshToken, userId, fullName, avatar);
        }
    }
}
