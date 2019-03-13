package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.util.cheeker.RoleChecker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String INVALID_CREDENTIAL = "There is no user with such credentials";
    private static final String ERROR_MESSAGE = "Something go wrong try again";
    private static final String MAIN_PAGE = "index.jsp";
    private static final String AUTH_PAGE = "controller?command=go_to_auth";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService service = provider.getClientService();


        User user = null;
        String page;
        try {
            user = service.authorization(login, password);

            if (user == null) {
                request.getSession(true).setAttribute("error", INVALID_CREDENTIAL);
                page = AUTH_PAGE;


            } else {

                page = MAIN_PAGE;
                String role = RoleChecker.getRole(user);
                HttpSession session = request.getSession(true);
                session.setAttribute("role", role);
                session.setAttribute("user", user.getUsername());

            }

        } catch (ServiceException e) {
            request.getSession(true).setAttribute("error", ERROR_MESSAGE);
            log.error(e);
            page = AUTH_PAGE;
        }

        response.sendRedirect(page);

    }
}
