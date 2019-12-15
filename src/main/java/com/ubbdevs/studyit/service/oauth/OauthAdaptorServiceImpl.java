package com.ubbdevs.studyit.service.oauth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class OauthAdaptorServiceImpl implements OauthAdaptorService {

    private static final String CLIENT_ID_FIELD = "client_id";
    private static final String DEFAULT_PASSWORD = "";
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String GRANT_TYPE_FIELD = "grant_type";
    private static final String GRANT_TYPE_PASSWORD = "password";

    private final TokenEndpoint tokenEndpoint;

    public OAuth2AccessToken loginUser(final String clientId, final String email, final String password) {
        final User user = new User(clientId, DEFAULT_PASSWORD, Collections.emptyList());
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                null, Collections.emptyList());
        return createAccessToken(clientId, authenticationToken, email, password);
    }

    private OAuth2AccessToken createAccessToken(final String clientId,
                                                final UsernamePasswordAuthenticationToken authenticationToken,
                                                final String email, final String password) {
        try {
            final ResponseEntity<OAuth2AccessToken> accessToken = tokenEndpoint.postAccessToken(authenticationToken,
                    getAuthenticationParameters(clientId, email, password));
            return accessToken.getBody();
        } catch (final HttpRequestMethodNotSupportedException e) {
            throw new RuntimeException("Exception occurred while generating access token!", e);
        }
    }

    private Map<String, String> getAuthenticationParameters(final String clientId, final String email,
                                                            final String password) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CLIENT_ID_FIELD, clientId);
        parameters.put(USERNAME_FIELD, email);
        parameters.put(PASSWORD_FIELD, password);
        parameters.put(GRANT_TYPE_FIELD, GRANT_TYPE_PASSWORD);
        return parameters;
    }

}
