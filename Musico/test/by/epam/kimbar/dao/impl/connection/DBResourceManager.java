package by.epam.kimbar.dao.impl.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static String BUNDLE_NAME = "db";

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }
}
