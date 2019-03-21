package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.util.CreatorFullURL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newLocale = request.getParameter("locale");
        request.getSession(true).setAttribute("local", newLocale);

        response.sendRedirect(String.valueOf(request.getSession(false).getAttribute("url")));
    }
}
