package com.github.kazuki43zoo.app;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.web.el.Functions;

@Component
public class PaginationHelper {

    public String toCriteriaQuery(Object form, Pageable pageable) {
        return Functions.query(form) + "&page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();
    }

}
