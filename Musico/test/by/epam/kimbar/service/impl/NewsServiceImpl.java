package by.epam.kimbar.service.impl;


import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.file.FileDestroyer;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private static final String ERROR_MESSAGE = "Service exception ";

    @Override
    public List<News> news() throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.news();
        } catch (DaoException e) {
            throw new ServiceException(ERROR_MESSAGE, e);
        }
        return news;
    }

    @Override
    public List<News> paginationNews(int start, int total) throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.paginationNews(start, total);
        } catch (DaoException e) {

            throw new ServiceException(ERROR_MESSAGE, e);
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

            throw new ServiceException(ERROR_MESSAGE, e);
        }
        return news;
    }

    @Override
    public boolean isCreated(String content, String filename, String tag) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();
        boolean res;
        try {
            res = newsDao.create(content, filename, tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return res;
    }

    @Override
    public boolean delete(int idNews) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();
        boolean res = false;
        boolean res1;
        try {
            News path = newsDao.findNewsById(idNews);
            if (path != null) {
                res1 = FileDestroyer.delete(path.getFilePath());
                if (res1) {
                    res = newsDao.delete(idNews);
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return res ;
    }


}
