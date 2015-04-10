package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface UserSharedService {

    User findUser(String userUuid);

    User findUserByUserId(String userId);

    boolean isValidUserIdOnCreating(String userId);

    boolean isValidUserIdOnUpdating(String userId, String userUuid);

    void createCredential(User inputUser);

    void checkOptimisticLockingWithinLongTransaction(User storedUser, User inputUser);

    void updateUser(User storedUser, User inputUser);


    void updateCredential(User storedUser, String userId, String password);


}
