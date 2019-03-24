package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.TagConverter;
import by.epam.kimbar.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * This class for initialization main page
 */

public class GoToIndexCommand implements Command {
    /** Logger to log exceptions  */
    private static final Logger log = Logger.getLogger(GoToIndexCommand.class);

    /** Path to main page */
    private static final String PATH = "jsp/default1.jsp";

    /** This page  if error's occur */
    private static final String ERROR_PAGE = "error.jsp";

    /**This variable to get current number of page */
    private static final String PAGE_COUNT = "page";

    /**This is amount news per page */
    private static final int NEWS_PER_PAGE = 3;

    /** Previous url attribute */
    private static final String ATTRIBUTE_URL = "url";

    /** This attribute for tag for concrete news */
    private static final String ATTRIBUTE_TAG = "tag";

    /**This attribute for set all  news */
    private static final String ATTRIBUTE_NEWS = "news";

    /** This attribute for quantities of pages   */
    private static final String ATTRIBUTE_NUM_OF_PAGES = "noOfPages";

    /** This attribute for current page */
    private static final String ATTRIBUTE_CURRENT_PAGE = "currentPage";


    /**
     *
     * @param request
     *        Set previous url , set tag of news and move to main page
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see #doPagination(HttpServletRequest)
     * @see by.epam.kimbar.util.CreatorFullURL#create(HttpServletRequest)
     * @see by.epam.kimbar.util.TagConverter#getNameOfTag(List)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<News> newsList =  doPagination(request);
            String url = CreatorFullURL.create(request);

            request.getSession(true).setAttribute(ATTRIBUTE_URL, url);
            request.setAttribute(ATTRIBUTE_TAG,TagConverter.getNameOfTag((newsList)));

            request.getRequestDispatcher(PATH).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }


    /**
     * Method for displaying concrete amount news per page
     * @param request
     * @return list of news per page
     * @throws ServiceException
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getNewsService()
     * @see by.epam.kimbar.service.NewsService#paginationNews(int, int)
     */
    private List<News> doPagination(HttpServletRequest request) throws ServiceException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        NewsService newsService = serviceProvider.getNewsService();
        int page = 0;
        int recordsPerPage = NEWS_PER_PAGE;
        if (request.getParameter(PAGE_COUNT) != null)
            page = Integer.parseInt(request.getParameter(PAGE_COUNT));

        List<News> list = newsService.paginationNews((page - 1) * recordsPerPage,
                recordsPerPage);

        int noOfRecords = newsService.news().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute(ATTRIBUTE_NEWS, list);
        request.setAttribute(ATTRIBUTE_NUM_OF_PAGES, noOfPages);
        request.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);

        return list;
    }


}










