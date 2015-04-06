package com.github.kazuki43zoo.app.user;

public interface UserCredentialForm {
    interface Creating {
    }

    interface Updating {
    }

    String getUserId();

    String getUserUuid();

    String getPassword();

    String getConfirmPassword();
}
