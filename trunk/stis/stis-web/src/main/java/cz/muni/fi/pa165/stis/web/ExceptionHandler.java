package cz.muni.fi.pa165.stis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;

/**
 * ExceptionHandler class takes care of exceptions
 * thrown by whole web server.
 * Caught exceptions are passed to ErrorHandlerActionBean
 * with root cause and caught exception.
 * 
 * @author Michal Toth
 */
public class ExceptionHandler extends DefaultExceptionHandler {
        
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private String rootCause;        
    
    public Resolution handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        // general exception handling
        rootCause = getCause(exc).toString();
        logger.error("handleGeneric({})", exc, " ROOT CAUSE=", rootCause);                 
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
    
    public Throwable getCause(Throwable exc) {
        return ExceptionUtils.getRootCause(exc);
    }
}
