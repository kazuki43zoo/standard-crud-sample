package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface UserSharedService {

    User findUser(String userUuid);

    boolean isValidUserIdOnCreating(String userId);

    boolean isValidUserIdOnUpdating(String userId, String userUuid);

    void createCredential(User inputUser);

    void checkOptimisticLockingWithinLongTransaction(User storedUser, User inputUser);

    void updateUser(User storedUser, User inputUser,String beanMappingId);

    void updateCredential(User storedUser, String userId, String password);

}
