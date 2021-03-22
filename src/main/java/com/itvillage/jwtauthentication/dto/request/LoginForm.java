package com.itvillage.jwtauthentication.dto.request;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LoginForm {

    private String username;

    private String password;

}
