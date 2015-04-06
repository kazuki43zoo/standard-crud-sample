package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.service.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ProfileHelper {

    @Inject
    UserDetailsService userDetailsService;

    @Inject
    UserService userService;

    public void updateSecurityContextByUserId(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void updateSecurityContextByUserUuid(String userUuid) {
        User user = userService.find(userUuid);
        updateSecurityContextByUserId(user.getCredential().getUserId());
    }

}
