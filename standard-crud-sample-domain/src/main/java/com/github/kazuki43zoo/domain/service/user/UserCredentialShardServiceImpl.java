package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional
@Service
public class UserCredentialShardServiceImpl implements UserCredentialShardService {

    @Inject
    UserRepository userRepository;

    public boolean isValidUserIdOnCreating(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    public boolean isValidUserIdOnUpdating(String userId, String userUuid) {
        return !userRepository.existsByUserIdAndNotUserUuid(userId, userUuid);
    }

}
