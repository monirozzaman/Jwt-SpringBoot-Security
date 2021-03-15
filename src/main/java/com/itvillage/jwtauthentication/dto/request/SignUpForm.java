package com.itvillage.jwtauthentication.dto.request;

import com.itvillage.jwtauthentication.model.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String userId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    private Set<RoleName> role;

    @NotBlank
    @Size(min = 4, max = 40)
    private String password;

}
