package com.ubbdevs.studyit.service.oauth;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OauthAdaptorService {

    OAuth2AccessToken loginUser(String clientId, final String email, final String password);

}
