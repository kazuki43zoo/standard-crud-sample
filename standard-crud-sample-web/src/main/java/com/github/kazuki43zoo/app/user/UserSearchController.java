package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.domain.model.User;
import com.github.kazuki43zoo.domain.repository.user.UserSearchCriteria;
import com.github.kazuki43zoo.domain.service.user.UserService;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import javax.inject.Inject;

@Controller
@RequestMapping("users")
@TransactionTokenCheck("users")
public class UserSearchController {

    @Inject
    UserService userService;

    @Inject
    UserHelper userHelper;

    @Inject
    Mapper beanMapper;

    @ModelAttribute("userSearchForm")
    public UserSearchForm setupUserSearchForm() {
        return new UserSearchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchForm")
    public String searchForm(UserSearchForm from) {
        return "user/searchForm";
    }

    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    @RequestMapping(method = RequestMethod.GET)
    public String search(
            @Validated UserSearchForm form,
            BindingResult bindingResult,
            Pageable pageable,
            Model model) {

        if (bindingResult.hasErrors()) {
            return searchForm(form);
        }

        UserSearchCriteria criteria = beanMapper.map(form, UserSearchCriteria.class);
        Page<User> users = userService.searchUsers(criteria, pageable);

        if (users.getTotalElements() == 0) {
            model.addAttribute(ResultMessages.info().add("i.sc.um.2000"));
            return searchForm(form);
        }

        if (!users.hasContent()) {
            model.addAttribute(
                    ResultMessages.info().add("i.sc.um.2001", pageable.getPageNumber() + 1));
            return search(form, bindingResult,
                    new PageRequest(users.getTotalPages() - 1, pageable.getPageSize(), pageable.getSort()), model);
        }

        model.addAttribute("usersPage", users);
        return "user/searchResult";
    }

}
