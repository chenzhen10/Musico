package by.epam.kimbar.service.impl;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.exception.InvalidDataException;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.service.validation.CredentialValidator;

import java.util.List;

/**
 * This class implements {@link by.epam.kimbar.service.ClientService}
 */
public class ClientServiceImpl implements ClientService {
    /**Error message if data is invalid or unexpected exception was thrown */
    private static final String ERROR_MESSAGE = "Invalid data";

    /**
     *  Method check if the inputed credentials are correct if all ok user successfully login in to the system
     *  if not user get corresponding message
     * @param login
     *         login of the user contains only latin symbols and number
     * @param password
     *          password of the user contains only latin symbols and number
     * @return user if registration succeed or exception if password or login are incorrect
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.UserDao
     * @see by.epam.kimbar.dao.UserDao#authentication(String, String)
     */
    @Override
    public User authorization(String login, String password) throws ServiceException {

        if (!CredentialValidator.isCorrect(login, password)) {
            throw new InvalidDataException(ERROR_MESSAGE);
        }

        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();

        User user;
        try {
            user = userDao.authentication(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     *  Method provide registration in system and if all credentials is ok user get access to application like a user
     * @param login
     *          login of the user should contains latin symbols with any case(upper/lower) and numbers
     * @param password
     *          password of the user should contains latin symbols with any case(upper/lower) and numbers
     * @param username
     *         username can contains any of the available character
     * @return true - if registration succeed, false - if not
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.UserDao
     * @see by.epam.kimbar.dao.UserDao#registration(String, String, String)
     */
    @Override
    public boolean isRegistrationSucceed(String login, String password, String username) throws ServiceException {
        if (!CredentialValidator.isCorrect(login, password)) {
            throw new InvalidDataException(ERROR_MESSAGE);
        }
        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        boolean newUser;
        try {
            newUser = userDao.registration(login, password, username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newUser;
    }

    /**
     * Method return list of all users to delete or give them privilege
     * @return list of all users
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.UserDao
     * @see by.epam.kimbar.dao.UserDao#getAllUsers()
     */
    @Override
    public List<User> getAllUser() throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        List<User> users = null;
        try {
            users = userDao.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method deleting users by id
     * @param id
     *          Parameter which uses when deleting user
     * @return true - if user was deleted , false - if not
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.UserDao
     * @see by.epam.kimbar.dao.UserDao#deleteUser(int)
     */
    @Override
    public boolean deleteUser(int id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        boolean result;
        try {
            result = userDao.deleteUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Method updating user by id
     * @param id
     *         Parameter which uses when updating user
     * @return true - if user was updated successfully , false - if not
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.UserDao
     * @see by.epam.kimbar.dao.UserDao#updateUser(int)
     */
    @Override
    public boolean updateUser(int id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        boolean result;
        try {
            result = userDao.updateUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return result;
    }


}
