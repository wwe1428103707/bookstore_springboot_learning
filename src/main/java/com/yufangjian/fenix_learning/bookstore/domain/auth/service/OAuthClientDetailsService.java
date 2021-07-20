package com.yufangjian.fenix_learning.bookstore.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OAuthClientDetailsService implements ClientDetailsService {
    private static final String CLIENT_ID="bookstore_frontend";

    private static final String CLIENT_SECRET = "bookstore_secret";

    @Inject
    private PasswordEncoder passwordEncoder;

    private ClientDetailsService clientDetailsService;

    @PostConstruct
    public void init() throws Exception{
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();

        builder.withClient(CLIENT_ID).secret(passwordEncoder.encode(CLIENT_SECRET)).scopes("BROWSER").authorizedGrantTypes("password","refresh_token");
        clientDetailsService = builder.build();
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(s);
    }
}
