package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.model.Role;
import com.github.kazuki43zoo.domain.model.UserStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@lombok.Data
@lombok.ToString(callSuper = true)
@lombok.EqualsAndHashCode(callSuper = true)
public class UserForm extends ProfileForm {


    private static final long serialVersionUID = 1L;

    @NotNull
    private List<Role> roles;

    @NotNull(groups = Updating.class)
    private UserStatus status;

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

}
