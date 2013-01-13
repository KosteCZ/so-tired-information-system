package cz.muni.fi.pa165.stis.web.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dusan
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
