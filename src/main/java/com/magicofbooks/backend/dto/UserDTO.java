package com.magicofbooks.backend.dto;

import com.magicofbooks.backend.model.ImageModel;
import com.magicofbooks.backend.model.ImageModel;

public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;

    private ImageModel userImage;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public ImageModel getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageModel userImage) {
        this.userImage = userImage;
    }
}
