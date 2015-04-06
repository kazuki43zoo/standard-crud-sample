package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;

public interface CredentialService {

    void change(String userId, String password, String newPassword);

}
