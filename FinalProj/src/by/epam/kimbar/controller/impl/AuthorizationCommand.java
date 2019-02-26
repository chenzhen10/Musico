package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String MAIN_PAGE = "index.jsp";
    private static final String INDEX_PAGE = "jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                request.setAttribute("error", "login or password are incorrect");
                page = INDEX_PAGE;

            } else {

               // request.setAttribute("user", user);
                page = MAIN_PAGE;
               // String role = "admin";
              //  HttpSession session = request.getSession(true);
               // session.setAttribute("role", role);
            }
        } catch (DaoException | ServiceException e) {
            request.setAttribute("error", "login or password are incorrect");
            log.error(e);
            page = INDEX_PAGE;
        }

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }
}
