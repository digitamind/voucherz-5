package com.interswitch.voucherz.authservice.controller.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Username {

    @Email
    @NotBlank(message = "Required")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
