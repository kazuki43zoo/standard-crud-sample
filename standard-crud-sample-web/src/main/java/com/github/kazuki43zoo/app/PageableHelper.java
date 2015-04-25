package com.github.kazuki43zoo.app;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableHelper {

    public String toQuery(Pageable pageable) {
        return "page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
    }

}
