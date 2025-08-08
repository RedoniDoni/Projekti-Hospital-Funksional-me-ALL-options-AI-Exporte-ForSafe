package com.example.HospitalManagment.security.auth;

import com.example.HospitalManagment.entity.User;
import com.example.HospitalManagment.repository.RoleRepository;
import com.example.HospitalManagment.repository.UserRepository;
import com.example.HospitalManagment.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authentication(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).userId(user.getId()).build();
    }
}