package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.file.NewsWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewsCommand implements Command {
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        NewsService newsService = provider.getNewsService();

        String title = request.getParameter("title");
        String tag = request.getParameter("tag");
        String content = request.getParameter("content");


        NewsWriter.write(content, title);

        try {
            System.out.println(newsService.isCreated(title, title, tag));
        } catch (ServiceException e) {
            request.setAttribute("error", "login or password are incorrect");
            log.error(e);
        }
        response.sendRedirect("index.jsp");
    }
}
