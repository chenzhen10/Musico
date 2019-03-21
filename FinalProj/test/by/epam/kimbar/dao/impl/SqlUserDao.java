package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.impl.connection.ConnectionPool1;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.password.PasswordEncryptor;
import by.epam.kimbar.util.password.PasswordValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDao implements UserDao {
    private static final int NUMBER_OF_VALID_OPERATION = 1;
    private static final String USER_ID_COLUMN = "iduser";
    private static final String LOGIN_COLUMN = "user_login";
    private static final String PASSWORD_COLUMN = "user_password";
    private static final String USER_NAME_COLUMN = "user_name";
    private static final String ROLE_ID_COLUMN = "idrole"; //Default role id for user : 1 - admin 2 - user

    private static final String QUERY_TO_GET_ALL_USERS = "SELECT * FROM users";

    private static final String QUERY_TO_DELETE_USER = "DELETE FROM users WHERE iduser = ?";

    private static final String QUERY_TO_UPDATE_USER = "UPDATE users SET idrole = 1 WHERE iduser = ?";


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
        user.setIdUser(rs.getInt(USER_ID_COLUMN));
        user.setLogin(rs.getString(LOGIN_COLUMN));
        user.setPassword(rs.getString(PASSWORD_COLUMN));
        user.setUsername(rs.getString(USER_NAME_COLUMN));
        user.setIdRole(rs.getInt(ROLE_ID_COLUMN));
        return user;
    }


    public boolean registration(String login, String password, String username) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool1.getInstance().takeConnection();
            con.setAutoCommit(false);
            prepSt = con.prepareStatement(QUERY_TO_REGISTRATE_IN_SYSTEM);
            prepSt.setString(1, login);
            prepSt.setString(2, PasswordEncryptor.encryptPassword(password));
            prepSt.setString(3, username);


            res = prepSt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }
        return res == NUMBER_OF_VALID_OPERATION;
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<User> user = new ArrayList<>();
        try {
            con = ConnectionPool1.getInstance().takeConnection();

            st = con.createStatement();
            rs = st.executeQuery(QUERY_TO_GET_ALL_USERS);
            while (rs.next()) {
                user.add(createUser(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, st);
        }
        return user;

    }

    @Override
    public boolean deleteUser(int id) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool1.getInstance().takeConnection();
            ;
            con.setAutoCommit(false);

            prepSt = con.prepareStatement(QUERY_TO_DELETE_USER);
            prepSt.setInt(1, id);


            res = prepSt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }
        return res == NUMBER_OF_VALID_OPERATION;
    }

    @Override
    public boolean updateUser(int id) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool1.getInstance().takeConnection();
            ;

            con.setAutoCommit(false);
            prepSt = con.prepareStatement(QUERY_TO_UPDATE_USER);
            prepSt.setInt(1, id);


            res = prepSt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
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
            con = ConnectionPool1.getInstance().takeConnection();
            ;

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
