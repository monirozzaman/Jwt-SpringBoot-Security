package com.itvillage.jwtauthentication.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ResetPasswordForm {

    @NotBlank
    @Size(min = 4, max = 40)
    private String password;

}
