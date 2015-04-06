package com.github.kazuki43zoo.domain.repository.user;

import com.github.kazuki43zoo.domain.model.UserStatus;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.List;

@lombok.Data
public class UserSearchCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String tel;
    private String email;
    private List<UserStatus> statusTargets;
}
