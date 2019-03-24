package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    private static String ATTRIBUTE_URL = "url";
    private static String ATTRIBUTE_LOCALE = "locale";
    private static String ATTRIBUTE_LOCAL = "local";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newLocale = request.getParameter(ATTRIBUTE_LOCALE);
        request.getSession(true).setAttribute(ATTRIBUTE_LOCAL, newLocale);

        response.sendRedirect(String.valueOf(request.getSession(false).getAttribute(ATTRIBUTE_URL)));
    }
}
