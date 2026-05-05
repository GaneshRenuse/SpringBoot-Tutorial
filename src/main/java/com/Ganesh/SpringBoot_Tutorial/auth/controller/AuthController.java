package com.Ganesh.SpringBoot_Tutorial.auth.controller;

import com.Ganesh.SpringBoot_Tutorial.auth.dto.AuthRequest;
import com.Ganesh.SpringBoot_Tutorial.auth.dto.AuthResponse;
import com.Ganesh.SpringBoot_Tutorial.auth.util.JwtUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        if ("admin".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            String token = JwtUtil.generateToken(request.getUsername());
            return new AuthResponse(token);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
