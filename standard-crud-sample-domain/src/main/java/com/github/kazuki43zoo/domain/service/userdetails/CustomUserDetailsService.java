package com.github.kazuki43zoo.domain.service.userdetails;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findOneByUserId(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new CustomUserDetails(user, toGrantedAuthorities(user));
    }

    private List<GrantedAuthority> toGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().name()));
        });
        return authorities;
    }

}
