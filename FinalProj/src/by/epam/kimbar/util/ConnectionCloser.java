package by.epam.kimbar.util;


import org.apache.log4j.Logger;

import java.sql.*;

public class ConnectionCloser {
    private static final Logger log = Logger.getLogger(ConnectionCloser.class);
    public static void close(Connection con, ResultSet rs, Statement prep) {
        if (con != null) {
            try {
                con.close();
            }
            catch (SQLException e) {
                log.error("Connection close exception " + e);
            }
        }if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("ResultSet close exception " + e);
            }
        }if(prep != null){
            try {
                prep.close();
            } catch (SQLException e) {
                log.error("PreparedStatement close exception " + e);
            }
        }
    }

    private ConnectionCloser() {
    }
}
