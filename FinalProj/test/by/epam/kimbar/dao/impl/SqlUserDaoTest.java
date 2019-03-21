package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.ServiceProvider;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlUserDaoTest {
    private static final Logger log = Logger.getLogger(SqlUserDaoTest.class);
    private static DaoProvider provider = null;
    private static UserDao userDao = null;

    @BeforeClass
    public static void init() {

        provider = DaoProvider.getInstance();
        userDao = provider.getUserDao();
    }

    @Test
    public void authentication() {
        String login = "root";
        String password = "toor";
        String username = "admin";

        User user = null;
        try {
            user = userDao.authentication(login, password);
        } catch (DaoException e) {
           log.error(e);
        }
        assertEquals(username, user.getUsername());

    }

    @Test
    public void registration() {
        String login = "test";
        String username = "test12";
        String password = "tester";
        boolean result = false;

        boolean expectedResult = true;
        try {
            result = userDao.registration(login, password, username);
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult, result);
    }


    @Test
    public void deleteUser() {
        boolean expectedResult = false;

        boolean result = true;

        try {
            result = userDao.deleteUser(170);
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void deleteUser2() {
        boolean expectedResult = true;

        boolean result = true;

        try {
            result = userDao.deleteUser(72);
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void getAllUsers() {
        String expecteUser_name = "admin";
        String result = "";
        try {
            result = userDao.getAllUsers().get(0).getUsername();
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expecteUser_name, result);
    }


    @Test
    public void updateUser() {
        boolean expectedResult = false;
        boolean result = false;

        try {
            result =  userDao.updateUser(2);
        } catch (DaoException e) {
            log.error(e);
        }

        assertEquals(expectedResult,result);
    }

    @Test
    public void updateUser2() {
        boolean expectedResult = true;
        boolean result = false;

        try {
            result =  userDao.updateUser(71);
        } catch (DaoException e) {
            log.error(e);
        }

        assertEquals(expectedResult,result);
    }


}