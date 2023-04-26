package com.waa.PropertyManagment.service.impl;

import com.waa.PropertyManagment.entity.Role;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.entity.dto.request.LoginRequest;
import com.waa.PropertyManagment.entity.dto.request.RefreshTokenRequest;
import com.waa.PropertyManagment.entity.dto.request.RegisterRequest;
import com.waa.PropertyManagment.entity.dto.response.LoginResponse;
import com.waa.PropertyManagment.enums.Roles;
import com.waa.PropertyManagment.repo.RoleRepo;
import com.waa.PropertyManagment.repo.UserRepo;
import com.waa.PropertyManagment.service.AuthService;
import com.waa.PropertyManagment.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if(isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew is this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            final String accessToken = jwtUtil.doGenerateToken(  jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new LoginResponse();
    }
    @Override
    public void register(RegisterRequest registerRequest) {
        try {
            User user = modelMapper.map(registerRequest, User.class);
            user.setName(Optional.ofNullable(registerRequest.getName()).orElse("Default Name"));
            Roles roleValue = registerRequest.getIsOwner() ? Roles.OWNER : Roles.CUSTOMER;
            Role role = roleRepo.findByRole(roleValue);
            user.setRoles(Collections.singletonList(role));
            user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
            //user.setActive(false);

            userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
