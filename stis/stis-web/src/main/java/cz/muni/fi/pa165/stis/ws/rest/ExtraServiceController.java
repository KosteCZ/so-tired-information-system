package cz.muni.fi.pa165.stis.ws.rest;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dusan Svancara
 */
@Controller
@RequestMapping("/extraservices")
public class ExtraServiceController {
    
    //@SpringBean
    private ExtraServiceService service;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ExtraServiceTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }
        
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody ExtraServiceTO update(@PathVariable("id") Long id, @RequestBody ExtraServiceTO es, HttpServletResponse resp) {
        es.setId(id);
        service.update(es);
            
        resp.setStatus(HttpStatus.OK.value());
        
        return es;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ExtraServiceTO delete(@PathVariable("id") Long id, HttpServletResponse resp) {
        ExtraServiceTO to = service.get(id);
        if (to != null) {
            service.remove(to);
        } else {
            throw new IllegalArgumentException("resource not found");
        }
        
        resp.setStatus(HttpStatus.OK.value());
        
        return null;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<ExtraServiceTO> find(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return service.findAll();
        }
        
        return service.findByName(name);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ExtraServiceTO create(@RequestBody ExtraServiceTO es, HttpServletRequest req, HttpServletResponse resp) {
        service.create(es);
        
        resp.setStatus(HttpStatus.CREATED.value());
        resp.setHeader("Location", req.getContextPath() + "/rest/extraservices/" + es.getId());
        
        return es;
    }
}
