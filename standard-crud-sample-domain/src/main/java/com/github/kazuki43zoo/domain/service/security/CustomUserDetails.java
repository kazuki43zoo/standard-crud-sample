package com.github.kazuki43zoo.domain.service.security;

import com.github.kazuki43zoo.domain.model.CredentialStatus;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserStatus;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(
                user.getCredential().getUserId(),
                user.getCredential().getPassword(),
                user.getStatus() == UserStatus.ACTIVE,
                true,
                user.getCredential().getStatus() == CredentialStatus.ACTIVE,
                true,
                authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
