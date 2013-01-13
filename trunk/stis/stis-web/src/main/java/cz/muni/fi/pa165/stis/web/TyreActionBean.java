package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
 * @author Honza Koščák
 */

@UrlBinding("/tyre/{$event}/")
public class TyreActionBean extends BaseActionBean {
        
    private final static Logger log = LoggerFactory.getLogger(TyreActionBean.class); 
    private static final Logger logger = LoggerFactory.getLogger(TyreActionBean.class);
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "name", required = true),
        @Validate(on = {"create", "save"}, field = "type", required = true),
        @Validate(on = {"create", "save"}, field = "price", required = true)
    })
    private TyreTO tto;
    
    private List<TyreTO> results;
     
    @SpringBean
    protected TyreService tyreService;

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/tyre/list.jsp");
    }
    
    public List<TyreTO> getAllTyres() {
        logger.debug("getting all");
        return tyreService.findAll();
    }

    public TyreTO getTyreTO() {
        return tto;
    }
    
    public void setTyreTO(TyreTO tto) {
        this.tto = tto;
    }
    
    public TyreTO getTto() {
        return tto;
    }
    
    public void setTto(TyreTO tto) {
        this.tto = tto;
    }
    
    public List<TyreTO> getResults() {
        return results;
    }

    public void setResults(List<TyreTO> results) {
        this.results = results;
    }
    
    public Resolution deleteTyre() {                
        HttpServletRequest req = getContext().getRequest();
        Long id = Long.parseLong(req.getParameter("tto.id"));
        tto = tyreService.get(id);
        log.debug("deleteTyre() tto={}", tto);
        tyreService.remove(tto);
        return new RedirectResolution(this.getClass(), "list");
    }
     
     
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTyreFromDatabase() {
        String ids = getContext().getRequest().getParameter("tto.id");
        if (ids == null) {
            return;
        }
        tto = tyreService.get(Long.parseLong(ids));
    }

    public Resolution newTyre() {
        logger.debug("newTyre()");
        return new ForwardResolution("/tyre/create.jsp");
    }
    
    public Resolution create() {
        logger.debug("create() {}", tto);
        tyreService.create(tto);
        return new RedirectResolution(TyreActionBean.class, "list");
    }
    
    public Resolution delete() {
        logger.debug("delete() {}", tto);
        tyreService.remove(tto);
        return new RedirectResolution(TyreActionBean.class, "list");
    }    
    
    public Resolution edit() {
        log.debug("edit() tto={}", tto);
        return new ForwardResolution("/tyre/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() tto={}", tto);
        tyreService.update(tto);
        return new RedirectResolution(this.getClass(), "all");
    }
     
    public Resolution findByName() {
        String name = getContext().getRequest().getParameter("name");
        logger.debug("findByName() {}", name);
        this.results = tyreService.findByName(name);
        return new ForwardResolution("/tyre/results.jsp");
    }
}
