package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.dto.RegisterDTO;
import com.magicofbooks.backend.dto.UserDTO;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<User> registerUser(@RequestPart("user") RegisterDTO registerDTO,
                                             @RequestPart("imageFile")MultipartFile file) {
        try {
            User createdUser = this.userService.registerUser(registerDTO, file);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PutMapping(value = "/update-profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserDTO> updateUserProfile(@RequestPart("user") UserDTO userDTO,
                                                     @RequestPart("imageFile")MultipartFile file, Principal principal) {
        try {
            UserDTO createdUser = this.userService.updateUserProfile(userDTO, file, principal);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserDetails(Principal principal) {
        UserDTO userDTO = this.userService.getUserDetails(principal);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('admin')")
    public String forAdmin() {
        return "This URL is only accessible to admin";
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('user')")
    public String forUser() {
        return "This URL is only accessible to users";
    }
}
