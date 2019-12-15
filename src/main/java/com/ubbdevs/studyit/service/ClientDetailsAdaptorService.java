package com.ubbdevs.studyit.service;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ClientDetailsAdaptorService {

    private final ClientDetailsService clientDetailsService;

    void validateClientId(final String clientId) throws ClientRegistrationException {
        clientDetailsService.loadClientByClientId(clientId);
    }

}
