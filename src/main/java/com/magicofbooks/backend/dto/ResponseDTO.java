package com.magicofbooks.backend.dto;

public class ResponseDTO {
    private boolean status;
    private String message;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
