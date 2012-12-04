package cz.muni.fi.pa165.stis.ws.rest;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dusan Svancara
 */
@Controller
@RequestMapping("/extraservices")
public class ExtraServiceController {
    
    @Autowired
    private ExtraServiceService service;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ExtraServiceTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody String update(@PathVariable("id") Long id, @RequestBody ExtraServiceTO es) {
        es.setId(id);
        service.update(es);
        
        return "ok";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") Long id) {
        ExtraServiceTO to = service.get(id);
        if (to != null) {
            service.remove(to);
            return "ok";
        }
        
        return "not_found";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<ExtraServiceTO> findAll() {
        return service.findAll();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody String create(@RequestBody ExtraServiceTO es) {
        service.create(es);
        
        return "ok";
    }
}
