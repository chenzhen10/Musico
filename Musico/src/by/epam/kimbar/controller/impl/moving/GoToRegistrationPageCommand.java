package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * This class for moving to registration page
 * */
public class GoToRegistrationPageCommand implements Command {
    /** This attribute for path to registration page */
    private static final String PATH ="jsp/registration.jsp";

    /**
     * Method for moving to registration page
     * @param request
     *        To move to registration page
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PATH).forward(request,response);
    }
}
