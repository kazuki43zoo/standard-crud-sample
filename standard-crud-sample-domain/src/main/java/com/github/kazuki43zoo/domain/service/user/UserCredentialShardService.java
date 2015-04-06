package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface UserCredentialShardService {

    boolean isValidUserIdOnCreating(String userId);

    boolean isValidUserIdOnUpdating(String userId, String userUuid);

    void createUserCredential(User inputUser);

    void updateUserCredential(User storedUser, User inputUser);

}
