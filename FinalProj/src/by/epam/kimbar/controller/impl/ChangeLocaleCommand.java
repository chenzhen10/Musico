package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLocale;
        HttpSession session;

        newLocale = request.getParameter("locale");


        session = request.getSession(true);
        session.setAttribute("local", newLocale);


        response.sendRedirect(request.getHeader("referer"));
    }
}
