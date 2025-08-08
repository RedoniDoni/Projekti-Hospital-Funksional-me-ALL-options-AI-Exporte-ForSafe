package com.example.HospitalManagment.security.auth;

import com.example.HospitalManagment.repository.UserRepository;
import com.example.HospitalManagment.security.service.JwtService;
import com.example.HospitalManagment.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${base.url}")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authentication(request));
    }
}