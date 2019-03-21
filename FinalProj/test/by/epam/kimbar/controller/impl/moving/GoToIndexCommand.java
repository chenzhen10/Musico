package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.CreatorFullURL;
import by.epam.kimbar.util.TagConverter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToIndexCommand implements Command {
    private static final Logger log = Logger.getLogger(GoToIndexCommand.class);

    private static final String PATH = "jsp/default1.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final String PAGE_COUNT = "page";
    private static final int NEWS_PER_PAGE = 3;

    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_TAG = "tag";
    private static final String ATTRIBUTE_NEWS = "news";
    private static final String ATTRIBUTE_NUM_OF_PAGES = "noOfPages";
    private static final String ATTRIBUTE_CURRENT_PAGE = "currentPage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<News> newsList =  pagination(request);
            String url = CreatorFullURL.create(request);

            request.getSession(true).setAttribute(ATTRIBUTE_URL, url);
            request.setAttribute(ATTRIBUTE_TAG,TagConverter.getNameOfTag((newsList)));

            request.getRequestDispatcher(PATH).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }



    private List<News> pagination(HttpServletRequest request) throws ServiceException {
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










