package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.RegisterDTO;
import com.magicofbooks.backend.dto.UserDTO;
import com.magicofbooks.backend.exception.CustomException;
import com.magicofbooks.backend.model.*;
import com.magicofbooks.backend.repository.RoleRepository;
import com.magicofbooks.backend.repository.UserRepository;
import com.magicofbooks.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WishlistServiceImpl wishlistService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CompletedListServiceImpl completedListService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User getUserByDTO(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPassword(registerDTO.getPassword());
        user.setUserImage(registerDTO.getUserImageModel());
        return user;
    }

    public UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserImage(user.getUserImage());
        return userDTO;
    }

    public ImageModel uploadImage(MultipartFile multipartFile) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(multipartFile.getOriginalFilename());
        imageModel.setType(multipartFile.getContentType());
        imageModel.setPicByte(multipartFile.getBytes());
        return imageModel;
    }

    public User registerUser(RegisterDTO registerDTO, MultipartFile file) throws IOException {
        ImageModel userImageModel = this.uploadImage(file);
        registerDTO.setUserImageModel(userImageModel);
        User user = this.getUserByDTO(registerDTO);
        Role role = this.roleRepository.findById("user").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        Wishlist wishlist = new Wishlist();
        CompletedList completedList = new CompletedList();
        User createdUser = this.userRepository.save(user);
        wishlist.setUser(createdUser);
        completedList.setUser(createdUser);
        this.wishlistService.createWishlist(wishlist);
        this.completedListService.createCompList(completedList);
        return createdUser;
    }

    public UserDTO updateUserProfile(UserDTO userDTO, MultipartFile file, Principal principal) throws IOException {
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User)this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to continue");
        }
        User user = this.jwtService.getUserFromUserDetails(userDetails);
        ImageModel userImageModel = this.uploadImage(file);
        user.setUserImage(userImageModel);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = this.userRepository.save(user);
        return this.getUserDTOFromUser(user);
    }

    @Override
    public UserDTO getUserDetails(Principal principal) {
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User)this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to continue");
        }
        User user = this.jwtService.getUserFromUserDetails(userDetails);
        return this.getUserDTOFromUser(user);
    }


    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
