package com.redhat.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.redhat.service.GenericManager;
import com.redhat.model.Vendor;
import com.redhat.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/vendorform*")
public class VendorFormController extends BaseFormController {
    private GenericManager<Vendor, Long> vendorManager = null;

    @Autowired
    public void setVendorManager(@Qualifier("vendorManager") GenericManager<Vendor, Long> vendorManager) {
        this.vendorManager = vendorManager;
    }

    public VendorFormController() {
        setCancelView("redirect:vendors");
        setSuccessView("redirect:vendors");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Vendor showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return vendorManager.get(new Long(id));
        }

        return new Vendor();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Vendor vendor, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(vendor, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "vendorform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (vendor.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            vendorManager.remove(vendor.getId());
            saveMessage(request, getText("vendor.deleted", locale));
        } else {
            vendorManager.save(vendor);
            String key = (isNew) ? "vendor.added" : "vendor.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:vendorform?id=" + vendor.getId();
            }
        }

        return success;
    }
}
