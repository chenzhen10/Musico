package by.epam.kimbar.dao.impl.connection;

import java.util.ResourceBundle;

/**
 * This class is for resources for DB
 */
public class DBResourceManager {
    /** Name of the bundle*/
    private final static String BUNDLE_NAME = "db";
    /** Make our class singleton*/
    private final static DBResourceManager instance = new DBResourceManager();

    /** To get value via method */
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    /** Get our instance */
    public static DBResourceManager getInstance(){
        return instance;
    }

    /** Get value via method */
    public String getValue(String key){
        return resourceBundle.getString(key);
    }
}
