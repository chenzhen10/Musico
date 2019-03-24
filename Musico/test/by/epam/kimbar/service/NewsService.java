package by.epam.kimbar.service;

import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    List<News> news() throws ServiceException;
    List<News> paginationNews(int start, int total) throws ServiceException;
    List<News> findNews(String tag) throws ServiceException;
    boolean isCreated(String content, String filename, String tag) throws ServiceException;

    boolean delete(int idNews) throws ServiceException;


}
