package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserCredential;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional
@Service
public class ProfileServiceImpl implements ProfileService {

    @Inject
    UserSharedService userSharedService;

    @Inject
    Mapper beanMapper;

    public User edit(String userUuid, User inputUser) {

        User storedUser = userSharedService.findUser(userUuid);

        userSharedService.checkOptimisticLockingWithinLongTransaction(storedUser, inputUser);

        userSharedService.updateUser(storedUser, inputUser, "updateProfile");

        UserCredential inputCredential = inputUser.getCredential();
        userSharedService.updateCredential(
                storedUser, inputCredential.getUserId(), inputCredential.getPassword());

        return storedUser;
    }

}
