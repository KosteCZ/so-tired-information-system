package cz.muni.fi.pa165.stis.web.security.custom;

import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Dusan Svancara
 */
public class CustomUserDetailsService implements UserDetailsService {
    
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    
    @Autowired
    private CustomerUserFacade cuFacade;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        //
        CustomUserDetails d = new CustomUserDetails();
        if (string.equals(adminUsername)) {
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
            d.setUsername(adminUsername);
            d.setPassword(adminPassword);
            
            return d;
        }
        CustomerUserTO cUser = cuFacade.getByUsername(string);
        if (cUser == null) {
            throw new UsernameNotFoundException(string + " not found");
        }
        d.setIsAdmin(cUser.getUser().getRoleAdmin());
        if (d.getIsAdmin()) {
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
        } else {
            d.setAuthorities(Arrays.asList(createRole("ROLE_USER")));
        }
        d.setUsername(cUser.getUser().getUsername());
        d.setPassword(cUser.getUser().getPassword());
        d.setCustomer(cUser.getCustomer());

        return d;
    }
    
    private CustomGrantedAuthority createRole(String role) {
        CustomGrantedAuthority cga = new CustomGrantedAuthority();
        cga.setAuthority(role);
        
        return cga;
    }
    
}
