package by.epam.kimbar.controller.impl.news;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.util.TagConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindNewsCommand implements Command {
    private static final Logger log = Logger.getLogger(FindNewsCommand.class);

    private static final String MESSAGE = "Nothing was found try to find something else";
    private static final String ATTRIBUTE_TAG = "tag";
    private static final String ATTRIBUTE_FINDED_NEWS = "findedNews";
    private static final String ATTRIBUTE_EMPTY_RESULT = "empty_result";
    private static final String PATH_IF_SEARCH_REQUEST_NOT_NULL= "jsp/default1.jsp";
    private static final String PATH_IF_SEARCH_REQUEST_IS_NULL = "index.jsp";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        String tag = request.getParameter(ATTRIBUTE_TAG);


        if (tag != null) {
            if (tag.length() != 0) {
                try {
                    news = newsDao.findNews(tag);
                    if (!news.isEmpty()) {
                        request.setAttribute(ATTRIBUTE_FINDED_NEWS, news);
                        request.setAttribute(ATTRIBUTE_TAG, TagConverter.getNameOfTag((news)));
                    } else {
                        request.setAttribute(ATTRIBUTE_EMPTY_RESULT, MESSAGE);
                    }

                } catch (DaoException e) {
                    log.error(e);
                }
                request.getRequestDispatcher(PATH_IF_SEARCH_REQUEST_NOT_NULL).forward(request, response);
            } else {
                request.getRequestDispatcher(PATH_IF_SEARCH_REQUEST_IS_NULL).forward(request, response);
            }
        }
    }
}
