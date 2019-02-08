package com.interswitch.voucherz.authservice.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ChangePassword {

    @NotBlank(message = "Required")
    @Pattern(message="The password's first character must be a letter, it must contain at least 4 " +
            "characters and no more than 15 characters and no characters other than letters, numbers " +
            "and the underscore may be used", regexp = "^[a-zA-Z]\\w{3,14}$")
    private String newPassword;

    @NotBlank(message = "Required")
    private String confirmPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
