package com.github.kazuki43zoo.app.security;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@lombok.Data
@lombok.ToString(exclude = "password")
public class LoginForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;

    @NotNull
    private String password;

}
