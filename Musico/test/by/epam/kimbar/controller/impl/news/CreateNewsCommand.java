package by.epam.kimbar.controller.impl.news;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.file.NewsWriter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewsCommand implements Command {
    private static final Logger log = Logger.getLogger(CreateNewsCommand.class);

    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_TAG = "tag";
    private static final String ATTRIBUTE_CONTENT = "content";
    private static final String PATH = "index.jsp";
    private static final String ERROR_MESSAGE = "You input incorrect data please try again";
    private static final String ATTRIBUTE_ERROR = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        NewsService newsService = provider.getNewsService();

        String title = request.getParameter(ATTRIBUTE_TITLE);
        String tag = request.getParameter(ATTRIBUTE_TAG);
        String content = request.getParameter(ATTRIBUTE_CONTENT);


        NewsWriter.write(content, title);

        try {
            newsService.isCreated(title, title, tag);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE);
            log.error(e);
        }
        response.sendRedirect(PATH);
    }
}
