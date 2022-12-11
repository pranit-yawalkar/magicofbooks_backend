package com.magicofbooks.backend.service;

import com.magicofbooks.backend.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    List<String> getRoleNames(Set<Role> roles);
    Role getRoleByRoleName(String roleName);
}
