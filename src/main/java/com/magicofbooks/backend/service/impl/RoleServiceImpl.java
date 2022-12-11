package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.exception.ResourceNotFoundException;
import com.magicofbooks.backend.repository.RoleRepository;
import com.magicofbooks.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicofbooks.backend.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role createRole(Role role) {
		return this.roleRepository.save(role);
	}

	public List<Role> getAllRoles() {
		List<Role> roles = this.roleRepository.findAll();
		return roles;
	}

	public List<String> getRoleNames(Set<Role> roles) {
		List<String> roleNames = new ArrayList<>();
		roles.forEach(role-> {
			roleNames.add(role.getRoleName());
		});
		return roleNames;
	}

	public Role getRoleByRoleName(String roleName) {
		return this.roleRepository.findById(roleName).orElseThrow(() -> new ResourceNotFoundException("No Role Found"));
	}
}
