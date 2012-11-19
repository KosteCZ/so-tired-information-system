package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dusan Svancara
 */

//@UrlBinding("/customer/{$event}")
@UrlBinding("/customer/all")
public class CustomerActionBean implements ActionBean {
    
    private final static Logger log = LoggerFactory.getLogger(CustomerActionBean.class); 
    private ActionBeanContext context;
    
    @SpringBean
    protected CustomerService customerService;
    
    @DefaultHandler
    public Resolution all() {
        log.debug("all()");        
        return new ForwardResolution("/customer/list.jsp");
    }
    
    public List<CustomerTO> getCustomers() {
        
        List<CustomerTO> list = customerService.findAll();
        System.err.println("AAAAAAAAAAAAAA");
        System.err.println("AAAAAAAAAAAAAA");
        System.err.println(list);
        System.err.println("AAAAAAAAAAAAAA");
        System.err.println("AAAAAAAAAAAAAA");
        return list;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    
    
    public CustomerTO getCustomerTO() {
        return cto;
    }
    
    public void setCustomerTO(CustomerTO cto) {
        this.cto = cto;
    }
                    
    @ValidateNestedProperties(value = {
        @Validate(on = {"newCustomer", "saveCustomer"}, field = "firstname", required = true),
        @Validate(on = {"newCustomer", "saveCustomer"}, field = "lastname", required = true)
//        @Validate(on = {"newCustomer", "saveCustomer"}, field = "address", required = true, minlength = 10),
//        @Validate(on = {"newCustomer", "saveCustomer"}, field = "phone", required = true, minlength= 10, maxlength=14)
    })
    private CustomerTO cto;

    public Resolution newCustomer() {
        log.debug("newCustomer() cto={}", cto);
        customerService.create(cto);
        System.err.println(cto);
        return new RedirectResolution(this.getClass(), "all");
    }
        
    
}

