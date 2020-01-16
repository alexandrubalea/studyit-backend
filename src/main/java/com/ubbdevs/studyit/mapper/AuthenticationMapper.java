package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.AuthenticationDto;
import com.ubbdevs.studyit.model.enums.Role;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public AuthenticationDto modelToDto(final Long userId, final Role userRole, final OAuth2AccessToken accessToken) {
        return AuthenticationDto.builder()
                .userId(userId)
                .userRole(userRole)
                .accessToken(accessToken.getValue())
                .tokenType(accessToken.getTokenType())
                .scope(accessToken.getScope())
                .expiresIn(accessToken.getExpiresIn())
                .build();
    }
}
