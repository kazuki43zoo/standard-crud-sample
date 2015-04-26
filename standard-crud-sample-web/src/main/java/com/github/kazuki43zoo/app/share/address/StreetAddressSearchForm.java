package com.github.kazuki43zoo.app.share.address;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@lombok.Data
public class StreetAddressSearchForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 7)
    private String zipCode;
    @Size(max = 256)
    private String address;
    @Min(0)
    @Max(200)
    private Integer size;
}
