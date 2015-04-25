package com.github.kazuki43zoo.app.user;

import com.github.kazuki43zoo.app.PageableHelper;
import com.github.kazuki43zoo.app.flow.DefaultFlow;
import com.github.kazuki43zoo.app.flow.Flow;
import com.github.kazuki43zoo.app.flow.FlowHelper;
import com.github.kazuki43zoo.domain.model.StreetAddress;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.el.Functions;
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

    @Inject
    FlowHelper flowHelper;

    @Inject
    PageableHelper pageableHelper;

    @ModelAttribute
    public UserSearchForm setupUserSearchForm() {
        return new UserSearchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchForm")
    public String searchForm() {
        return "user/searchForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "clearSearchForm")
    public String clearForm(Model model) {
        model.addAttribute(setupUserSearchForm());
        return searchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "gotoAddressSearch")
    public String gotoAddressSearch(
            UserSearchForm form,
            Model model,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        currentFlow.saveModel(model);
        DefaultFlow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users?applyAddress&destination=searchForm")
                .cancelPath("/users?searchRedo")
                .build();
        return flowHelper.redirectAndBeginFlow(
                "/share/streetAddresses?searchForm", newFlow, redirectAttributes);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"applyAddress", "destination=searchForm"})
    public String applyAddress(
            UserSearchForm form,
            StreetAddress selectedStreetAddress) {
        form.setAddress(selectedStreetAddress.getAddress());
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
            return searchForm();
        }

        UserSearchCriteria criteria = beanMapper.map(form, UserSearchCriteria.class);
        Page<User> users = userService.searchUsers(criteria, pageable);

        if (users.getTotalElements() == 0) {
            model.addAttribute(ResultMessages.info().add("i.sc.um.2000"));
            return searchForm();
        }

        if (!users.hasContent()) {
            model.addAttribute(
                    ResultMessages.info().add("i.sc.um.2001", pageable.getPageNumber() + 1));
            return search(form, bindingResult,
                    new PageRequest(users.getTotalPages() - 1, pageable.getPageSize(), pageable.getSort()), model);
        }

        model.addAttribute("page", users);
        return "user/searchResult";
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchRedo")
    public String searchRedo(UserSearchForm form) {
        return searchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "gotoCreateForm")
    public String gotoCreateForm(
            UserSearchForm form,
            Pageable pageable,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users?" + Functions.query(form) + "&" + pageableHelper.toQuery(pageable))
                .build();
        return flowHelper.redirectAndBeginFlow(
                "/users?createForm", newFlow, redirectAttributes);
    }

    @RequestMapping(method = RequestMethod.GET, params = "gotoUpdateForm")
    public String gotoUpdateForm(
            @RequestParam("userUuid") String userUuid,
            UserSearchForm form,
            Pageable pageable,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users?" + Functions.query(form) + "&" + pageableHelper.toQuery(pageable))
                .build();
        redirectAttributes.addAttribute("userUuid", userUuid);
        return flowHelper.redirectAndBeginFlow(
                "/users/{userUuid}?updateForm", newFlow, redirectAttributes);
    }


    @RequestMapping(method = RequestMethod.POST, params = "gotoDelete")
    public String gotoDelete(
            @RequestParam("userUuid") String userUuid,
            UserSearchForm form,
            Pageable pageable,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            Model model) {
        Flow newFlow = DefaultFlow.builder(currentFlow)
                .finishPath("/users?" + Functions.query(form) + "&" + pageableHelper.toQuery(pageable))
                .build();
        return flowHelper.forwardAndBeginFlow(
                UriComponentsBuilder.fromPath("/users/{userUuid}").buildAndExpand(userUuid).toString(),
                newFlow,
                model);
    }


}
