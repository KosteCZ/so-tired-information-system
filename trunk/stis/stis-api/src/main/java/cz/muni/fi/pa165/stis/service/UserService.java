package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.UserTO;

/**
 *
 * @author michalxo
 */
public interface UserService {
           
    public void create(UserTO user);

    public void remove(UserTO user);

    public void update(UserTO user);

    public UserTO get(Long id);
    
    // username found in db yes/no
    public boolean availableUsername(String username);
    
    public boolean isAdmin(UserTO user);
    
    public void makeAdmin(UserTO user);
}
