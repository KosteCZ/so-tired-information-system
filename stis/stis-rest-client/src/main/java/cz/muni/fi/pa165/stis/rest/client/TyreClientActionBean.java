package cz.muni.fi.pa165.stis.rest.client;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.rest.util.PropertyHelper;
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
import org.springframework.web.client.RestTemplate;

/**
 *
 * TyreClientActionBean - client side of REST stis API. Takes care of entity
 * Tyre CRUD manipulation.
 *
 * @author Jan Koščák
 */
@UrlBinding("/tyre/{$event}/")
public class TyreClientActionBean implements ActionBean {

    private static final Logger logger = LoggerFactory.getLogger(TyreClientActionBean.class);
    
    private ActionBeanContext context;
    
    @SpringBean
    private RestTemplate rt;
    
    @SpringBean
    private PropertyHelper ph;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "name", required = true),
        @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
    })
    private TyreTO tyre;
    
    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/tyre/list.jsp");
    }

    public Resolution edit() {
        logger.debug("edit() {}", tyre);
        return new ForwardResolution("/tyre/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", tyre);
        rt.put(getURL() + "/{id}", tyre, tyre.getId());
        System.out.println(tyre.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadESFromDatabase() {
        String id = context.getRequest().getParameter("tyre.id");
        if (id != null) {
            tyre = rt.getForObject(getURL() + "/{id}", TyreTO.class, id);
        }
    }

    public Resolution delete() {
        logger.debug("delete({})", tyre);
        rt.delete(getURL() + "/{id}", tyre.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution newTyre() {
        logger.info("newTyre()");
        return new ForwardResolution("/tyre/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", tyre);
        rt.postForObject(getURL() + "/", tyre, TyreTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public TyreTO[] getAllTyres() {
        logger.debug("getAllTyres()");
        TyreTO[] ess = rt.getForObject(getURL() + "/", TyreTO[].class);
        return ess;
    }

    public TyreTO getTyre() {
        return tyre;
    }

    public void setTyre(TyreTO tyre) {
        this.tyre = tyre;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    private String getURL() {
        return ph.getTyreURL();
    }
}
