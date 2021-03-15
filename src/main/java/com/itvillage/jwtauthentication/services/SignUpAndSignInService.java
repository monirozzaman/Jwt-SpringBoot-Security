package com.itvillage.jwtauthentication.services;

import com.itvillage.jwtauthentication.dto.request.LoginForm;
import com.itvillage.jwtauthentication.dto.request.ResetPasswordForm;
import com.itvillage.jwtauthentication.dto.request.SignUpForm;
import com.itvillage.jwtauthentication.dto.response.JwtResponse;
import com.itvillage.jwtauthentication.dto.response.LoggedUserDetailsResponse;
import com.itvillage.jwtauthentication.model.Role;
import com.itvillage.jwtauthentication.model.RoleName;
import com.itvillage.jwtauthentication.model.User;
import com.itvillage.jwtauthentication.repository.RoleRepository;
import com.itvillage.jwtauthentication.repository.UserRepository;
import com.itvillage.jwtauthentication.security.jwt.JwtProvider;
import com.itvillage.jwtauthentication.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SignUpAndSignInService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private UserPrinciple userPrinciple;

    public ResponseEntity<String> signUp(SignUpForm signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        User user = new User();

        Set<RoleName> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case ROLE_ADMIN:
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case ROLE_PM:
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                case ADMIN:
                    Role admin = roleRepository.findByName(RoleName.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(admin);

                    break;
                case LEARNER:
                    Role learner = roleRepository.findByName(RoleName.LEARNER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(learner);

                    break;
                case INSTRUCTOR:
                    Role instructor = roleRepository.findByName(RoleName.INSTRUCTOR)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(instructor);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setId(signUpRequest.getUserId());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<String>(signUpRequest.getUserId(), HttpStatus.OK);
    }

    public JwtResponse signIn(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(jwt);
    }

    public String getRole() {
        return userPrinciple.getUsername();
    }

    public LoggedUserDetailsResponse getLoggedUserDetails(Authentication authentication) {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> userRoleList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            userRoleList.add(grantedAuthority.getAuthority());
        }
        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
        loggedUserDetailsResponse.setUserName(authentication.getName());
        loggedUserDetailsResponse.setUserRole(userRoleList);
        loggedUserDetailsResponse.setIsAuthenticated(authentication.isAuthenticated());
        return loggedUserDetailsResponse;
    }

    public ResponseEntity<String> reset(String userId, ResetPasswordForm resetPasswordForm) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        } else {
            User user = userOptional.get();
            user.setPassword(encoder.encode(resetPasswordForm.getPassword()));
            userRepository.save(user);
            return new ResponseEntity("Updated", HttpStatus.OK);
        }

    }
}
