package com.smartera.storeapp.controller;

import com.smartera.storeapp.entity.LoginRequest;
import com.smartera.storeapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */
//@Controller
public class LoginController {

//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//    @Autowired
//    private AuthService authService;
//    @GetMapping("/login")
//    public String loginForm(Model model) {
//        model.addAttribute("loginRequest", new LoginRequest());
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginRequest loginRequest) {
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//        String authorizationLevel = loginRequest.getAuthorizationLevel();
//        logger.info("gelen istek: {}", loginRequest);
//        boolean isAuthenticated = authService.authenticateUser(email, password, authorizationLevel);
//        if (isAuthenticated) {
//            logger.info("gelen istek doğru {}" ,  email + " "+ password+ " "+ authorizationLevel );
//            return "redirect:/index";
//        } else {
//            String errorMessage = "Kimlik doğrulama başarısız.";
//            return "redirect:/login?error=true";
//
//        }
//    }


}