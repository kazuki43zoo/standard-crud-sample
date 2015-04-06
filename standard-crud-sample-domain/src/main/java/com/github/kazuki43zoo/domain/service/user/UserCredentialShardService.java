package com.github.kazuki43zoo.domain.service.user;

public interface UserCredentialShardService {

    boolean isValidUserIdOnCreating(String userId);

    boolean isValidUserIdOnUpdating(String userId, String userUuid);

}
