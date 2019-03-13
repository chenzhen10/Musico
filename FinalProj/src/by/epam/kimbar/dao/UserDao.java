package by.epam.kimbar.dao;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.User;

public interface UserDao {
    User authentication(String login, String password) throws DaoException;
    boolean registration(String login, String password, String username) throws DaoException;
}
