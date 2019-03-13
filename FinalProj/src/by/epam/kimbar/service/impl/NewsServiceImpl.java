package by.epam.kimbar.service.impl;


import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private static final String ERROR_MESSEGE = "Service exception ";

    @Override
    public List<News> news() throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.News();
        } catch (DaoException e) {
            throw new ServiceException(ERROR_MESSEGE ,e);
        }
        return news;
    }

    @Override
    public List<News> paginationNews(int start, int total) throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.PaginationNews(start,total);
        } catch (DaoException e) {

            throw new ServiceException(ERROR_MESSEGE ,e);
        }
        return news;
    }



    @Override
    public List<News> findNews(String tag) throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.findNews(tag);
        } catch (DaoException e) {

            throw new ServiceException(ERROR_MESSEGE ,e);
        }
        return news;
    }

    @Override
    public boolean isCreated(String content, String filename, String tag) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();
        boolean res;
        try {
            res =  newsDao.create(content,filename,tag);
        } catch (DaoException e) {
           throw new ServiceException(e);
        }
        return res;
    }
}
