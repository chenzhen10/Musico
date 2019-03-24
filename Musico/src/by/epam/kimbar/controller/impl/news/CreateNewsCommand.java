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

/**
 * This class is for create new news command
 * */
public class CreateNewsCommand implements Command {
    /** Logger to log exceptions */
    private static final Logger log = Logger.getLogger(CreateNewsCommand.class);

    /** Name of the file and name of the news  */
    private static final String ATTRIBUTE_TITLE = "title";

    /** Tag  */
    private static final String ATTRIBUTE_TAG = "tag";

    /** Content of news which will be create */
    private static final String ATTRIBUTE_CONTENT = "content";

    /** path to redirect  if news was created */
    private static final String PATH = "index.jsp";

    /** Message if something go wrong */
    private static final String ERROR_MESSAGE = "You input incorrect data please try again";

    /** Attribute if something go wrong */
    private static final String ATTRIBUTE_ERROR = "error";


    /**
     * Method creates news and also create txt file on HDD and redirect to main page
     * @param request
     *          Set title,tag and content of the news and redirect to main page if all was done successfully
     * @param response
     * @throws IOException
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getNewsService()
     * @see by.epam.kimbar.service.NewsService#isCreated(String, String, String)
     * @see by.epam.kimbar.util.file.NewsWriter#write(String, String)
     */
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
