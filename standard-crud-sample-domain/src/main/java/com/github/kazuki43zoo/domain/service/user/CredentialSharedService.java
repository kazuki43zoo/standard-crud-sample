package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface CredentialSharedService {

    boolean isValidUserIdOnCreating(String userId);

    boolean isValidUserIdOnUpdating(String userId, String userUuid);

    void createCredential(User inputUser);

    void updateCredential(User storedUser, String userId, String password);

}
