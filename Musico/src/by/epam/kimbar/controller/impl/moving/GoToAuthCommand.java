package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.util.CreatorFullURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class let  move to authentication page
 */

public class GoToAuthCommand implements Command {
    /** This variable is our path where we will move */
    private static final String PATH = "jsp/authpage.jsp";
    /** This variable contains attribute to set url */
    private static final String ATTRIBUTE_URL = "url";

    /**
     * @param request
     *        Set previous url to change locale and also move us to authentication page
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see by.epam.kimbar.util.CreatorFullURL#create(HttpServletRequest)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(ATTRIBUTE_URL, url);
        request.getRequestDispatcher(PATH).forward(request,response);
    }
}
