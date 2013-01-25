package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.User;
import java.util.Objects;
import org.dozer.DozerBeanMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.argThat;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Michal Toth
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl service;
    @Mock
    private UserDAO dao;
    private DozerBeanMapper mapper;

    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        service = new UserServiceImpl();
        ReflectionTestUtils.setField(service, "dao", dao);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    @After
    public void tearDown() {
        service = null;
        mapper = null;
    }

    @Test
    public void testExceptions() {
        try {
            service.create(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.update(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.get(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        
        try {
            service.getByUsername(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException e) {
            //ok
        }
        
        try {
            service.remove(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.availableUsername(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.isAdmin(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.makeAdmin(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }

        User u = createUser("mrkvicka", "345sac", false);
        UserTO uto = mapper.map(u, UserTO.class);
        try {
            service.update(uto);
            fail("null id exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(uto);
            fail("null id exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        uto.setId(5L);
        try {
            service.create(uto);
            fail("not null id - exception should be thrown");
        } catch (IllegalArgumentException ex) {
            //ok
        }
    }

    @Test
    public void testCreate() {
        final User user = createUser("mrkvicka", "345sac", false);
        UserTO uto = mapper.map(user, UserTO.class);

        service.create(uto);
        verify(dao).create(argThat(new BaseMatcher<User>() {
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof User)) {
                    return false;
                }
                final User u = (User) item;
                if (!Objects.equals(u.getPassword(), user.getPassword())) {
                    return false;
                }
                if (!Objects.equals(u.getUsername(), user.getUsername())) {
                    return false;
                }
                if (!Objects.equals(u.getRoleAdmin(), user.getRoleAdmin())) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }));
    }

    @Test
    public void testGet() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        when(dao.get(4L)).thenReturn(user);
        UserTO userTO = service.get(4L);
        assertEquals(userTO, uto);
    }

    @Test
    public void getByUsername() {
        User user3 = createUser("mrkvicka", "345sac", false);
        User user2 = createUser("Bruce", "Willis", false);
        User user1 = createUser("Peter", "Mravec", true);
        UserTO uto1 = mapper.map(user1, UserTO.class);
        UserTO uto2 = mapper.map(user2, UserTO.class);
        UserTO uto3 = mapper.map(user3, UserTO.class);
        
        when(dao.getByUsername("mrkvicka")).thenReturn(user3);               
        UserTO uto = service.getByUsername("mrkvicka");
        verify(dao).getByUsername("mrkvicka");
        assertEquals(uto, uto3);        
    }

    @Test
    public void testUpdate() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        service.update(uto);
        verify(dao).update(user);
    }

    @Test
    public void testRemove() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        service.remove(uto);
        verify(dao).remove(user);
    }

    @Test
    public void testAvailableUsername() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        when(dao.availableUsername("mrkvicka")).thenReturn(Boolean.FALSE);
        assertFalse("Should not be available Username", service.availableUsername("mrkvicka"));
        service.remove(uto);
        when(dao.availableUsername("mrkvicka")).thenReturn(Boolean.TRUE);
        assertTrue("Should be available Username", service.availableUsername("mrkvicka"));
    }

    @Test
    public void testIsAdmin() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        when(dao.isAdmin(user)).thenReturn(Boolean.FALSE);
        assertFalse("user should not have roleAdmin", service.isAdmin(uto));
        when(dao.isAdmin(user)).thenReturn(Boolean.TRUE);
        assertTrue("user should have roleAdmin", service.isAdmin(uto));
    }

    @Test
    public void testMakeAdmin() {
        User user = createUser("mrkvicka", "345sac", false);
        user.setId(4L);
        UserTO uto = mapper.map(user, UserTO.class);

        when(dao.isAdmin(user)).thenReturn(Boolean.FALSE);
        assertFalse("User should not be admin", service.isAdmin(uto));
        service.makeAdmin(uto);
        verify(dao, times(1)).makeAdmin(user);
        when(dao.isAdmin(user)).thenReturn(Boolean.TRUE);
        assertTrue("User should be admin", service.isAdmin(uto));
    }

    // newly created user is missing ID
    private static User createUser(String username, String password, boolean roleAdmin) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleAdmin(roleAdmin);
        return user;
    }
}
