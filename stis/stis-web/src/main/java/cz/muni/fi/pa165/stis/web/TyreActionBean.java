package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
 * @author Honza Koščák
 */

//@UrlBinding("/tyre/{$event}")
//@UrlBinding("/tyre/all")
@UrlBinding("/tyre/{$event}/")
public class TyreActionBean implements ActionBean {
        
    private final static Logger log = LoggerFactory.getLogger(TyreActionBean.class); 
    private ActionBeanContext context;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"newTyre", "saveTyre"}, field = "name", required = true),
        @Validate(on = {"newTyre", "saveTyre"}, field = "type", required = true)
//        @Validate(on = {"newTyre", "saveTyre"}, field = "address", required = true, minlength = 10),
//        @Validate(on = {"newTyre", "saveTyre"}, field = "phone", required = true, minlength= 10, maxlength=14)
    })
    private TyreTO tto;
    
    @SpringBean
    protected TyreService tyreService;
    
    @DefaultHandler
    public Resolution all() {
        //tto = new TyreTO();
        //tto.setName("Michelin");
        //tto.setType("Normal");
        //tyreService.create(tto);
        //
        //log.debug("New tyre: " + tto.getId());
        log.debug("all()");
        return new ForwardResolution("/tyre/list.jsp");
    }
    
    public List<TyreTO> getTyres() {
        List<TyreTO> list = tyreService.findAll();
        //System.err.println(list);
        return list;
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    public TyreTO getTyreTO() {
        return tto;
    }
    
    public void setTyreTO(TyreTO tto) {
        this.tto = tto;
    }
    
    public Resolution newTyre() {
        log.debug("newTyre() tto={}", tto);
        HttpServletRequest req = context.getRequest();
        System.out.println(req.getParameterMap());
        tyreService.create(tto);
        System.err.println(tto);
        return new RedirectResolution(this.getClass(), "all");
    }
    
    public Resolution deleteTyre() {                
        HttpServletRequest req = context.getRequest();
        //System.out.println(req.getContextPath() + "./." + req.getServletPath() + "./."  +req.getPathInfo() +"\n"+ req.getParameterMap());        
        Long id = Long.parseLong(req.getParameter("tto.id"));
        tto = tyreService.get(id);
        log.debug("deleteTyre() tto={}", tto);
        tyreService.remove(tto);
        return new RedirectResolution(this.getClass(), "all");
    }
     
     
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTyreFromDatabase() {
        String ids = context.getRequest().getParameter("tto.id");
        if (ids == null) {
            return;
        }
        tto = tyreService.get(Long.parseLong(ids));
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
     
}
