package com.yufangjian.fenix_learning.bookstore.domain.auth.service;

import com.yufangjian.fenix_learning.bookstore.domain.auth.AuthenticAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AuthenticAccountDetailsService implements UserDetailsService {

    @Inject
    private AuthenticAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return accountRepository.findByUsername(name);
    }
}
