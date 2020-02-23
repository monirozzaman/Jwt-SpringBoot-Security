package com.itvillage.jwtauthentication.controller;

import com.itvillage.jwtauthentication.dto.request.LoginForm;
import com.itvillage.jwtauthentication.dto.request.SignUpForm;
import com.itvillage.jwtauthentication.services.SignUpAndSignInService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/")
@AllArgsConstructor
public class AuthRestAPIs {

    private final SignUpAndSignInService signUpAndSignInService;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        return ResponseEntity.ok(signUpAndSignInService.signIn(loginRequest));
    }

    @PostMapping("signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        return signUpAndSignInService.signUp(signUpRequest);
    }

    @GetMapping("test")
    public String get() {
        return "hi";
    }

}