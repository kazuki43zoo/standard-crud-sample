package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.core.message.Message;
import com.github.kazuki43zoo.domain.model.*;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import com.github.kazuki43zoo.domain.repository.user.UserSearchCriteria;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;

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
    Mapper beanMapper;

    @Value("${user.limitCount:2000}")
    int limitCount;

    @Transactional(readOnly = true)
    public Page<User> searchUsers(UserSearchCriteria criteria, Pageable pageable) {
        long count = userRepository.countByCriteria(criteria);
        List<User> users;
        if (0 < count) {
            users = userRepository.findPageByCriteria(criteria, pageable);
        } else {
            users = Collections.emptyList();
        }
        return new PageImpl<>(users, pageable, count);
    }

    @Transactional(readOnly = true)
    public List<User> searchUsers(UserSearchCriteria criteria) {
        long count = userRepository.countByCriteria(criteria);
        if (limitCount < count) {
            throw new BusinessException(
                    Message.MATCHING_DATA_LIMIT_OVER.resultMessages(count, limitCount));
        }
        return userRepository.findAllByCriteria(criteria, limitCount);
    }

    public User create(User inputUser, List<Role> inputRoles) {

        inputUser.setStatus(UserStatus.ACTIVE);
        userRepository.create(inputUser);

        userSharedService.createCredential(inputUser);

        inputRoles.forEach(inputRole -> {
            inputUser.addRole(new UserRole(inputUser.getUserUuid(), inputRole));
        });
        userRepository.createRoles(inputUser);

        return inputUser;
    }

    public User update(String userUuid, User inputUser, List<Role> inputRoles) {

        User storedUser = userSharedService.findUser(userUuid);

        userSharedService.checkOptimisticLockingWithinLongTransaction(storedUser, inputUser);

        userSharedService.updateUser(storedUser, inputUser, "updateUser");

        UserCredential inputCredential = inputUser.getCredential();
        userSharedService.updateCredential(
                storedUser, inputCredential.getUserId(), inputCredential.getPassword());

        userRepository.deleteRoles(storedUser);
        storedUser.getRoles().clear();
        inputRoles.forEach(inputRole -> {
            storedUser.addRole(new UserRole(storedUser.getUserUuid(), inputRole));
        });
        userRepository.createRoles(storedUser);

        return storedUser;
    }

    public User delete(String userUuid) {
        User user = userSharedService.findUser(userUuid);
        if (user.getStatus() != UserStatus.DELETED) {
            user.setStatus(UserStatus.DELETED);
            userRepository.update(user);
        }
        return user;
    }

}
