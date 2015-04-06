package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.core.validation.Numeric;
import com.github.kazuki43zoo.domain.model.Role;
import com.github.kazuki43zoo.domain.model.UserStatus;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@lombok.Data
@lombok.ToString(exclude = {"password", "confirmationPassword"})
public class UserForm implements Serializable {

    interface Creating {
    }

    interface Updating {
    }

    @NotNull
    @Size(max = 256, groups = Creating.class)
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
    private String email;

    @NotNull(groups = Creating.class)
    @Size(min = 8, max = 32)
    private String password;

    @NotNull(groups = Creating.class)
    private String confirmationPassword;

    @NotNull
    private List<Role> roles;

    @NotNull(groups = Updating.class)
    private UserStatus status;

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
