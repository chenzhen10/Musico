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

/**
 * This class is used to log in to the system
 *
 */
public class AuthorizationCommand implements Command {
    /**Logger to log exceptions */
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

    /** Attribute to get login */
    private static final String PARAMETER_LOGIN = "login";

    /** Attribute to get password */
    private static final String PARAMETER_PASSWORD = "password";

    /** Message if credentials aren't correct */
    private static final String INVALID_CREDENTIAL = "There is no user with such credentials please sign up";

    /** Message if exception will occur */
    private static final String ERROR_MESSAGE = "Something go wrong try again";

    /** Main page */
    private static final String MAIN_PAGE = "index.jsp";

    /** If credentials are incorrect we will redirect  to authentication page again */
    private static final String AUTH_PAGE = "controller?command=go_to_auth";

    /** Attribute to set error message */
    private static final String ATTRIBUTE_ERROR = "error";

    /** Attribute to set role  */
    private static final String ATTRIBUTE_ROLE = "role";

    /** Attribute to set username when credentials is ok */
    private static final String ATTRIBUTE_USER = "user";


    /**
     * Method for authorization user
     * here we find data in database and if it exists we will log in to the system if not
     * we will get message to sign up in system or if exceptions will occur we will get corresponding message
     *
     * @param request
     *          Set error message, and forward if credentials aren't correct,
     *          redirect to main page set role username when credentials are correct
     *          and logging and forward to authentication page if credentials aren't correct
     * @param response
     * @throws IOException
     * @throws ServletException
     * @see by.epam.kimbar.util.cheeker.RoleChecker#getRole(User)
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getClientService()
     * @see by.epam.kimbar.service.ClientService#authorization(String, String)
     */

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
