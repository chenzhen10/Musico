package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class for invalidation current session
 */
public class LogOutCommand implements Command {
    /** Path where need to redirect */
    private static final String PATH = "index.jsp";

    /**
     * Method to invalidate session
     * @param request
     *          Invalidate session
     * @param response
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(false).invalidate();
        response.sendRedirect(PATH);
    }
}
