package com.github.kazuki43zoo.domain.model;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userUuid;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String tel;
    private String email;
    private UserStatus status;
    private UserCredential credential;
    private List<UserRole> roles = new ArrayList<>();
    private long version;

    public void addRole(UserRole role) {
        roles.add(role);
    }
}
