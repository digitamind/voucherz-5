package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.JwtToken;

public interface JwtTokenDao {
    public boolean findById(String token);
    public void save(JwtToken token);
    public void delete(JwtToken token);
}
