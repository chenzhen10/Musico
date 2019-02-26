package by.epam.kimbar.util;

import java.sql.*;

public class ConnectionCloser {
    public static void close(Connection con, ResultSet rs, Statement prep) {
        if (con != null && rs != null && prep != null) {
            try {
                con.close();
                rs.close();
                prep.close();
            } catch (SQLException e) {
                //log

            }
        }
    }

    private ConnectionCloser() {
    }
}
