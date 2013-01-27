package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import cz.muni.fi.pa165.stis.web.security.custom.CustomUserDetails;
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
 * @author dusan
 */
@UrlBinding("/extraservice/{$event}/")
public class ExtraServiceActionBean extends BaseActionBean {
    
    private CustomUserDetails user;
    
    private static final Logger logger = LoggerFactory.getLogger(ExtraServiceActionBean.class);
    
    @SpringBean
    private ExtraServiceService service;
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"create", "save"}, field = "name", required = true),
            @Validate(on = {"create", "save"}, field = "price", required = true, minvalue = 1)
    })
    private ExtraServiceTO extraService;
    
    private List<ExtraServiceTO> results;
    
    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/extraservice/list.jsp");
    }
    
    public List<ExtraServiceTO> getAllExtraServices() {
        logger.debug("getting all");
        return service.findAll();
    }
    
    public Resolution newExtraService() {
        logger.debug("newExtraService()");
        //
        return new ForwardResolution("/extraservice/create.jsp");
    }
    
    public Resolution create() {
        logger.debug("create() {}", extraService);
        service.create(extraService);
        //
        return new RedirectResolution(ExtraServiceActionBean.class, "list");
    }
    
    public Resolution delete() {
        logger.debug("delete() {}", extraService);
        service.remove(extraService);
        //
        return new RedirectResolution(ExtraServiceActionBean.class, "list");
    }
    
    public Resolution edit() {
        logger.debug("edit() {}", extraService);
        //
        return new ForwardResolution("/extraservice/edit.jsp");
    }
    
    public Resolution save() {
        logger.debug("save() {}", extraService);
        service.update(extraService);
        //
        return new RedirectResolution(ExtraServiceActionBean.class, "list");
    }
    
    public Resolution findByName() {
        String name = getContext().getRequest().getParameter("name");
        logger.debug("findByName() {}", name);
        this.results = service.findByName(name);
        //
        return new ForwardResolution("/extraservice/results.jsp");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadESFromDatabase() {
        String ids = getContext().getRequest().getParameter("extraService.id");
        if (ids != null) {
            extraService = service.get(Long.parseLong(ids));
        }
    }
    
    public ExtraServiceTO getExtraService() {
        return extraService;
    }

    public void setExtraService(ExtraServiceTO extraService) {
        this.extraService = extraService;
    }

    public List<ExtraServiceTO> getResults() {
        return results;
    }

    public void setResults(List<ExtraServiceTO> results) {
        this.results = results;
    }

}
