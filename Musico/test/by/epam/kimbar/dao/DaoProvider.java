package by.epam.kimbar.dao;

import by.epam.kimbar.dao.impl.SqlNewsDao;
import by.epam.kimbar.dao.impl.SqlUserDao;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private final UserDao sqlUserDao = new SqlUserDao();
    private final NewsDao newsDao = new SqlNewsDao();

    private DaoProvider(){}

    public static DaoProvider getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return sqlUserDao;
    }

    public NewsDao getNewsDao(){return newsDao;}
}
