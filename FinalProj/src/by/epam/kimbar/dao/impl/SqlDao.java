package by.epam.kimbar.dao.impl;

//TO-DO DELETE THIS

public abstract class SqlDao {
//    protected static final String driver;
//    protected static final String url;
//    protected static final String login;
//    protected static final String password;

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";

    private static final String DB_PROPERTIES_PATH = "db" ;

    static {

//        ResourceBundle res = ResourceBundle.getBundle(DB_PROPERTIES_PATH);
//        driver = res.getString(DB_DRIVER);
//        url = res.getString(DB_URL);
//        login = res.getString(DB_LOGIN);
//        password = res.getString(DB_PASSWORD);
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//
//            //logger
//        }
    }

    public SqlDao() {
    }
}
