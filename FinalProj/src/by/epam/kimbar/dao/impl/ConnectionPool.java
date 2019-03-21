package by.epam.kimbar.dao.impl;


import by.epam.kimbar.dao.exception.DaoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private ConnectionPool() {}



    public static ConnectionPool getInstance() {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }


    public Connection getConnection() throws DaoException {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/pool");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
           //log
           throw new DaoException("Error in connection pool",e);
        }
        return c;
    }

}
