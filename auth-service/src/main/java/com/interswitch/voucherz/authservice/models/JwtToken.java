package com.interswitch.voucherz.authservice.models;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class JwtToken implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public JwtToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
