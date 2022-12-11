package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.exception.CustomException;
import com.magicofbooks.backend.model.Authority;
import com.magicofbooks.backend.model.auth.JwtRequest;
import com.magicofbooks.backend.model.auth.JwtResponse;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.repository.UserRepository;
import com.magicofbooks.backend.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) {
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        try {
            this.authenticate(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("User not found");
        }
        final UserDetails userDetails = loadUserByUsername(email);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = this.userRepository.findByEmail(email);
        List<String> roles = this.roleService.getRoleNames(user.getRoles());
        return new JwtResponse(user, newGeneratedToken, roles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);


            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), getAuthorities(user)
            );
    }

    private Set<Authority> getAuthorities(User user) {
        Set<Authority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new Authority("ROLE_" + role.getRoleName()));
        });

        return authorities;
    }


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials from User");
        }


    }

    public User getUserFromUserDetails(org.springframework.security.core.userdetails.User user) {
        return this.userRepository.findByEmail(user.getUsername());
    }


}
