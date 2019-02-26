package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToIndexCommand implements Command {
    private static final Logger log = Logger.getLogger(GoToIndexCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        NewsService newsService = serviceProvider.getNewsService();

        try {
            List<News> news = newsService.showNews();
            request.setAttribute("news",news);

            request.getRequestDispatcher("jsp/default.jsp").forward(request,response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("error.jsp").forward(request,response);
            log.error(e);
        }
    }
}
