package by.epam.kimbar.service;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.exception.InvalidDataException;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;
/** This interface displays <strong>user</strong> functionality with additional processing some exceptions */
public interface ClientService {
    User authorization(String login,String password) throws ServiceException;
    boolean isRegistrationSucceed(String login,String password,String username) throws ServiceException;
    List<User> getAllUser()throws ServiceException;
    boolean deleteUser(int id) throws ServiceException;
    boolean updateUser(int id) throws  ServiceException;
}

