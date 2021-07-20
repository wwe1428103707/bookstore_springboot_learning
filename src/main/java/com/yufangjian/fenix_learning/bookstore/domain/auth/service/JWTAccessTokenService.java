package com.yufangjian.fenix_learning.bookstore.domain.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JWTAccessTokenService extends DefaultTokenServices {
    @Inject
    public JWTAccessTokenService(JWTAccessToken token, OAuthClientDetailsService clientDetailsService, AuthenticationManager authenticationManager){
        setTokenStore(new JwtTokenStore(token));

        setClientDetailsService(clientDetailsService);

        setAuthenticationManager(authenticationManager);

        setTokenEnhancer(token);

        setAccessTokenValiditySeconds(60*60*3);
        setRefreshTokenValiditySeconds(60*60*24*15);
        setSupportRefreshToken(true);
        setReuseRefreshToken(true);
    }
}
