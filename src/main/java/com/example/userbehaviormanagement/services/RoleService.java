package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.Role;
import com.example.userbehaviormanagement.entities.dto.RoleDTO;
import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    Role getRoleByName(String name);
}
