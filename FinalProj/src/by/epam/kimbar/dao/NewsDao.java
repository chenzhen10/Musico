package by.epam.kimbar.dao;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;

public interface NewsDao {
    List<News> News() throws DaoException;


    List<News> PaginationNews(int start, int total) throws DaoException;
    List<News> findNews(String tag) throws DaoException;
    boolean create(String content,String filename,String tag) throws DaoException;
}
