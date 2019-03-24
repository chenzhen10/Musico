package by.epam.kimbar.util;


import org.apache.log4j.Logger;

import java.sql.*;

/**
 * This class close DB connection
 */
public class ConnectionCloser {
    /** Logger to log exceptions */
    private static final Logger log = Logger.getLogger(ConnectionCloser.class);

    /** Messages if on of parameter won't be clsoed */
    private static final String RESULT_SET_ERROR_MESSAGE = "ResultSet close exception ";
    private static final String CONNECTION_ERROR_MESSAGE = "Connection close exception ";
    private static final String STATEMENT_ERROR_MESSAGE = "PreparedStatement close exception ";

    /** private empty constructor  don't need to create an entity  */
    private ConnectionCloser() {
    }

    /**
     * Method close all main services which we used to create DB connections
     * @param con
     *          Connection
     * @param rs
     *          Result ser
     * @param prep
     *          Statement
     */
    public static void close(Connection con, ResultSet rs, Statement prep) {
        if (con != null) {
            try {
                con.close();
            }
            catch (SQLException e) {
                log.error(CONNECTION_ERROR_MESSAGE + e);
            }
        }if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error(RESULT_SET_ERROR_MESSAGE + e);
            }
        }if(prep != null){
            try {
                prep.close();
            } catch (SQLException e) {
                log.error(STATEMENT_ERROR_MESSAGE + e);
            }
        }
    }

}
