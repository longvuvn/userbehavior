package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Role;
import com.example.userbehaviormanagement.entities.dto.RoleDTO;
import com.example.userbehaviormanagement.repositories.RoleRepository;
import com.example.userbehaviormanagement.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
       List<Role> roles = roleRepository.findAll();
       return roles.stream()
               .map(role -> modelMapper.map(role, RoleDTO.class))
               .collect(Collectors.toList());
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
