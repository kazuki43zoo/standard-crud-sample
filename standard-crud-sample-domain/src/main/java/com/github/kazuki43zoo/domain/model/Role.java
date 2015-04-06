package com.github.kazuki43zoo.domain.model;

import org.terasoluna.gfw.common.codelist.EnumCodeList;

public enum Role implements EnumCodeList.CodeListItem {

    USER("利用者"), ADMIN("管理者");

    private final String label;

    private Role(String label) {
        this.label = label;
    }

    public String getCodeValue() {
        return name();
    }

    public String getCodeLabel() {
        return label;
    }
}
