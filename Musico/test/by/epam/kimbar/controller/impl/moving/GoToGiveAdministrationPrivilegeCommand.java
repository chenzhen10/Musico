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

public class GoToGiveAdministrationPrivilegeCommand implements Command {
    private static final String PATH = "jsp/give_privilege.jsp";
    private static final Logger log = Logger.getLogger(GoToGiveAdministrationPrivilegeCommand.class);
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_USERS = "users";
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
