package com.github.kazuki43zoo.app.share.address;

import com.github.kazuki43zoo.app.flow.DefaultFlow;
import com.github.kazuki43zoo.app.flow.Flow;
import com.github.kazuki43zoo.domain.model.StreetAddress;
import com.github.kazuki43zoo.domain.repository.address.StreetAddressSearchCriteria;
import com.github.kazuki43zoo.domain.service.address.StreetAddressService;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("share/streetAddresses")
public class StreetAddressSearchController {

    @Inject
    StreetAddressService streetAddressService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public StreetAddressSearchForm setupSearchForm() {
        return new StreetAddressSearchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchForm")
    public String searchForm() {
        return "share/streetAddress/searchForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchRedo")
    public String searchRedo(StreetAddressSearchForm form) {
        return searchForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(
            @Validated StreetAddressSearchForm form,
            BindingResult bindingResult,
            Pageable pageable,
            Model model) {
        if (bindingResult.hasErrors()) {
            return searchForm();
        }
        StreetAddressSearchCriteria criteria = beanMapper.map(form, StreetAddressSearchCriteria.class);
        Page<StreetAddress> page = streetAddressService.search(criteria, pageable);
        model.addAttribute("page", page);
        return "share/streetAddress/searchResult";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String select(
            StreetAddress streetAddress,
            @ModelAttribute(Flow.MODEL_NAME) DefaultFlow currentFlow,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(streetAddress);
        return "redirect:" + currentFlow.getFinishPath();
    }

}
