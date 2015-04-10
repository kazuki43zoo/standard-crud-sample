package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.util.Optional;

@Transactional
@Service
public class CredentialServiceImpl implements CredentialService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserSharedService userSharedService;

    @Inject
    PasswordEncoder passwordEncoder;

    public void change(String userId, String currentPassword, String newPassword) {
        User user = Optional.ofNullable(userRepository.findOneByUserId(userId))
                .filter(entity -> passwordEncoder.matches(currentPassword, entity.getCredential().getPassword()))
                .orElseThrow(() -> new BusinessException(ResultMessages.danger().add("e.sc.um.8011")));
        userSharedService.updateCredential(user, userId, newPassword);
    }

}
