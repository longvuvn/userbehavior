package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();
    AdminDTO createAdmin(AdminDTO adminDTO);
}
