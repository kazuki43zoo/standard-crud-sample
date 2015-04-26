package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.core.message.Message;
import com.github.kazuki43zoo.domain.model.CredentialStatus;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserCredential;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.dozer.Mapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class UserSharedServiceImpl implements UserSharedService {

    @Inject
    UserRepository userRepository;

    @Inject
    JodaTimeDateFactory dateFactory;

    @Inject
    Mapper beanMapper;

    @Inject
    PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findUser(String userUuid) {
        return Optional.ofNullable(userRepository.findOne(userUuid))
                .orElseThrow(() -> new ResourceNotFoundException(Message.RESOURCE_NOT_FOUND.resultMessages()));
    }

    @Transactional(readOnly = true)
    public boolean isValidUserIdOnCreating(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public boolean isValidUserIdOnUpdating(String userId, String userUuid) {
        return !userRepository.existsByUserIdAndNotUserUuid(userId, userUuid);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void checkOptimisticLockingWithinLongTransaction(User storedUser, User inputUser) {
        if (storedUser.getVersion() != inputUser.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(
                    User.class, storedUser.getUserUuid());
        }
        if (storedUser.getCredential().getVersion() != inputUser.getCredential().getVersion()) {
            throw new ObjectOptimisticLockingFailureException(
                    UserCredential.class, storedUser.getUserUuid());
        }
    }

    public void updateUser(User storedUser, User inputUser, String beanMappingId) {
        beanMapper.map(inputUser, storedUser, beanMappingId);
        if (!userRepository.update(storedUser)) {
            throw new ObjectOptimisticLockingFailureException(
                    User.class, storedUser.getUserUuid());
        }
        storedUser.incrementVersion();
    }

    public void createCredential(User inputUser) {
        UserCredential credential = inputUser.getCredential();
        credential.setUserUuid(inputUser.getUserUuid());
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setStatus(CredentialStatus.WAITING_FOR_ACTIVE);
        credential.setLastUpdateAt(dateFactory.newDateTime().toLocalDateTime());
        userRepository.createCredential(inputUser);
    }

    public void updateCredential(User storedUser, String userId, String password) {
        UserCredential storedCredential = storedUser.getCredential();
        storedCredential.setUserId(userId);
        Optional.ofNullable(password).ifPresent(value -> {
            storedCredential.setPassword(passwordEncoder.encode(value));
            storedCredential.setLastUpdateAt(dateFactory.newDateTime().toLocalDateTime());
            if (storedCredential.getStatus() == CredentialStatus.WAITING_FOR_ACTIVE) {
                storedCredential.setStatus(CredentialStatus.ACTIVE);
            }
        });
        if (!userRepository.updateCredential(storedUser)) {
            throw new ObjectOptimisticLockingFailureException(
                    UserCredential.class, storedUser.getUserUuid());
        }
        storedCredential.incrementVersion();
    }

}
