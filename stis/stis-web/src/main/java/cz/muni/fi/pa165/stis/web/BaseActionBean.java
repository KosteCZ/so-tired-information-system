package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.web.security.CustomUserDetails;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Dusan Svancara
 */
public class BaseActionBean implements ActionBean {

    private static final Logger logger = LoggerFactory.getLogger(BaseActionBean.class);
    private ActionBeanContext context;
    private CustomUserDetails user;

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.debug("loaded principal: {}", o);
            if (o instanceof CustomUserDetails) {
                user = (CustomUserDetails) o;
            }
        }
        logger.debug("loaded user: {}", user);
    }

    public CustomUserDetails getUser() {
        return user;
    }

    public void setUser(CustomUserDetails user) {
        this.user = user;
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
