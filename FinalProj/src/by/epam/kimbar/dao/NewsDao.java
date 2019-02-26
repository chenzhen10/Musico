package by.epam.kimbar.dao;

import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;

import java.util.List;

public interface NewsDao {
    List<News> showNews() throws DaoException;
    List<News> findNews(String tag) throws DaoException;
}
