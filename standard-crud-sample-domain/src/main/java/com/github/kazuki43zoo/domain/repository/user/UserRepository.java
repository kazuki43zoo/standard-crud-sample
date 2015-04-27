package com.github.kazuki43zoo.domain.repository.user;

import com.github.kazuki43zoo.domain.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository {

    long countByCriteria(
            @Param("criteria") UserSearchCriteria criteria);

    List<User> findPageByCriteria(
            @Param("criteria") UserSearchCriteria criteria,
            @Param("pageable") Pageable pageable);
    List<User> findAllByCriteria(
            @Param("criteria") UserSearchCriteria criteria,
            @Param("pageSize") int pageSize);

    User findOne(String userUuid);

    User findOneByUserId(String userId);

    void create(User user);

    void createCredential(User user);

    void createRoles(User user);

    boolean update(User user);

    boolean updateCredential(User user);

    boolean deleteRoles(User user);

    boolean existsByUserId(String userId);

    boolean existsByUserIdAndNotUserUuid(
            @Param("userId") String userId,
            @Param("userUuid") String userUuid);
}
