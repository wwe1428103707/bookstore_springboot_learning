package com.yufangjian.fenix_learning.bookstore.domain.auth.provider;

import com.yufangjian.fenix_learning.bookstore.domain.auth.service.AuthenticAccountDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private AuthenticAccountDetailsService authenticAccountDetailsService;

    @Inject
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toLowerCase();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = authenticAccountDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) throw new BadCredentialsException("密码错误");

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
