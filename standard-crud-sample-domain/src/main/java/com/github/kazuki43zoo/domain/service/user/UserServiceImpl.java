package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.*;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import com.github.kazuki43zoo.domain.repository.user.UserSearchCriteria;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    JodaTimeDateFactory dateFactory;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    Mapper beanMapper;

    @Override
    public Page<User> searchUsers(UserSearchCriteria criteria, Pageable pageable) {
        long count = userRepository.countByCriteria(criteria);
        List<User> users;
        if (0 < count) {
            users = userRepository.findPageByCriteria(criteria, pageable);
        } else {
            users = Collections.emptyList();
        }
        return new PageImpl<User>(users, pageable, count);
    }

    @Override
    public User find(String userUuid) {
        User user = userRepository.findOne(userUuid);
        if (user == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add("e.sc.fw.5001"));
        }
        return user;
    }

    @Override
    public User create(User inputUser, List<Role> inputRoles) {

        // create a user
        {
            inputUser.setStatus(UserStatus.ACTIVE);
            inputUser.setVersion(0);
            userRepository.create(inputUser);
        }

        // create a user credential
        {
            UserCredential credential = inputUser.getCredential();
            credential.setUserUuid(inputUser.getUserUuid());
            credential.setPassword(passwordEncoder.encode(credential.getPassword()));
            credential.setStatus(CredentialStatus.WAITING_FOR_ACTIVE);
            credential.setLastUpdateAt(dateFactory.newDateTime().toLocalDateTime());
            credential.setVersion(0);
            userRepository.createCredential(inputUser);
        }

        // create user roles
        {
            inputRoles.forEach(inputRole -> inputUser.addRole(new UserRole(inputUser.getUserUuid(), inputRole)));
            userRepository.createRoles(inputUser);
        }

        return inputUser;
    }

    @Override
    public User update(String userUuid, User inputUser, List<Role> inputRoles) {

        // find user
        User storedUser = find(userUuid);

        // check a optimistic locking within long transaction
        {
            if (storedUser.getVersion() != inputUser.getVersion()) {
                throw new ObjectOptimisticLockingFailureException(User.class, userUuid);
            }
            if (storedUser.getCredential().getVersion() != inputUser.getCredential().getVersion()) {
                throw new ObjectOptimisticLockingFailureException(UserCredential.class, userUuid);
            }
        }

        // update a user
        {
            beanMapper.map(inputUser, storedUser, "updateUser");
            if (!userRepository.update(storedUser)) {
                throw new ObjectOptimisticLockingFailureException(User.class, userUuid);
            }
            storedUser.setVersion(storedUser.getVersion() + 1);
        }

        // update a user credential
        {
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
                throw new ObjectOptimisticLockingFailureException(UserCredential.class, userUuid);
            }
            storedCredential.setVersion(storedCredential.getVersion() + 1);
        }

        // update user roles
        {
            userRepository.deleteRoles(storedUser);
            storedUser.getRoles().clear();
            inputRoles.forEach(inputRole -> storedUser.addRole(new UserRole(storedUser.getUserUuid(), inputRole)));
            userRepository.createRoles(storedUser);
        }

        return storedUser;
    }

    @Override
    public User delete(String userUuid) {
        User user = find(userUuid);
        if (user != null && user.getStatus() != UserStatus.DELETED) {
            user.setStatus(UserStatus.DELETED);
            userRepository.update(user);
        }
        return user;
    }

}
