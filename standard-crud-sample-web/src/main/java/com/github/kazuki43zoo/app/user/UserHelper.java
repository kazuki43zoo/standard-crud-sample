package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.model.CredentialStatus;
import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.model.UserCredential;
import com.github.kazuki43zoo.domain.service.user.UserSharedService;
import org.dozer.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.util.List;

@Component
public class UserHelper {

    @Inject
    UserSharedService userSharedService;
    @Inject
    Mapper beanMapper;

    @Inject
    List<CodeList> codeLists;

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


    public User loadUserIntoModel(
            String userUuid,
            Model model) {
        User user = userSharedService.find(userUuid);
        model.addAttribute(user);
        if (user.getCredential().getStatus() == CredentialStatus.WAITING_FOR_ACTIVE) {
            model.addAttribute(ResultMessages.warning().add("w.sc.um.2008"));
        }
        return user;
    }

    public User loadUserIntoModelWithCheckingVersion(
            String userUuid,
            Model model,
            UserForm form) {
        User user = loadUserIntoModel(userUuid, model);
        if (user.getVersion() != form.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(User.class, userUuid);
        }
        if (user.getCredential().getVersion() != form.getCredentialVersion()) {
            throw new ObjectOptimisticLockingFailureException(UserCredential.class, userUuid);
        }
        return user;
    }

    public void rejectInvalidUserId(Errors errors) {
        errors.rejectValue("userId", "e.sc.um.8003");
    }


    public void bindAllCodeListIntoModel(Model model) {
        codeLists.forEach(codeList -> {
            model.addAttribute(codeList.getCodeListId(), codeList.asMap());
        });
    }

}
