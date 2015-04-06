package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.CredentialStatus;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserCredential;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

import javax.inject.Inject;

@Transactional
@Service
public class UserCredentialShardServiceImpl implements UserCredentialShardService {

    @Inject
    UserRepository userRepository;

    @Inject
    JodaTimeDateFactory dateFactory;

    @Inject
    PasswordEncoder passwordEncoder;


    public boolean isValidUserIdOnCreating(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    public boolean isValidUserIdOnUpdating(String userId, String userUuid) {
        return !userRepository.existsByUserIdAndNotUserUuid(userId, userUuid);
    }

    public void createUserCredential(User inputUser) {
        UserCredential credential = inputUser.getCredential();
        credential.setUserUuid(inputUser.getUserUuid());
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setStatus(CredentialStatus.WAITING_FOR_ACTIVE);
        credential.setLastUpdateAt(dateFactory.newDateTime().toLocalDateTime());
        credential.setVersion(0);
        userRepository.createCredential(inputUser);
    }

    public void updateUserCredential(User storedUser, User inputUser) {
        UserCredential storedCredential = storedUser.getCredential();
        UserCredential inputCredential = inputUser.getCredential();
        storedCredential.setUserId(inputCredential.getUserId());
        if (inputCredential.getPassword() != null) {
            storedCredential.setPassword(passwordEncoder.encode(inputCredential.getPassword()));
            storedCredential.setLastUpdateAt(dateFactory.newDateTime().toLocalDateTime());
            if (storedCredential.getStatus() == CredentialStatus.WAITING_FOR_ACTIVE) {
                storedCredential.setStatus(CredentialStatus.ACTIVE);
            }
        }
        if (!userRepository.updateCredential(storedUser)) {
            throw new ObjectOptimisticLockingFailureException(UserCredential.class, storedUser.getUserUuid());
        }
        storedCredential.setVersion(storedCredential.getVersion() + 1);
    }


}
