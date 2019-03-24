package by.epam.kimbar.controller.impl.moving;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import by.epam.kimbar.util.CreatorFullURL;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class let move to give privilege page and see all registered users
 */

public class GoToGiveAdministrationPrivilegeCommand implements Command {
    /** Logger to log exception */
    private static final Logger log = Logger.getLogger(GoToGiveAdministrationPrivilegeCommand.class);

    /** Path to give privilege page */
    private static final String PATH = "jsp/give_privilege.jsp";

    /** Attribute for previous url */
    private static final String ATTRIBUTE_URL = "url";

    /** Variable for all registered users */
    private static final String ATTRIBUTE_USERS = "users";

    /**
     * Method set all users to get them administration privilege
     * @param request  Set previous url , list of all users  and move to give privilege page
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getClientService()
     * @see by.epam.kimbar.service.ClientService
     * @see by.epam.kimbar.util.CreatorFullURL#create(HttpServletRequest)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute(ATTRIBUTE_URL, url);
        List<User> user = null;
        try {
            user = clientService.getAllUser();
        } catch (ServiceException e) {
            log.error(e);
        }
        request.getSession(true).setAttribute(ATTRIBUTE_USERS, user);
        request.getRequestDispatcher(PATH).forward(request, response);
    }
}
