package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.entity.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Mravec
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles("test")
@Transactional
public class UserDAOImplTest {
    private User user;
    @Autowired
    private UserDAO userDAO;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        removeAll();
    }

    /**
     * Test of create method, of class UserDAO.
     */
    @Test
    public void testCreate() {        
        user = null;

        try {
            userDAO.create(user);
            fail("User is null and didn't throw exception.");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("User is null and didn't throw appropriate exception.");
        }

        user = newUser(null, null, true);
        user.setId(11L);
        try {
            userDAO.create(user);
            fail("ID not null and didn't throw exception");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("ID not null and didn't throw appropriate exception");
        }
        user.setId(null);
        userDAO.create(user);
        assertNotNull("ID is null", user.getId());
    }
    
    @Test
    public void testGet() {
        User u = newUser(null, null, true);
        userDAO.create(u);
        Long id = null;
        try {
            userDAO.get(id);
            fail("Id null and didn't throw exception");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("Id null and didn't throw appropriate exception");
        }
        User u2 = userDAO.get(u.getId());
        assertNotNull("User is null", u2);
        assertEquals("Users are not the same", u2, u);
        assertDeepEquals(u, u2);
        User u3 = userDAO.get(u.getId() + 1); // shouldn't exist
        assertNull("User is not null", u3);
        removeAll();
    }
    
    @Test
    public void testUpdate() {
        User u = newUser(null, null, false);
        userDAO.create(u);
        Long uId = u.getId();
        u.setId(null);
        //
        try {
            userDAO.update(null);
            fail("User is null and didn't throw exception");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("User is null and didn't throw appropriate exception");
        }
        try {
            userDAO.update(u);
            fail("User is ID null and didn't throw exception");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("User is ID null and didn't throw appropriate exception");
        }
        //
        u.setId(uId);
        userDAO.update(u);
        //
        User u2 = userDAO.get(u.getId());
        assertEquals("Customer are not the same", u2, u);
        assertDeepEquals(u2, u);
    }
    
    @Test
    public void testRemove() {
        User u = newUser(null, null, false);
        userDAO.create(u);
        //
        try {
            userDAO.remove(null);
            fail("User is null and didn't throw exception");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("User is null and didn't throw appropriate exception");
        }
        try {
            userDAO.remove(new User());
            fail("User ID is null and didn't throw exception");
        } catch (DataAccessException ex) {
            // ok
        } catch (Exception ex) {
            fail("User ID is null and didn't throw appropriate exception");
        }
        try {
            User u2 = new User();
            u2.setId(-1L);
            userDAO.remove(u2);
            fail("Shouldn't remove non-existent entity");
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("Non existent user - should throw appropriate exception");
        }
        //
        userDAO.remove(u);
        User u3 = userDAO.get(u.getId());
        assertNull("Found user that shouldn't be there", u3);
        removeAll();
    }
    
    @Test
    public void testFindAll() {
        List<User> users = userDAO.findAll();
        assertTrue("Users should be empty", users.isEmpty());
        users = Arrays.asList(new User[] {
            newUser("Bruce", "Willis", false),
            newUser("Jan", "Hrach", false),
            newUser("Peter", "Mravec", true)
        });
        for (User u : users) {
            userDAO.create(u);
        }
        List<User> all = userDAO.findAll();
        assertTrue("Size is not the same", all.size() == users.size());
        Collections.sort(all, userComparator);
        Collections.sort(users, userComparator);
        for (int i = 0; i < all.size(); i++) {
            assertDeepEquals(all.get(i), users.get(i));
        }
    }
    
    @Test
    public void testAvailableUsername(){
        List<User> users = userDAO.findAll();
        assertTrue("Users should be empty", users.isEmpty());
        users = Arrays.asList(new User[] {
            newUser("Bruce", "Willis", false),
            newUser("Jan", "Hrach", false),
            newUser("Peter", "Mravec", true)
        });
        for (User u : users) {
            userDAO.create(u);
        }
        User u1 = newUser("Bruce", "pass", false);
        User u2 = newUser("Bruce1", "pass", false);
        assertFalse("Username exist", userDAO.availableUsername(u1.getUsername()));
        assertTrue("Username is free", userDAO.availableUsername(u2.getUsername()));
    }
    
    @Test
    public void testIsAdmin(){
        List<User> users = userDAO.findAll();
        assertTrue("Users should be empty", users.isEmpty());
        users = Arrays.asList(new User[] {
            newUser("Bruce", "Willis", false),
            newUser("Peter", "Mravec", true)
        });
        for (User u : users) {
            userDAO.create(u);
        }
        assertFalse("Username is not admin", userDAO.isAdmin(users.get(0)));
        assertTrue("Username is admin", userDAO.isAdmin(users.get(1)));
    }
    
    @Test
    public void testMakeAdmin(){
        User u1 = newUser("Bruce", "pass", false);
        userDAO.create(u1);
        assertFalse("Username is not admin", userDAO.isAdmin(u1));
        userDAO.makeAdmin(u1);
        assertTrue("Username is admin", userDAO.isAdmin(u1));
    }
    
    private void removeAll() {
        List<User> ts = userDAO.findAll();
        for (User t : ts) {
            userDAO.remove(t);
        }
    }
    
    private static User newUser(String userName, String password, boolean isAdmin) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setRoleAdmin(isAdmin);
        
        return user;
    }
    
    private void assertDeepEquals(User u1, User u2) {
        assertEquals(u1 == null, u2 == null);
        if (u1 != null) {
            assertEquals(u1.getId(), u2.getId());
            assertEquals(u1.getUsername(), u2.getUsername());
            assertEquals(u1.getPassword(), u2.getPassword());
            assertEquals(u1.getRoleAdmin(), u2.getRoleAdmin());
        }
    }
    
    private static Comparator<User> userComparator = new Comparator<User>() {
        @Override
        public int compare(User t, User t1) {
            return t.getId().compareTo(t1.getId());
        }
    };
}
