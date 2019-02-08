package com.interswitch.voucherz.authservice.controller.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignupRequest {

    @NotBlank(message = "Required")
    @Pattern(message="The password's first character must be a letter, it must contain at least 4 " +
            "characters and no more than 15 characters and no characters other than letters, numbers " +
            "and the underscore may be used", regexp = "^[a-zA-Z]\\w{3,14}$")
    private String password;

    @Length(min = 3, max = 50)
    @NotBlank(message = "required")
    private String firstName;

    @Length(min = 3, max = 50)
    @NotBlank(message = "required")
    private String lastName;

    @Email
    @NotBlank(message = "required")
    @Length(min = 6, max = 50)
    private String email;

    public int getCompanySize() {
        return companySize;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }

    private int companySize;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +

                ", email='" + email + '\'' +
                ", companySize=" + companySize +
                '}';
    }
}
