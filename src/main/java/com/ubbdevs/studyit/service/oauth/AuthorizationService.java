package com.ubbdevs.studyit.service.oauth;

public interface AuthorizationService {

    long getUserId();
    void validateCanModifyUserWithId(long id);

}
