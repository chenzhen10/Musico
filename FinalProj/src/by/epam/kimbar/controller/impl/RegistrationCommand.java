package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_USERNAME = "username";

    private static final String MAIN_PAGE = "index.jsp";
    private static final String INDEX_PAGE = "jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;
        String username;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);
        username = request.getParameter(PARAMETER_USERNAME);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();

        boolean newUser;
        String page;
        try {
            newUser = service.isRegistrationSucceed(login, password, username);

            if (!newUser) {
                request.setAttribute("error", "login or password are incorrect!");
                page = INDEX_PAGE;
            } else {
                page = MAIN_PAGE;
                String role = "admin";
                HttpSession session = request.getSession(true);
                session.setAttribute("role", role);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "login or password are incorrect!!");
            log.error(e);

            page = INDEX_PAGE;
        }

        String url = CreatorFullURL.create(request);
        request.getSession(false).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
