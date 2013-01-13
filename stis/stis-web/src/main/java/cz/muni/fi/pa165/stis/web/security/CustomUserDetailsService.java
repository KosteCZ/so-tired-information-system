package cz.muni.fi.pa165.stis.web.security;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Dusan Svancara
 */
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        List<CustomerTO> custs = customerService.findAll();
        //
        ShaPasswordEncoder spe = new ShaPasswordEncoder();
        //
        CustomUserDetails d = new CustomUserDetails();
        if (string.equals("john")) {
            CustomGrantedAuthority cga1 = new CustomGrantedAuthority();
            cga1.setAuthority("ROLE_USER");
            CustomGrantedAuthority cga2 = new CustomGrantedAuthority();
            cga2.setAuthority("ROLE_ADMIN");
            d.setIsAdmin(Boolean.TRUE);
            d.setAuthorities(Arrays.asList(cga1, cga2));
            d.setUsername("john");
            d.setPassword(spe.encodePassword("johnson", d.getUsername()));
            
            return d;
        }
        if (string.equals("paul")) {
            CustomGrantedAuthority cga1 = new CustomGrantedAuthority();
            cga1.setAuthority("ROLE_USER");
            d.setIsAdmin(Boolean.FALSE);
            d.setAuthorities(Arrays.asList(cga1));
            d.setUsername("paul");
            d.setPassword(spe.encodePassword("paulson", d.getUsername()));
            if (!custs.isEmpty()) {
                d.setCustomer(custs.get(0));
            }
            
            return d;
        }
        
        return null;
    }
    
}
