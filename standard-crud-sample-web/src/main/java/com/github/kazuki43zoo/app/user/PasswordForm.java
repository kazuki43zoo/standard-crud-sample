package com.github.kazuki43zoo.app.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@lombok.Data
@lombok.ToString(of = "userId")
public class PasswordForm implements ConfirmPasswordContainer, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;

    @NotNull
    private String currentPassword;

    @NotNull
    @Size(min = 8, max = 32)
    private String password;

    @NotNull
    private String confirmPassword;

}
