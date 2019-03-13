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

    private static final String ERROR_MESSAGE = "User with this credentials already exists please input another data";
    private static final String INVALID_DATA = "Login or password are incorrect! Use latin symbols and numbers";

    private static final String REGISTRATION_SUCCESSFUL_PAGE = "index.jsp";
    private static final String REGISTRATION_PAGE = "controller?command=go_to_registration_page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                request.getSession(true).setAttribute("error", INVALID_DATA);
                page = REGISTRATION_PAGE;
            } else {
                page = REGISTRATION_SUCCESSFUL_PAGE;
                HttpSession session = request.getSession(true);
                session.setAttribute("user", username);
                session.setAttribute("done",true);

            }
        } catch (ServiceException e) {
            request.getSession(true).setAttribute("error", ERROR_MESSAGE);
            log.error(e);

            page = REGISTRATION_PAGE;
        }
        response.sendRedirect(page);
    }
}
