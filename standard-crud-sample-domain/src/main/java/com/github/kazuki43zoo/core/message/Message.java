package com.github.kazuki43zoo.core.message;

import org.terasoluna.gfw.common.message.ResultMessageType;
import org.terasoluna.gfw.common.message.ResultMessages;

import static org.terasoluna.gfw.common.message.StandardResultMessageType.*;

public enum Message {
    // Security message
    VALID_SESSION_NOT_EXIST(
            "w.sc.se.2000", WARNING),
    USER_WAS_LOGOUT(
            "i.sc.se.1001", SUCCESS),
    LOGIN_ENCOURAGE(
            "i.sc.se.1002", INFO),
    ACCOUNT_WAS_DELETED(
            "i.sc.se.8003", INFO),
    LOGOUT_CONFIRM(
            "w.sc.se.2004", WARNING),
    // User Management message
    MATCHING_USERS_NOT_FOUND(
            "e.sc.um.2000", DANGER),
    USERS_OF_PAGE_NOT_FOUND(
            "w.sc.um.2001", WARNING),
    USER_WAS_DELETED(
            "i.sc.um.1002", SUCCESS),
    USER_ID_CAN_NOT_USE(
            "e.sc.um.8003", DANGER),
    USER_WAS_ADDED(
            "i.sc.um.1004", SUCCESS),
    USER_DELETE_CONFIRM(
            "w.sc.um.2005", WARNING),
    USER_WAS_UPDATED(
            "i.sc.um.1006", SUCCESS),
    PASSWORD_NEED_CHANGE(
            "w.sc.um.2008", WARNING),
    PROFILE_WAS_UPDATED(
            "i.sc.um.1009", SUCCESS),
    PASSWORD_CHANGE_ENCOURAGE(
            "i.sc.um.1010", INFO),
    PASSWORD_CAN_NOT_CHANGE(
            "e.sc.um.8011", DANGER),
    // Shared message
    VALIDATION_ERROR(
            "e.sc.fw.2001", DANGER),
    RESOURCE_NOT_FOUND(
            "e.sc.fw.5001", DANGER),
    OPERATION_CONFLICT(
            "e.sc.fw.8002", DANGER),
    SYSTEM_ERROR(
            "e.sc.fw.9001", DANGER);

    @lombok.Getter
    private final String code;

    @lombok.Getter
    private final ResultMessageType type;

    private Message(String code, ResultMessageType type) {
        this.code = code;
        this.type = type;
    }

    public ResultMessages resultMessages(Object... args) {
        return new ResultMessages(type).add(code, args);
    }

    public String getTypeString() {
        return type.getType().toLowerCase();
    }

}
