package com.github.kazuki43zoo.domain.service.user;

import com.github.kazuki43zoo.domain.model.Role;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.repository.user.UserSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface UserService {

    Page<User> searchUsers(UserSearchCriteria criteria, Pageable pageable);

    List<User> searchUsers(UserSearchCriteria criteria);

    User create(User inputUser, List<Role> inputRoles);

    User update(String userUuid, User inputUser, List<Role> inputRoles);

    User delete(String userUuid);

}
