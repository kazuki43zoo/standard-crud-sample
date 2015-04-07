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
    CredentialSharedService credentialShardService;

    @Inject
    PasswordEncoder passwordEncoder;

    public void change(String userId, String currentPassword, String newPassword) {
        Optional<User> storedUser = Optional.ofNullable(userRepository.findOneByUserId(userId));
        if (!storedUser.isPresent()
                || !passwordEncoder.matches(currentPassword, storedUser.get().getCredential().getPassword())) {
            throw new BusinessException(ResultMessages.danger().add("e.sc.um.8011"));
        }
        credentialShardService.updateCredential(storedUser.get(), userId, newPassword);
    }

}
