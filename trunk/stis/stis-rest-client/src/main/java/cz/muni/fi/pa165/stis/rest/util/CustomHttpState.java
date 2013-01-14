package cz.muni.fi.pa165.stis.rest.util;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 *
 * @author Dusan Svancara
 */
public class CustomHttpState extends HttpState {

    public synchronized void setCredentials(Credentials credentials) {
        super.setCredentials(AuthScope.ANY, credentials);
    }
    
    
}
