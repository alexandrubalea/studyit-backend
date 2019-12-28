package com.ubbdevs.studyit.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String signingKey;

    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(@Value("${authorization.server.signing.key}") final String signingKey,
                                     final AuthenticationManager authenticationManager, final DataSource dataSource,
                                     final UserDetailsService userDetailsService) {
        this.signingKey = signingKey;
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .jdbc(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.reuseRefreshTokens(false)
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .pathMapping("/oauth/token", "/users/token");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = getAccessTokenConverter();
        converter.setSigningKey(signingKey);
        converter.setAccessTokenConverter(new JwtConverter());
        return converter;
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer configurer) {
        configurer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        final FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    private JwtAccessTokenConverter getAccessTokenConverter() {
        return new JwtAccessTokenConverter() {

            /**
             * Add custom details to JWT access token
             * @param accessToken default access token
             * @param authentication object
             * @return New access token that contains also custom user details
             */
            @Override
            public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
                final Principal user = (Principal) authentication.getUserAuthentication().getPrincipal();
                final Map<String, Object> additionalInfo = new HashMap<>();
                additionalInfo.put("user_id", user.getUser().getId());
                additionalInfo.put("user_role", user.getUser().getRole().toString());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
                return super.enhance(accessToken, authentication);
            }

        };
    }

    private static class JwtConverter extends DefaultAccessTokenConverter {

        /**
         * @param map that contains JWT details
         * @return Authentication containing JWT details
         */
        @Override
        public OAuth2Authentication extractAuthentication(final Map<String, ?> map) {
            final OAuth2Authentication auth = super.extractAuthentication(map);
            auth.setDetails(map);
            return auth;
        }

    }

}
