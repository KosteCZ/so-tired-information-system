package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import cz.muni.fi.pa165.stis.service.CustomerService;
import java.util.List;
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
public class CustomerActionBean extends BaseActionBean {

    private final static Logger log = LoggerFactory.getLogger(CustomerActionBean.class);
    private List<CustomerTO> foundList;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "firstName", required = true),
        @Validate(on = {"add", "save"}, field = "lastName", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true)
    })
    private CustomerTO cto;
    @ValidateNestedProperties(value = {
        @Validate(on = {"save"}, field = "password", required = true, minlength = 4)
    })
    private UserTO uto;
    @Validate(on = {"save"}, field = "password2", required = true, minlength = 4)
    private String password2;
    
    @SpringBean
    protected CustomerService customerService;
    @SpringBean
    protected CustomerUserFacade cuFacade;

    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/customer/list.jsp");
    }

    public List<CustomerTO> getCustomers() {
        return customerService.findAll();
    }
    
    public CustomerTO getCto() {
        return cto;
    }

    public void setCto(CustomerTO cto) {
        this.cto = cto;
    }

    public UserTO getUto() {
        return uto;
    }

    public void setUto(UserTO uto) {
        this.uto = uto;
    }
    
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password) {
        this.password2 = password;
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
        Long id = Long.parseLong(getContext().getRequest().getParameter("cto.id"));
        cto = customerService.get(id);
        log.debug("deleteCustomer() cto={}", cto);
        cuFacade.remove(cuFacade.getByCustomerId(id));
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadCustomerFromDatabase() {
        log.debug("load() \ncto={} \nuto={}", cto, uto);
        if (getContext().getRequest().getParameter("id") == null) {
            return;
        }
        Long id = Long.parseLong(getContext().getRequest().getParameter("cto.id"));
        CustomerUserTO cuto = cuFacade.getByCustomerId(id);
        cto = cuto.getCustomer();
        uto = cuto.getUser();

        log.debug("load() \ncuto={} \ncto={} \nuto={}", cuto, cto, uto);
        //cto = customerService.get(Long.parseLong(ids));
        //uto = cto.getUser();
    }

    public Resolution edit() {
        log.debug("edit() getCto={} \ngetUto={}", this.getCto(), this.getUto());
        return new ForwardResolution("/customer/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() cto={} \nuto={}", this.getCto(), this.getUto());
        log.debug("save() cto={} \nuto={}", cto, uto);
        if (uto.getPassword().equals(password2)) {
            log.debug("save() cto={} \nid={}", cto, cto.getUser());
            cuFacade.update(new CustomerUserTO(cto, uto));            
            return new RedirectResolution("/tyre/list");
        }
        
//        log.debug("save() cto={} \nuto={}", cto, cto.getUser());
//        if (uto.getPassword().equals(password2)) {
//            log.debug("save() cto={} \nid={}", cto, cto.getUser());
//            cuFacade.update(new CustomerUserTO(cto, uto));            
//            return new RedirectResolution("/tyre/list");
//        }
//        //customerService.update(cto);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution findByName() {
        log.debug("findByName() ");
        String fn = getContext().getRequest().getParameter("firstname");
        String ln = getContext().getRequest().getParameter("lastname");

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