package lexicon.data.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lexicon.data.impl.AppRole;
import lexicon.data.impl.AppUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AppUserDaoCollectionTest {

    private AppUser testObject;

    @Autowired
    private AppUserDao appUserDao;

    @BeforeEach
    public void setup() {
        testObject = new AppUser("username", "password", AppRole.ROLE_APP_USER);
    }

    @Test
    public void testPersist() {
        appUserDao.persist(testObject);
        appUserDao.findAll();
    }

    @Test
    public void testFindByUserName() {
        appUserDao.persist(testObject);
        assertEquals(testObject.getUsername(), appUserDao.findByUsername(testObject.getUsername()).getUsername());
    }

    @Test
    public void testFindAll() {
        appUserDao.persist(testObject); // Adds AppUser object to Collection
        List<AppUser> appUsersAll = new ArrayList<>(appUserDao.findAll());

        assertEquals(testObject.toString(), appUsersAll.get(0).toString()); // Compares AppUser object with first element in Collection
    }

    @Test
    public void testRemove() {
        appUserDao.persist(testObject);
        appUserDao.remove(testObject.getUsername());
        assertTrue(appUserDao.findAll().isEmpty());
    }
}