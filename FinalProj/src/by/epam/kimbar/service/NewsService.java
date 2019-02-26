package by.epam.kimbar.service;

import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
    List<News> showNews() throws ServiceException;
    List<News> findNews(String tag) throws ServiceException;
}
