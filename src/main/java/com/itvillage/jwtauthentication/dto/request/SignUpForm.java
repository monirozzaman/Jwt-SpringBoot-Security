package com.itvillage.jwtauthentication.dto.request;

import com.itvillage.jwtauthentication.model.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SignUpForm {

    private String userId;


    private String username;

    private Set<RoleName> role;


    private String password;

}
