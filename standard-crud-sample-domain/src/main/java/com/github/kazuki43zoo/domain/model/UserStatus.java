package com.github.kazuki43zoo.domain.model;

import org.terasoluna.gfw.common.codelist.EnumCodeList;

public enum UserStatus implements EnumCodeList.CodeListItem {

    INACTIVE("無効"), ACTIVE("有効"), DELETED("削除済み");

    private final String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public String getCodeValue() {
        return name();
    }

    public String getCodeLabel() {
        return label;
    }
}
