package cz.muni.fi.pa165.stis.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Dusan Svancara
 */
@UrlBinding("/security/{$event}/")
public class SecurityActionBean implements ActionBean {
    
    private ActionBeanContext ctx;
    private String error;
    
    public Resolution login() {
        error = getContext().getRequest().getParameter("error");
        //
        return new ForwardResolution("/security/login.jsp");
    }
    
    public Resolution logout() {
        return new RedirectResolution(TyreActionBean.class);
    }

    public String getError() {
        return error;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.ctx = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.ctx;
    }
    
}
