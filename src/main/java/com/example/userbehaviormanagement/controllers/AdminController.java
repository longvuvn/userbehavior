package com.example.userbehaviormanagement.controllers;


import com.example.userbehaviormanagement.entities.dto.AdminDTO;
import com.example.userbehaviormanagement.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAll(){
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.status(HttpStatus.OK).body(admins);
    }
}
