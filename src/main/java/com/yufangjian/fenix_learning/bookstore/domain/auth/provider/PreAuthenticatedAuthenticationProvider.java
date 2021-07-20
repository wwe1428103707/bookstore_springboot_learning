package com.yufangjian.fenix_learning.bookstore.domain.auth.provider;

import com.yufangjian.fenix_learning.bookstore.domain.auth.AuthenticAccount;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.inject.Named;

@Named
public class PreAuthenticatedAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal() instanceof UsernamePasswordAuthenticationToken){
            AuthenticAccount user = (AuthenticAccount) ((UsernamePasswordAuthenticationToken) authentication.getPrincipal()).getPrincipal();
            if (user.isEnabled() && user.isCredentialsNonExpired() && user.isCredentialsNonExpired()){
                return new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
            }else{
                throw new DisabledException("用户状态出错");
            }
        }
        else{
            throw new PreAuthenticatedCredentialsNotFoundException("令牌有问题");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
