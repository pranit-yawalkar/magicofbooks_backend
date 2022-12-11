package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.model.auth.JwtRequest;
import com.magicofbooks.backend.model.auth.JwtResponse;
import com.magicofbooks.backend.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return this.jwtService.createJwtToken(jwtRequest);
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.jwtService.loadUserByUsername(principal.getName());
    }
}
