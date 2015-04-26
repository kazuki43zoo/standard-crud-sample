package com.github.kazuki43zoo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@lombok.Data
@lombok.ToString(exclude = {"userId", "password"})
public class UserCredential implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private String userUuid;
    private String userId;
    @JsonIgnore
    private String password;
    private CredentialStatus status;
    private LocalDateTime lastUpdateAt;
    @JsonIgnore
    private long version;
    public void incrementVersion(){
        version++;
    }
}
