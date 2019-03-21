package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.Tag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToCreateNewsCommand implements Command {
    private static final String PATH = "jsp/create_news.jsp";
    private static final String ATTRIBUTE_TAGS = "tags";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Tag[] tags = Tag.values();
        request.setAttribute(ATTRIBUTE_TAGS,tags);
        request.getRequestDispatcher(PATH).forward(request,response);
    }
}
