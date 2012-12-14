package cz.muni.fi.pa165.stis.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dusan Svancara
 */
@Component
public class PropertyHelper {
    
    @Value("${rest.tyre}")
    private String tyre;
    
    @Value("${rest.extraservice}")
    private String extraService;
    
    @Value("${rest.host}") 
    private String host;
    @Value("${rest.port}")
    private int port;       
    @Value("${rest.webapp}")
    private String webapp;
    
    public String getExtraServiceURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + extraService;
    }
    
    public String getTyreURL() {
        return "http://" + host + ":" + port + "/" + webapp + "/" + tyre;
    }
}
