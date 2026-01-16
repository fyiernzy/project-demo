package com.demo.springbootdemo1.shared.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final boolean enabled = true;
    private final boolean accountNonExpired = true;
    private final boolean credentialsNonExpired = true;
    private final boolean accountNonLocked = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of();
    }
}
