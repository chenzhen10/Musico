package by.epam.kimbar.service.impl;


import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    @Override
    public List<News> showNews() throws ServiceException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        try {
            news = newsDao.showNews();
        } catch (DaoException e) {
            //log
            throw new ServiceException("Service exception " ,e);
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
            throw new ServiceException("Service exception " ,e);
        }
        return news;
    }
}
