package com.waa.PropertyManagment.service;

import com.waa.PropertyManagment.entity.dto.request.LoginRequest;
import com.waa.PropertyManagment.entity.dto.request.RegisterRequest;
import com.waa.PropertyManagment.entity.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
   // public void activateUserAccount(long userId);

    void register(RegisterRequest registerRequest);
   // LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
