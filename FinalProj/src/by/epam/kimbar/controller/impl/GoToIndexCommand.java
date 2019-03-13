package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.Converter;
import by.epam.kimbar.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GoToIndexCommand implements Command {
    private static final Logger log = Logger.getLogger(GoToIndexCommand.class);

    private static final String PATH = "jsp/default1.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final String PAGE_COUNT = "page";
    private static final int NEWS_PER_PAGE = 3;



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            List<News> newsList =  pagination(request);
            String url = CreatorFullURL.create(request);

            request.getSession(true).setAttribute("url", url);



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

        request.setAttribute("news", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return list;
    }

    private List<String> getTag(List<News> newsList){
        List<String> tagList = new ArrayList<>();
        for(News news : newsList){
            tagList.add(Converter.convert(news.getIdTag()));
        }
        return tagList;
    }
}










