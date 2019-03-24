package by.epam.kimbar.controller.impl.user;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *This class is for registration new user
 */
public class RegistrationCommand implements Command {
    /** Logger to log exceptions */
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    /** Attribute login */
    private static final String PARAMETER_LOGIN = "login";

    /** Attribute password*/
    private static final String PARAMETER_PASSWORD = "password";

    /**  Attribute username */
    private static final String PARAMETER_USERNAME = "username";

    /** Error message if user already exists */
    private static final String ERROR_MESSAGE = "User with this credentials already exists please input another data";

    /** If data is incorrect */
    private static final String INVALID_DATA = "Login or password are incorrect! Use latin symbols and numbers";

    /** If registration is successfull */
    private static final String REGISTRATION_SUCCESSFUL_PAGE = "index.jsp";

    /** If registration unsuccessful */
    private static final String REGISTRATION_PAGE = "controller?command=go_to_registration_page";

    /** Attribute error */
    private static final String ATTRIBUTE_ERROR = "error";

    /** Attribute to set username in welcome message */
    private static final String ATTRIBUTE_USER = "user";

    /** If registration is successfully */
    private static final String ATTRIBUTE_DONE = "done";


    /**
     * Method for registrations user
     * if data was correct user successfully will be registered and pop p window will be shown
     * if data was incorrect error message will be sown
     * @param request
     *        Get login,password,username set username,successfully registration attribute(done)
     *        and error message if necessary
     * @param response
     * @throws IOException
     * @throws ServletException
     * @see  by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see  by.epam.kimbar.service.ServiceProvider#getClientService()
     * @see  by.epam.kimbar.service.ClientService#isRegistrationSucceed(String, String, String)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                request.setAttribute(ATTRIBUTE_ERROR, INVALID_DATA);
                page = REGISTRATION_PAGE;

                request.getRequestDispatcher(page).forward(request,response);

            } else {
                page = REGISTRATION_SUCCESSFUL_PAGE;
                HttpSession session = request.getSession(true);
                session.setAttribute(ATTRIBUTE_USER, username);
                session.setAttribute(ATTRIBUTE_DONE, true);

                response.sendRedirect(page);
            }
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE + " or . " + INVALID_DATA);
            log.error(e);

            page = REGISTRATION_PAGE;
            request.getRequestDispatcher(page).forward(request,response);
        }

    }
}
