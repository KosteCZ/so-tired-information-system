package cz.muni.fi.pa165.stis.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author Michal Toth
 */

@UrlBinding("/extraservice/{$event}/")
public class ExtraServiceClientActionBean implements ActionBean {

    ObjectMapper mapper = new ObjectMapper();
    static String HOST = "localhost";
    static int PORT = 8080;
    static String webapp = "pa165/rest";
    String url = "http://" + HOST + ":" + PORT + "/" + webapp + "/extraservices";
    final static Logger logger = LoggerFactory.getLogger(ExtraServiceClientActionBean.class);
    private ActionBeanContext context;
    private RestTemplate rt = new RestTemplate();
   
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

    public Resolution edit() {
        logger.debug("edit() {}", extraService);
        return new ForwardResolution("/extraservice/edit.jsp");
    }

    public Resolution save() {
        logger.debug("save() {}", extraService);
        rt.put(url + "/{id}", extraService, extraService.getId());
        System.out.println(extraService.toString());
        return new RedirectResolution(this.getClass(), "list.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadESFromDatabase() {
        String id = context.getRequest().getParameter("extraService.id");        
        if (id != null) {
            extraService = rt.getForObject(url + "/{id}", ExtraServiceTO.class, id);
        }

        System.out.println("DB obj -> EXTRA SERVICE=" + extraService);
    }

    public Resolution delete() {

        logger.debug("delete({})", extraService);
        try {
            rt.delete(url + "/{id}", extraService.getId());
        } catch (RestClientException e) {
            System.err.println("Exception has been thrown " + e.getStackTrace());
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution newExtraService() {
        logger.info("newExtraService()");
        return new ForwardResolution("/extraservice/create.jsp");
    }

    public Resolution create() {
        logger.debug("create() {}", extraService);
        rt.postForObject(url + "/", extraService, ExtraServiceTO.class);
        return new RedirectResolution(this.getClass(), "list");
    }

    public ExtraServiceTO[] getAllExtraServices() {
        logger.debug("getAllExtraService()");
        ExtraServiceTO[] ess = rt.getForObject(url + "/", ExtraServiceTO[].class);
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
}