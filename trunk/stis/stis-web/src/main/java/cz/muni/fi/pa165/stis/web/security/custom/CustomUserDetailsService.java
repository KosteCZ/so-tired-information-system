package cz.muni.fi.pa165.stis.web.security.custom;

import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
    @Value("${rest.username}")
    private String restUsername;
    @Value("${rest.password}")
    private String restPassword;
    
    @Autowired
    private CustomerUserFacade cuFacade;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        ShaPasswordEncoder spe = new ShaPasswordEncoder();
        System.out.println(spe.encodePassword("rest", "rest"));
        //
        CustomUserDetails d = new CustomUserDetails();
        if (string.equals(adminUsername)) {
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
            d.setUsername(adminUsername);
            d.setPassword(adminPassword);
            
            return d;
        }
        if (string.equals(restUsername)) {
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
            d.setUsername(restUsername);
            d.setPassword(restPassword);
            
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
