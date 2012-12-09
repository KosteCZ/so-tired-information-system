package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Michal Toth
 */

@UrlBinding("/customer/{$event}/")
public class CustomerActionBean implements ActionBean {

    private final static Logger log = LoggerFactory.getLogger(CustomerActionBean.class);
    private ActionBeanContext context;
    private List<CustomerTO> foundList;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "firstName", required = true),
        @Validate(on = {"add", "save"}, field = "lastName", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true)
    })
    private CustomerTO cto;

    @SpringBean
    protected CustomerService customerService;

    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/customer/list.jsp");
    }

    public List<CustomerTO> getCustomers() {
        return customerService.findAll();
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public CustomerTO getCto() {
        return cto;
    }

    public void setCto(CustomerTO cto) {
        this.cto = cto;
    }

    public Resolution add() {
        log.debug("newcustomer() cto={}", cto);
        customerService.create(cto);
        return new RedirectResolution(this.getClass(), "all");
        
    }
    
    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/customer/create.jsp");
    }

    public Resolution delete() {
        Long id = Long.parseLong(context.getRequest().getParameter("cto.id"));
        cto = customerService.get(id);
        log.debug("deleteCustomer() cto={}", cto);
        customerService.remove(cto);
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadCustomerFromDatabase() {
        String ids = context.getRequest().getParameter("cto.id");
        if (ids == null) {
            return;
        }
        cto = customerService.get(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() cto={}", cto);
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() cto={}", cto);
        customerService.update(cto);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution findByName() {
        log.debug("findByName() ");
        String fn = context.getRequest().getParameter("firstname");
        String ln = context.getRequest().getParameter("lastname");

        if (!fn.equals("") && !ln.equals("")) {
            foundList = customerService.findByName(fn, ln);
        } else if (ln.equals("")) {
            foundList = customerService.findByName(fn, null);
        } else if (fn.equals("")) {
            foundList = customerService.findByName(null, ln);
        } else {
            return new RedirectResolution(this.getClass(), "all");
        }
        return new ForwardResolution("/customer/resultlist.jsp");
    }

    /**
     * returns list of found customers
     */
    public List<CustomerTO> getFoundList() {
        return foundList;
    }
}