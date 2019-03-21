package by.epam.kimbar.controller.impl.user;

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

    private static final String ATTRIBUTE_ERROR = "error";
    private static final String ATTRIBUTE_ROLE = "role";
    private static final String ATTRIBUTE_USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                request.setAttribute(ATTRIBUTE_ERROR, INVALID_CREDENTIAL);
                page = AUTH_PAGE;

                request.getRequestDispatcher(page).forward(request,response);

            } else {

                page = MAIN_PAGE;
                String role = RoleChecker.getRole(user);
                HttpSession session = request.getSession(true);
                session.setAttribute(ATTRIBUTE_ROLE, role);
                session.setAttribute(ATTRIBUTE_USER, user.getUsername());
                response.sendRedirect(page);
            }

        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE);
            log.error(e);
            page = AUTH_PAGE;
            request.getRequestDispatcher(page).forward(request,response);
        }



    }
}
