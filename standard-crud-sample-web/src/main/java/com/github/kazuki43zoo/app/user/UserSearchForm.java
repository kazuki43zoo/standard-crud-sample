package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.core.validation.Numeric;
import com.github.kazuki43zoo.domain.model.UserStatus;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@lombok.Data
public class UserSearchForm implements Serializable {
    private static final List<UserStatus> DEFAULT_STATUS_TARGETS =
            Arrays.asList(UserStatus.INACTIVE, UserStatus.ACTIVE);

    @Size(max = 256)
    private String userId;

    @Size(max = 256)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @Size(max = 256)
    private String address;

    @Size(max = 11)
    @Numeric
    private String tel;

    @Size(max = 256)
    private String email;

    private List<UserStatus> statusTargets = DEFAULT_STATUS_TARGETS;

}
