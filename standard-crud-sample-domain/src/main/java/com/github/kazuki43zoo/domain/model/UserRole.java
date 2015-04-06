package com.github.kazuki43zoo.domain.model;

import java.io.Serializable;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userUuid;
    private Role role;
}
