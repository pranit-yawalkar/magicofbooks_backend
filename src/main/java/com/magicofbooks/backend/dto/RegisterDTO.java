package com.magicofbooks.backend.dto;

import com.magicofbooks.backend.model.ImageModel;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.ImageModel;

public class RegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private ImageModel userImageModel;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String firstName, String lastName, String password, ImageModel userImageModel) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userImageModel = userImageModel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageModel getUserImageModel() {
        return userImageModel;
    }

    public void setUserImageModel(ImageModel userImageModel) {
        this.userImageModel = userImageModel;
    }
}
