package cz.muni.fi.pa165.stis.rest.client;

import javax.el.ELException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

/**
 * RestClientExceptionHandler class takes care of exceptions
 * thrown by whole client communication with REST server.
 * Caught exceptions are passed to ErrorHandlerActionBean
 * with root cause and caught exception.
 * 
 * @author Michal Toth
 */
public class RestClientExceptionHandler extends DefaultExceptionHandler {
        
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(RestClientExceptionHandler.class);
    private String rootCause;
    
/** 
 *  These following ExceptionHandlers might be deleted. After more testings they will be removed from code.
 
    public Resolution handleDatabaseException(SqlExceptionHelper exc, HttpServletRequest request, HttpServletResponse response) {
        // do something to handle RestClient exceptions
        logger.debug("handleSqlExceptionHelper({})", exc, " ROOT CAUSE=", request, response);            
        
        return new ForwardResolution("/error.jsp").addParameter("exception", exc);
    }
   
    public Resolution handleRestClientException(RestClientException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        logger.error("handleRestClientExceptionHelper({})", exc, " ROOT CAUSE=", rootCause, request, response);                            
        return new ForwardResolution("/error.jsp").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
    
    public Resolution handleELException(ELException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        logger.error("handleELExceptionHelper({})", exc, " ROOT CAUSE=", rootCause);                            
        return new ForwardResolution("/error.jsp").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
*/    
    public Resolution handleServletException(ServletException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        logger.error("handleServletException() {}", exc,  " ROOT CAUSE=", rootCause);          
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc.toString()).addParameter("rootCause", rootCause);                
    }
        
    
    public Resolution handleServerErrorException(HttpServerErrorException exc, HttpServletRequest request, HttpServletResponse response) {
        rootCause = this.getCause(exc).toString();
        logger.error("handleServerErrorException({})", exc, " ROOT CAUSE=", rootCause);    
        return new ForwardResolution(ErrorHandlerActionBean.class, "error").addParameter("exception", exc).addParameter("rootCause", rootCause);
    }
    
    
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
