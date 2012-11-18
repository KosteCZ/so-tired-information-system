package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dusan Svancara
 */

@UrlBinding("/customer/all")
public class CustomerActionBean implements ActionBean {
    
    private final static Logger log = LoggerFactory.getLogger(CustomerActionBean.class);
 
    private ActionBeanContext context;
    
    @SpringBean
    protected CustomerService customerService;
    
    @DefaultHandler
    public Resolution all() {
        CustomerTO cto = new CustomerTO();
        cto.setFirstName("Adam");
        cto.setLastName("Monk");
        customerService.create(cto);
        //
        log.debug("New customer: " + cto.getId());
        log.debug("all()");
        return new ForwardResolution("/index.jsp");
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
}
