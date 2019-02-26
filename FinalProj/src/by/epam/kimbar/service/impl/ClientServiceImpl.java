package by.epam.kimbar.service.impl;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.exception.InvalidDataException;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.service.validation.CredentialValidator;

public class ClientServiceImpl implements ClientService {

    @Override
    public User authorization(String login, String password) throws ServiceException {

        if (!CredentialValidator.isCorrect(login, password)) {
            throw new InvalidDataException("Invalid data");
        }

        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();

        User user ;
        try {
           user = userDao.authentication(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean isRegistrationSucceed(String login,String password,String username) throws ServiceException {
        if (!CredentialValidator.isCorrect(login, password)) {
            throw new InvalidDataException("Invalid data");
        }
        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        boolean newUser ;
        try {
            newUser = userDao.registration(login, password,username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newUser;
    }


}
