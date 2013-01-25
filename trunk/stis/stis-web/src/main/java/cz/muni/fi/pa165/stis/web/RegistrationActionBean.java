package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.UserService;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter Mravec
 */
@UrlBinding("/registration/{$event}/")
public class RegistrationActionBean implements ActionBean{
    private static final Logger logger = LoggerFactory.getLogger(BaseActionBean.class);
    private ActionBeanContext context;
    
    private CustomerTO cto;
    private UserTO uto;
    
    @SpringBean
    protected CustomerUserFacade cuFacade;
        
    @DefaultHandler
    public Resolution all() {
        logger.debug("all()");
        return new ForwardResolution("/registration/create.jsp");
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
    
    public Resolution add() {
        logger.debug("newcustomer() cto={}", cto);
        
        System.out.println("******************");
        System.out.println("******************");
        System.out.println("******************");
        System.out.println(cto.toString());
        System.out.println(uto.toString());
        System.out.println("******************");
        System.out.println("******************");
        System.out.println("******************");
        uto.setRoleAdmin(false);
        cuFacade.create(cto, uto);
        return new RedirectResolution("/"); 
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
