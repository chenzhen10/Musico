package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.Tag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * This class let  move to create news page
 */

public class GoToCreateNewsCommand implements Command {
    /** This variable is our path where we will move */
    private static final String PATH = "jsp/create_news.jsp";

    /** This variable contains attribute to see all available tags */
    private static final String ATTRIBUTE_TAGS = "tags";

    /**
     * @param request
     *        Set all available tags and move to create news page
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see by.epam.kimbar.entity.Tag
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Tag[] tags = Tag.values();
        request.setAttribute(ATTRIBUTE_TAGS,tags);
        request.getRequestDispatcher(PATH).forward(request,response);
    }
}
