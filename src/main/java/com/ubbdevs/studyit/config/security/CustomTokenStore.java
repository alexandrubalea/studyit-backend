package com.ubbdevs.studyit.config.security;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class CustomTokenStore extends JwtTokenStore implements TokenStore {

    private final JdbcTokenStore jdbcTokenStore;

    CustomTokenStore(final JwtAccessTokenConverter jwtTokenEnhancer, final JdbcTokenStore jdbcTokenStore) {
        super(jwtTokenEnhancer);
        this.jdbcTokenStore = jdbcTokenStore;
    }

    @Override
    public void storeRefreshToken(final OAuth2RefreshToken refreshToken, final OAuth2Authentication authentication) {
        jdbcTokenStore.storeRefreshToken(refreshToken, authentication);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(final String tokenValue) {
        return jdbcTokenStore.readRefreshToken(tokenValue);
    }

    @Override
    public void removeRefreshToken(final OAuth2RefreshToken token) {
        jdbcTokenStore.removeRefreshToken(token);
    }

}
