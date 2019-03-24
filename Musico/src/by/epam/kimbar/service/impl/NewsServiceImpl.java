package by.epam.kimbar.service.impl;


import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.file.FileDestroyer;

import java.util.List;
/**
 * This class implements {@link by.epam.kimbar.service.NewsService}
 */
public class NewsServiceImpl implements NewsService {

    /**Error message if data is invalid or unexpected exception was thrown */
    private static final String ERROR_MESSAGE = "Service exception ";

    /**
     * Method select all news from DB and return it in list
     * @return list of all news
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.NewsDao
     * @see by.epam.kimbar.dao.NewsDao#news()
     */
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

    /**
     * Method return news from <strong>start</strong> index and amount of <strong>total<strong/>
     * @param start
     *         Start parameter from where take news
     * @param total
     *          How much news was taken
     * @return list of news
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.NewsDao
     * @see by.epam.kimbar.dao.NewsDao#paginationNews(int, int)
     */
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


    /**
     * Method get string to find news in DB and куегкт дшые ща ащгтв туцы
     * @param tag
     *          Parameter which uses to find news
     * @return list of found news
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.NewsDao
     * @see by.epam.kimbar.dao.NewsDao#findNews(String) ()
     */
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

    /**
     * Method create news , and file on HDD
     * @param content
     *         path of the file
     * @param filename
     *          name of the file and title
     * @param tag
     *         Tag of news all available tag {@link by.epam.kimbar.entity.Tag}
     * @return true - if created, false - if not
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.NewsDao
     * @see by.epam.kimbar.dao.NewsDao#create(String, String, String)
     */
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

    /**
     * Method delete news by id and save results in DB
     * @param idNews
     *          Which news will be deleted
     * @return true - news deleted , false - not
     * @throws ServiceException
     * @see by.epam.kimbar.dao.DaoProvider
     * @see by.epam.kimbar.dao.NewsDao
     * @see by.epam.kimbar.dao.NewsDao#delete(int)
     */
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
