package com.ubbdevs.studyit.service.oauth;

import com.ubbdevs.studyit.exception.model.RestRuntimeException;
import com.ubbdevs.studyit.model.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorizationServiceImpl implements AuthorizationService {

    public long getUserId() {
        return Long.parseLong(getDecodedDetails().get("user_id").toString());
    }

    public Role getUserRole() { return Role.valueOf(getDecodedDetails().get("user_role").toString()); }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getDecodedDetails() {
        final OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) getAuthentication().getDetails();
        return (Map<String, Object>) authenticationDetails.getDecodedDetails();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public void validateCanModifyUserWithId(final long id) {
        final long loggedInUserId = getUserId();
        if (loggedInUserId != id) {
            throw new RestRuntimeException(String.format("User with id %d cannot access user with id: %d", loggedInUserId, id));
        }
    }

}
