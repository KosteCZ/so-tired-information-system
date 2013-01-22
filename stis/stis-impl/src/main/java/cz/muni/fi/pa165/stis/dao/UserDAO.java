package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.UserEntity;

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
    
    public void create(UserEntity user);

    public void remove(UserEntity user);

    public void update(UserEntity user);

    public UserEntity get(Long id);
    
    // username found in db yes/no
    public boolean availableUsername(String userName);
    
    public boolean isAdmin(UserEntity user);
    
    public void makeAdmin(UserEntity user);
    
}
