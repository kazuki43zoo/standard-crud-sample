package com.github.kazuki43zoo.app.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@lombok.Data
public class PasswordForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 8, max = 32)
    private String newPassword;

    @NotNull
    private String confirmNewPassword;

}
