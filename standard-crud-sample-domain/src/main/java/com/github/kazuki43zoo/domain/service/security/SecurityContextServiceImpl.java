package com.github.kazuki43zoo.domain.service.security;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.service.user.UserSharedService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional
@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    @Inject
    UserSharedService userSharedService;

    @Inject
    UserDetailsService userDetailsService;

    public void updateSecurityContextByUserId(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void updateSecurityContextByUserUuid(String userUuid) {
        User user = userSharedService.find(userUuid);
        updateSecurityContextByUserId(user.getCredential().getUserId());
    }

}
