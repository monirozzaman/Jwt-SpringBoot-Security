package com.itvillage.jwtauthentication.controller;

import com.itvillage.jwtauthentication.dto.response.LoggedUserDetailsResponse;
import com.itvillage.jwtauthentication.services.SignUpAndSignInService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthDetailsController {
    private final SignUpAndSignInService signUpAndSignInService;

    @GetMapping("loggedUser")
    public LoggedUserDetailsResponse getDashboard(Authentication authentication) {

        return signUpAndSignInService.getLoggedUserDetails(authentication);
    }

}
