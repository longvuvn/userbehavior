package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Admin;
import com.example.userbehaviormanagement.entities.Role;
import com.example.userbehaviormanagement.entities.dto.AdminDTO;
import com.example.userbehaviormanagement.enums.UserStatus;
import com.example.userbehaviormanagement.repositories.AdminRepository;
import com.example.userbehaviormanagement.services.AdminService;
import com.example.userbehaviormanagement.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private static final String DEFAULT_AVATAR_PATH = "/data/image/c21f969b5f03d33d43e04f8f136e7682.png";
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdminDTO.class))
                .collect(Collectors.toList()) ;
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Role role = roleService.getRoleByName("Admin");
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        String avatar = adminDTO.getAvatar() != null ? adminDTO.getAvatar() : DEFAULT_AVATAR_PATH;
        admin.setAvatar(avatar);
        admin.setStatus(UserStatus.ACTIVE);
        admin.setRole(role);
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        Admin savedAdmin = adminRepository.save(admin);
        AdminDTO result = modelMapper.map(savedAdmin, AdminDTO.class);
        result.setAvatar(savedAdmin.getAvatar());
        return result;
    }
}
