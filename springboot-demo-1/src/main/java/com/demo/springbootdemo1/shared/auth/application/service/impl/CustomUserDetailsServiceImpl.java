package com.demo.springbootdemo1.shared.auth.application.service.impl;

import com.demo.springbootdemo1.shared.auth.application.service.CustomUserDetailsService;
import com.demo.springbootdemo1.shared.auth.domain.CustomUserDetails;
import com.demo.springbootdemo1.shared.user.domain.User;
import com.demo.springbootdemo1.shared.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username)
        throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword());
    }
}
