package com.itvillage.jwtauthentication.controller;

import com.itvillage.jwtauthentication.dto.response.LoggedUserDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserDetailsController {

    @GetMapping("/user-details")
    public LoggedUserDetailsResponse getLoggedUserDetails() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> rolesList = new ArrayList<>();

        for (GrantedAuthority authority : auth.getAuthorities()) {
            rolesList.add(authority.getAuthority());
        }

        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
        loggedUserDetailsResponse.setUserName(auth.getName());
        loggedUserDetailsResponse.setUserRole(rolesList);
        loggedUserDetailsResponse.setIsAuthenticated(auth.isAuthenticated());

        return loggedUserDetailsResponse;
    }

    @GetMapping("/test-api")
    public String getUser() {
        return "I am User";
    }

}
