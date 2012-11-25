package cz.muni.fi.pa165.stis.web.wrapper;

import cz.muni.fi.pa165.stis.dto.CustomerTO;

/**
 *
 * @author Dusan Svancara
 */
public class CustomerTOWrapper extends CustomerTO {
    
    private String fullName;
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
