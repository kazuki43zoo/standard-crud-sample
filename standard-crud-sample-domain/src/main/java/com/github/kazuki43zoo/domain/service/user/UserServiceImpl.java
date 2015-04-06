package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.*;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import com.github.kazuki43zoo.domain.repository.user.UserSearchCriteria;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserSharedService userSharedService;

    @Inject
    CredentialSharedService userCredentialShardService;

    @Inject
    Mapper beanMapper;

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

    public User create(User inputUser, List<Role> inputRoles) {

        // create a user
        {
            inputUser.setStatus(UserStatus.ACTIVE);
            inputUser.setVersion(0);
            userRepository.create(inputUser);
        }

        // create a user credential
        {
            userCredentialShardService.createCredential(inputUser);
        }

        // create user roles
        {
            inputRoles.forEach(inputRole -> {
                inputUser.addRole(new UserRole(inputUser.getUserUuid(), inputRole));
            });
            userRepository.createRoles(inputUser);
        }

        return inputUser;
    }

    public User update(String userUuid, User inputUser, List<Role> inputRoles) {

        // find user
        User storedUser = userSharedService.find(userUuid);

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
            UserCredential inputCredential = inputUser.getCredential();
            userCredentialShardService.updateCredential(storedUser, inputCredential.getUserId(), inputCredential.getPassword());
        }

        // update user roles
        {
            userRepository.deleteRoles(storedUser);
            storedUser.getRoles().clear();
            inputRoles.forEach(inputRole -> {
                storedUser.addRole(new UserRole(storedUser.getUserUuid(), inputRole));
            });
            userRepository.createRoles(storedUser);
        }

        return storedUser;
    }

    public User delete(String userUuid) {
        User user = userSharedService.find(userUuid);
        if (user.getStatus() != UserStatus.DELETED) {
            user.setStatus(UserStatus.DELETED);
            userRepository.update(user);
        }
        return user;
    }

}
