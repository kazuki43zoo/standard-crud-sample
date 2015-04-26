package com.github.kazuki43zoo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private String userUuid;
    private Role role;
}
