package com.yufangjian.fenix_learning.bookstore.domain.auth;

import com.yufangjian.fenix_learning.bookstore.domain.account.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class AuthenticAccount extends Account implements UserDetails {



    public AuthenticAccount(){
        super();
        authorities.add(new SimpleGrantedAuthority(Role.USER));
    }

    public AuthenticAccount(Account origin){
        this();
        BeanUtils.copyProperties(origin, this);
        if(getId() == 1){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN));
        }
    }

//    private Collection<GrantedAuthority> authorities = new HashSet<>()

    private Collection<GrantedAuthority> authorities = new HashSet<>();

    public void setAuthorities(Collection<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
