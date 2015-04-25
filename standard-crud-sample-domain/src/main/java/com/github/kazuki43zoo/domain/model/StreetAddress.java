package com.github.kazuki43zoo.domain.model;

import java.io.Serializable;

@lombok.Data
public class StreetAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    private String zipCode;
    private String address;
    private String addressKana;
}
