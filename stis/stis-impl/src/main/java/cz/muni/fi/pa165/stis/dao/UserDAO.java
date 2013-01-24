package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.User;

/**
 *
 *
 * @author michalxo
 */
public interface UserDAO {

//    public String getUsername(Long id);
//    
//    public void setUsername(Long id, String username);
//        
//    public String getPassword(Long id);
//    
//    public void setPassword(Long id, String password);        
            
    public void create(User user);

    public void remove(User user);

    public void update(User user);

    public User get(Long id);
    
    // username found in db yes/no
    public boolean availableUsername(String username);
    
    public boolean isAdmin(User user);
    
    public void makeAdmin(User user);
    
}
