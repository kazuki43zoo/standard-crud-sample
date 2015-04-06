package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface UserSharedService {

    User find(String userUuid);

    User findByUserId(String userId);

}
