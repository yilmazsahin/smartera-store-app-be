package com.smartera.storeapp.controller;

import com.smartera.storeapp.entity.LoginRequest;
import com.smartera.storeapp.service.AuthService;
//import jdk.incubator.vector.VectorOperators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */
@Controller
@RequestMapping("/api")

public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String authorizationLevel = loginRequest.getAuthorizationLevel();
        logger.info("Incoming request : {}", loginRequest);
        boolean isAuthenticated = authService.authenticateUser(email, password, authorizationLevel);
        if (isAuthenticated) {
            logger.info("Incoming request is correct {}", email + " " + password + " " + authorizationLevel);
            return ResponseEntity.ok().build();
        } else {
            String errorMessage = "Authentication failed";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);

        }
    }
}
