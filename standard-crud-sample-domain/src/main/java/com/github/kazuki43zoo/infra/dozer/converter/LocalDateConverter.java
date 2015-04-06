package com.github.kazuki43zoo.infra.dozer.converter;

import org.dozer.DozerConverter;
import org.joda.time.LocalDate;

public class LocalDateConverter extends DozerConverter<LocalDate, LocalDate> {

    public LocalDateConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        // This method not called, because type of from/to is same.
        return convertFrom(source, destination);
    }

    @Override
    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        return source;
    }

}
