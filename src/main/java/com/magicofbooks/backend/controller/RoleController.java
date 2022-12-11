package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.model.Role;
import com.magicofbooks.backend.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = this.roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = this.roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
