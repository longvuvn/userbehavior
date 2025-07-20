package com.example.userbehaviormanagement.controllers;


import com.example.userbehaviormanagement.entities.auth.AuthRequest;
import com.example.userbehaviormanagement.entities.auth.AuthResponse;
import com.example.userbehaviormanagement.entities.auth.RefreshTokenRequest;
import com.example.userbehaviormanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = userService.authenticate(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthResponse authResponse = userService.refreshToken(refreshTokenRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

}
