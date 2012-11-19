package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
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
 * @author Dusan Svancara
 */

@UrlBinding("/customer/{$event}/")
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
        @Validate(on = {"newCustomer"}, field="lastname", required=true, minlength= 10)        
    })
    private CustomerTO cto;

    public Resolution newCustomer() {
        log.debug("newCustomer() cto={}", cto);
        HttpServletRequest req = context.getRequest();
        System.out.println(req.getParameterMap());
        customerService.create(cto);
        System.err.println(cto);
        return new RedirectResolution(this.getClass(), "all");
    }
        
     public Resolution deleteCustomer() {                
        HttpServletRequest req = context.getRequest();
        //System.out.println(req.getContextPath() + "./." + req.getServletPath() + "./."  +req.getPathInfo() +"\n"+ req.getParameterMap());        
        Long id = Long.parseLong(req.getParameter("cto.id"));
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
     
    
}

