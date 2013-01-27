package cz.muni.fi.pa165.stis.web.security.custom;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Dusan Svancara
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    
    private String authority;
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
    
}
