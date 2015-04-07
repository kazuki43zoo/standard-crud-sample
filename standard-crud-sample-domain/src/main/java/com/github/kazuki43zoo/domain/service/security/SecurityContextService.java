package com.github.kazuki43zoo.domain.service.security;

public interface SecurityContextService {

    void updateSecurityContextByUserId(String userId);

    void updateSecurityContextByUserUuid(String userUuid);

}
