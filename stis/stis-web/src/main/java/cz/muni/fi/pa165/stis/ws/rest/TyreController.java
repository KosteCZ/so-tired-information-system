package cz.muni.fi.pa165.stis.ws.rest;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/tyres")
public class TyreController {
    
    @Autowired
    private TyreService service;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody TyreTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }
        
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody TyreTO update(@PathVariable("id") Long id, @RequestBody TyreTO tyre, HttpServletResponse resp) {
        tyre.setId(id);
        service.update(tyre);
            
        resp.setStatus(HttpStatus.OK.value());
        
        return tyre;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody TyreTO delete(@PathVariable("id") Long id, HttpServletResponse resp) {
        TyreTO to = service.get(id);
        if (to != null) {
            service.remove(to);
        } else {
            throw new IllegalArgumentException("resource not found");
        }
        
        resp.setStatus(HttpStatus.OK.value());
        
        return null;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<TyreTO> find(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            return service.findAll();
        }
        
        return service.findByName(name);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody TyreTO create(@RequestBody TyreTO tyre, HttpServletRequest req, HttpServletResponse resp) {
        service.create(tyre);
        
        resp.setStatus(HttpStatus.CREATED.value());
        resp.setHeader("Location", req.getContextPath() + "/rest/tyres/" + tyre.getId());
        
        return tyre;
    }
}
