package by.epam.kimbar.controller.impl.news;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.TagConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class for finding news in search bar
 */
public class FindNewsCommand implements Command {
    /** Logger to log exception */
    private static final Logger log = Logger.getLogger(FindNewsCommand.class);

    /** Message in case nothing was found  */
    private static final String MESSAGE = "Nothing was found try to find something else";

    /** Search request  */
    private static final String ATTRIBUTE_TAG = "tag";

    /** Search result */
    private static final String ATTRIBUTE_FOUND_NEWS = "findedNews";

    /** In case results are empty */
    private static final String ATTRIBUTE_EMPTY_RESULT = "empty_result";

    /** If results aren't not null  */
    private static final String PATH_IF_SEARCH_REQUEST_NOT_NULL= "jsp/default1.jsp";

    /**If results is null */
    private static final String PATH_IF_SEARCH_REQUEST_IS_NULL = "index.jsp";


    /**
     * Method for finding news if search request is not null
     * if it's empty it will set picture of sad smile and corresponding text
     * if it's null will shown all news
     * @param request
     *         Set attribute for empty result,if result is positive also set tag
     *         and all news result for null request parameter
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getNewsService()
     * @see by.epam.kimbar.service.NewsService#findNews(String)
     * @see by.epam.kimbar.util.TagConverter#getNameOfTag(List)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> news = null;
        ServiceProvider provider = ServiceProvider.getInstance();
        NewsService newsService = provider.getNewsService();

        String tag = request.getParameter(ATTRIBUTE_TAG);


        if (tag != null) {
            if (tag.length() != 0) {
                try {
                    news = newsService.findNews(tag);
                    if (!news.isEmpty()) {
                        request.setAttribute(ATTRIBUTE_FOUND_NEWS, news);
                        request.setAttribute(ATTRIBUTE_TAG, TagConverter.getNameOfTag((news)));
                    } else {
                        request.setAttribute(ATTRIBUTE_EMPTY_RESULT, MESSAGE);
                    }

                } catch (ServiceException e) {
                    log.error(e);
                }
                request.getRequestDispatcher(PATH_IF_SEARCH_REQUEST_NOT_NULL).forward(request, response);
            } else {
                request.getRequestDispatcher(PATH_IF_SEARCH_REQUEST_IS_NULL).forward(request, response);
            }
        }
    }
}
