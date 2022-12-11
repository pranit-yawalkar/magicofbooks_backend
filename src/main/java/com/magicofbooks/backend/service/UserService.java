package com.magicofbooks.backend.service;

import com.magicofbooks.backend.dto.RegisterDTO;
import com.magicofbooks.backend.dto.UserDTO;
import com.magicofbooks.backend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UserService {
    User registerUser(RegisterDTO registerDTO, MultipartFile file) throws Exception;
    UserDTO getUserDetails(Principal principal);
    User getUserByEmail(String email);
}
