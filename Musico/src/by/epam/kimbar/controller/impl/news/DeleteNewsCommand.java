package by.epam.kimbar.controller.impl.news;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.NewsService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * This class is for delete news
 */
public class DeleteNewsCommand implements Command {
    /** Logger to log exception */
    private static final Logger log = Logger.getLogger(DeleteNewsCommand.class);

    /** Redirect page */
    private static final String MAIN_PAGE = "index.jsp";

    /** Message if something go wrong */
    private static final String ERROR_MESSAGE =
            "Error occurred while deleting news id should be number and news should exists";

    /** Reg exp for numbers only  */
    private static final String REG_EXP_FOR_NUMBERS =  "[0-9]+";

    /** Attribute  if something go wrong */
    private static final String ATTRIBUTE_ERROR = "error";

    /** Attribute to delete news */
    private static final String ATTRIBUTE_DELETE_FIELD = "delete_field";


    /**
     * Method delete news by id if id doesn't exist the error message will shown
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        NewsService newsService = serviceProvider.getNewsService();
        String param = request.getParameter(ATTRIBUTE_DELETE_FIELD);
        int idNews = 0;

        if(param.matches(REG_EXP_FOR_NUMBERS)) {
           idNews = Integer.parseInt(param);
        }

        boolean res = false;

        String page = null;
        try {
            res = newsService.delete(idNews);
            if (!res) {
                page = MAIN_PAGE;
                request.getSession(true).setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE);
            } else {
                page = MAIN_PAGE;
            }
        } catch (ServiceException e) {
            page = MAIN_PAGE;
            log.error(e);
        }

        response.sendRedirect(page);
    }
}
