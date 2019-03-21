package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.util.CreatorFullURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAuthCommand implements Command {
    private static final String PATH = "jsp/authpage.jsp";
    private static final String ATTRIBUTE_URL = "url";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(ATTRIBUTE_URL, url);
        request.getRequestDispatcher(PATH).forward(request,response);
    }
}
