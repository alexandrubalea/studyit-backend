package com.ubbdevs.studyit.service.oauth;

import com.ubbdevs.studyit.model.enums.Role;

public interface AuthorizationService {

    long getUserId();
    Role getUserRole();
    void validateCanModifyUserWithId(long id);

}
