package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserCredential;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.dozer.Mapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.util.Optional;

@Transactional
@Service
public class ProfileServiceImpl implements ProfileService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserCredentialShardService userCredentialShardService;

    @Inject
    Mapper beanMapper;

    public User edit(String userUuid, User inputUser) {

        // find user
        User storedUser = Optional.ofNullable(userRepository.findOne(userUuid))
                .orElseThrow(() -> new ResourceNotFoundException(ResultMessages.error().add("e.sc.fw.5001")));

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
            beanMapper.map(inputUser, storedUser, "updateProfile");
            if (!userRepository.update(storedUser)) {
                throw new ObjectOptimisticLockingFailureException(User.class, userUuid);
            }
            storedUser.setVersion(storedUser.getVersion() + 1);
        }

        // update a user credential
        {
            userCredentialShardService.updateUserCredential(storedUser, inputUser);
        }

        return storedUser;
    }

}
