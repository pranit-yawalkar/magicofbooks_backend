package com.magicofbooks.backend.model.auth;

import com.magicofbooks.backend.model.User;

import java.util.List;

public class JwtResponse {

    private User user;
    private String jwtToken;

    private List<String> roles;

    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public JwtResponse(User user, String jwtToken, List<String> roles) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
