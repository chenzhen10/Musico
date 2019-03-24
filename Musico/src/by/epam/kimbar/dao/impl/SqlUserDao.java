package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.UserDao;
import by.epam.kimbar.dao.impl.connection.ConnectionPool1;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.password.PasswordEncryptor;
import by.epam.kimbar.util.password.PasswordValidator;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link  by.epam.kimbar.service.ClientService}
 * and provide all necessary methods to interact with our pages
 * */
public class SqlUserDao implements UserDao {
    private static final int NUMBER_OF_VALID_OPERATION = 1;

    /** Fields from DB for news */
    private static final String USER_ID_COLUMN = "iduser";
    private static final String LOGIN_COLUMN = "user_login";
    private static final String PASSWORD_COLUMN = "user_password";
    private static final String USER_NAME_COLUMN = "user_name";
    /** Default role id for user : 1 - admin 2 - user */
    private static final String ROLE_ID_COLUMN = "idrole";
    /** Query to interact with DB */
    private static final String QUERY_TO_GET_ALL_USERS = "SELECT * FROM users";
    private static final String QUERY_TO_DELETE_USER = "DELETE FROM users WHERE iduser = ?";
    private static final String QUERY_TO_UPDATE_USER = "UPDATE users SET idrole = 1 WHERE iduser = ?";
    private static final String QUERY_TO_AUTH_IN_SYSTEM =
            "SELECT * FROM users WHERE user_login = ? ;";
    private static final String QUERY_TO_REGISTRATE_IN_SYSTEM =
            "INSERT INTO users (user_login,user_password,user_name,idrole) values(?,?,?,2);";


    /**
     * Method
     * @param login
     *          Login fo the user
     * @param password
     *          Password of the user
     * @return user - if success , null if not
     * @throws DaoException
     */
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

    /**
     * Create users from Result set parameters
     * @param rs
     *      From this will be created a new users
     * @return new user
     * @throws SQLException
     */
    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setIdUser(rs.getInt(USER_ID_COLUMN));
        user.setLogin(rs.getString(LOGIN_COLUMN));
        user.setPassword(rs.getString(PASSWORD_COLUMN));
        user.setUsername(rs.getString(USER_NAME_COLUMN));
        user.setIdRole(rs.getInt(ROLE_ID_COLUMN));
        return user;
    }

    /**
     * Registration new user
     * <strong>Note :</strong> username and password fields have an unique fields in DB
     * and autoCommit -> false -> true
     * @param login
     *         login of the user
     * @param password
     *          password of the user
     * @param username
     *          username of the user
     * @return true - if registration successful,false - if not
     * @throws DaoException
     */
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

    /**
     * Method return list of all users to display them into administration panel
     * @return list of all users
     * @throws DaoException
     */
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

    /**
     *  Method  delete user by id
     *  from DB if all good
     *  autoCommit -> false -> true
     *  otherwise rollback
     * @param id
     *      This parameter will use to delete user
     * @return true - deleted successful,false not
     * @throws DaoException
     */
    @Override
    public boolean deleteUser(int id) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool1.getInstance().takeConnection();
            con.setAutoCommit(false);
            ;
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

    /**
     *  Update user by id
     *  from DB if all good
     *      *  autoCommit -> false -> true
     *      *  otherwise rollback
     * @param id
     *      Parameter which  uses for updating users
     * @return true - updated was successfully , false - not
     * @throws DaoException
     */
    @Override
    public boolean updateUser(int id) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res;
        try {
            con = ConnectionPool1.getInstance().takeConnection();


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

    /**
     * Method check if system has credentials of the user with inputed login
     * @param login
     * @return found user - if found,null - if user doesn't exist
     * @throws DaoException
     */
    public User findUser(String login) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = ConnectionPool1.getInstance().takeConnection();

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
