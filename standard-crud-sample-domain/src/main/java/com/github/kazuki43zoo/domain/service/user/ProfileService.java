package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface ProfileService {

    User edit(String userUuid, User inputUser);

}
