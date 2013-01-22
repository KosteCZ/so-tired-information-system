package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.UserTO;

/**
 *
 * @author michalxo
 */
public interface UserService {
    
//    public String getUsername(Long id);
//    
//    public void setUsername(Long id, String username);
//        
//    public String getPassword(Long id);
//    
//    public void setPassword(Long id, String password);        
    
    public void create(UserTO user);

    public void remove(UserTO user);

    public void update(UserTO user);

    public UserTO get(Long id);
    
    // username found in db yes/no
    public boolean availableUsername(String userName);
    
    public boolean isAdmin(UserTO user);
    
    public void makeAdmin(UserTO user);
}
