package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Admin;
import com.example.userbehaviormanagement.entities.dto.AdminDTO;
import com.example.userbehaviormanagement.repositories.AdminRepository;
import com.example.userbehaviormanagement.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdminDTO.class))
                .collect(Collectors.toList()) ;
    }
}
