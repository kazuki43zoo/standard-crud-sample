package com.github.kazuki43zoo.app.user;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class UserSearchHelper {

    public void takeOverSearchCriteriaOnRedirect(
            RedirectAttributes redirectAttributes,
            UserSearchForm form,
            Pageable pageable) {
        redirectAttributes.addAttribute("userId", form.getUserId());
        redirectAttributes.addAttribute("name", form.getName());
        if (form.getDateOfBirth() != null) {
            redirectAttributes.addAttribute("dateOfBirth",
                    form.getDateOfBirth().toString("yyyy-MM-dd"));
        }
        redirectAttributes.addAttribute("address", form.getAddress());
        redirectAttributes.addAttribute("tel", form.getTel());
        redirectAttributes.addAttribute("email", form.getEmail());
        if (form.getStatusTargets() != null) {
            redirectAttributes.addAttribute("statusTargets",
                    StringUtils.arrayToCommaDelimitedString(form.getStatusTargets().toArray()));
        }
        redirectAttributes.addAttribute("page", pageable.getPageNumber());
        redirectAttributes.addAttribute("size", pageable.getPageSize());
    }

}
