package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.PasswordEncryptor;
import by.epam.kimbar.util.PasswordValidator;

import java.sql.*;

public class SqlUserDao implements UserDao {
    private static final int NUMBER_OF_VALID_OPERATION = 1;
    private static final String LOGIN_COLUMN = "user_login";
    private static final String PASSWORD_COLUMN = "user_password";
    private static final String USER_NAME_COLUMN = "user_name";
    private static final int DEFAULT_ROLE_ID = 2; //Default role id for user 1 - admin 2- user 3 - premium

    private static final String QUERY_TO_AUTH_IN_SYSTEM =
            "SELECT * FROM users WHERE user_login = ? ;";

    private static final String QUERY_TO_REGISTRATE_IN_SYSTEM =
            "INSERT INTO users (user_login,user_password,user_name,idrole) values(?,?,?,2);";


    @Override
    public User authentication(String login, String password) throws DaoException {
        User user = findUser(login);
        if (user != null) {
            if (PasswordValidator.isPasswordCorrect(password, user)) {
                return user;
            }
        }
        return null;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString(LOGIN_COLUMN));
        user.setPassword(rs.getString(PASSWORD_COLUMN));
        user.setUsername(rs.getString(USER_NAME_COLUMN));
        user.setIdRole(DEFAULT_ROLE_ID);
        return user;
    }


    public boolean registration(String login, String password, String username) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            prepSt = con.prepareStatement(QUERY_TO_REGISTRATE_IN_SYSTEM);
            prepSt.setString(1, login);
            prepSt.setString(2, PasswordEncryptor.encryptPassword(password));
            prepSt.setString(3, username);


            res = prepSt.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            //log
            try {
                con.rollback();
            } catch (SQLException e1) {
                //log
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }
        return res == NUMBER_OF_VALID_OPERATION;
    }


    public User findUser(String login) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            prepSt = con.prepareStatement(QUERY_TO_AUTH_IN_SYSTEM);

            prepSt.setString(1, login);

            rs = prepSt.executeQuery();

            if (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }

        return user;

    }
}
