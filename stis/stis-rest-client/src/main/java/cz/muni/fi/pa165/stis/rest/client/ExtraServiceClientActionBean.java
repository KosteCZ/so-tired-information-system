package cz.muni.fi.pa165.stis.rest.client;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ExtraServiceClientActionBean - client side of REST stis API.
 * Takes care of ENTITY ExtraService.
 *  
 * @author Michal Toth
 */

@Component
@UrlBinding("/extraservice/{$event}/")
public class ExtraServiceClientActionBean implements ActionBean {

    @SpringBean
    private PropertyHelper ph;
    
    private final static Logger logger = LoggerFactory.getLogger(ExtraServiceClientActionBean.class);
    
    private ActionBeanContext context;
    
    @SpringBean
    private RestTemplate restTemplate;
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "name", required = true),
        @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
    })
    private ExtraServiceTO extraService;

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");        
        return new ForwardResolution("/extraservice/list.jsp");
    }

    public Resolution edit() {
        logger.debug("edit() {}", extraService);
        return new ForwardResolution("/extraservice/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", extraService);
        restTemplate.put(getURL() + "/{id}", extraService, extraService.getId());
        System.out.println(extraService.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadESFromDatabase() {
        String id = context.getRequest().getParameter("extraService.id");
        if (id != null) {
            extraService = restTemplate.getForObject(getURL() + "/{id}", ExtraServiceTO.class, id);
        }
    }

    public Resolution delete() {
        logger.debug("delete({})", extraService);
        restTemplate.delete(getURL() + "/{id}", extraService.getId());
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution newExtraService() {
        logger.info("newExtraService()");
        return new ForwardResolution("/extraservice/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", extraService);
        System.out.println(extraService);
        restTemplate.postForObject(getURL() + "/", extraService, ExtraServiceTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public ExtraServiceTO[] getAllExtraServices() {
        logger.debug("getAllExtraService()");
        ExtraServiceTO[] ess = restTemplate.getForObject(getURL() + "/", ExtraServiceTO[].class);
        return ess;
    }
    
    
    
    public ExtraServiceTO getExtraService() {
        return extraService;
    }

    public void setExtraService(ExtraServiceTO extraService) {
        this.extraService = extraService;
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
        return ph.getExtraServiceURL();
    }
}
