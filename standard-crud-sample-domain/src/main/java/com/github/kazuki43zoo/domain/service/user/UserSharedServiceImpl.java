package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.util.Optional;

@Service
@Transactional
public class UserSharedServiceImpl implements UserSharedService {

    @Inject
    UserRepository userRepository;

    @Inject
    CredentialSharedService userCredentialShardService;

    @Inject
    Mapper beanMapper;


    public User find(String userUuid) {
        return get(userRepository.findOne(userUuid));
    }

    public User findByUserId(String userId) {
        return get(userRepository.findOneByUserId(userId));
    }

    private User get(User user) {
        return Optional.ofNullable(user)
                .orElseThrow(() -> new ResourceNotFoundException(ResultMessages.error().add("e.sc.fw.5001")));
    }


}
