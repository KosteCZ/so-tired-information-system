package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import cz.muni.fi.pa165.stis.service.UserService;
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
 * @author Peter Mravec
 */
@UrlBinding("/registration/{$event}/")
public class RegistrationActionBean implements ActionBean {

    private static final Logger logger = LoggerFactory.getLogger(BaseActionBean.class);
    private ActionBeanContext context;
    private String regSucc;
    private String passwordError;
    private String usernameError;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add","edit","save"}, field = "firstName", required = true),
        @Validate(on = {"add","edit","save"}, field = "lastName", required = true),
        @Validate(on = {"add","edit","save"}, field = "address", required = true)
    })
    private CustomerTO cto;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add","edit","save"}, field = "username", required = true),
        @Validate(on = {"add","edit","save"}, field = "password", required = true, minlength = 4)
    })
    private UserTO uto;
    @Validate(on = {"add","edit","save"}, field = "password2", required = true, minlength = 4)
    private String password2;
    
    @SpringBean
    protected CustomerUserFacade cuFacade;
    @SpringBean
    protected UserService uService;

    @DefaultHandler
    public Resolution add() {
        logger.debug("add()");
        return new ForwardResolution("/registration/create.jsp");
    }

    public Resolution create() {
        logger.debug("newcustomer() cto={} uto]={}", cto, uto);

        if (uto.getPassword().equals(password2)) {
            if (uService.availableUsername(uto.getUsername())) {
                uto.setRoleAdmin(false);
                cuFacade.create(cto, uto);
                regSucc = "true";
                return new RedirectResolution("/security/login/").addParameter("regSucc", "true");
            }
            //username is not available
            usernameError = "true";
            //return new RedirectResolution("/registration/create.jsp").addParameter("usernameError", "true").flash(this);
            return new RedirectResolution(this.getClass()).addParameter("usernameError", "true").flash(this);
        }
        //password is not same as password2
        passwordError = "true";
        //return new RedirectResolution("/registration/create.jsp").addParameter("passwordError", "true").flash(this);
        return new RedirectResolution(this.getClass()).addParameter("passwordError", "true").flash(this);
    }

    public Resolution list() {
        logger.debug("list()");
        return new RedirectResolution("/tyre/list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void load() {                                        
        if (getContext().getRequest().getParameter("id") == null) {
            return;
        }
        Long id = Long.parseLong(getContext().getRequest().getParameter("id"));
        CustomerUserTO cuto = cuFacade.getByCustomerId(id);
        cto = cuto.getCustomer();
        uto = cuto.getUser();
        logger.debug("load() cuto={} \ncto={} \nuto={}", cuto, cto, uto);                
    }
    
    public Resolution edit() {
        logger.debug("edit() cto={} \nuto={}", cto, uto);
        logger.debug("edit() getCto={} \ngetUto={}", this.getCto(), this.getUto());
        return new ForwardResolution("/registration/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() cto={} \nuto={}", cto, uto);
        if(uto.getPassword().equals(password2)){
            cuFacade.update(cuFacade.getByCustomerId(cto.getId()));
            return new RedirectResolution(this.getClass(), "all");
        }
        return new RedirectResolution("/registration/edit.jsp").addParameter("passwordError", "true").flash(this);                
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

    public String getRegSucc() {
        return regSucc;
    }

    public void setRegSucc(String regSucc) {
        this.regSucc = regSucc;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
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
