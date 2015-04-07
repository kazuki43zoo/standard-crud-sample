package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.core.validation.Numeric;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@lombok.Data
@lombok.ToString(exclude = {"password", "confirmPassword"})
public class ProfileForm implements ConfirmPasswordContainer, Serializable {

    interface Creating {
    }

    interface Updating {
    }

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 256)
    private String userId;

    @NotNull
    @Size(max = 256)
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @Size(max = 256)
    private String address;

    @Size(min = 10, max = 11)
    @Numeric
    private String tel;

    @NotNull
    @Size(max = 256)
    @Email
    private String email;

    @NotNull(groups = Creating.class)
    @Size(min = 8, max = 32)
    private String password;

    @NotNull(groups = Creating.class)
    private String confirmPassword;

    @Null(groups = Creating.class)
    @NotNull(groups = Updating.class)
    private Long version;

    @Null(groups = Creating.class)
    @NotNull(groups = Updating.class)
    private Long credentialVersion;

    @Null(groups = Creating.class)
    @NotNull(groups = Updating.class)
    private String userUuid;

}
