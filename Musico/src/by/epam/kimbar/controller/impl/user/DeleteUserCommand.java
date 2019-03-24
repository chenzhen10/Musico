package by.epam.kimbar.controller.impl.user;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.service.ClientService;
import by.epam.kimbar.service.ServiceProvider;
import by.epam.kimbar.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class was created to delete users
 *
 */
public class DeleteUserCommand implements Command {
    /**Logger to log exceptions */
    private static final Logger log = Logger.getLogger(DeleteUserCommand.class);

    /** Error message if input was incorrect */
    private static final String ERROR_MESSAGE = "Please input integer here and also you can't delete user with 1 id ";

    /** Error message2 if input was not an integer */
    private static final String ERROR_MESSAGE2 = "Please input correct data";

    /** This attribute uses to delete user */
    private static final String ATTRIBUTE_ID = "id";

    /** Use to drop a NAN data */
    private static final String REG_EXP_FOR_NUMBERS = "[0-9]+";

    /** The root user who can't be updated or deleted */
    private static final String NUMBER_OF_ID_WHICH_CANT_BE_CHANGED = "1";

    /**  Attribute if error occur  */
    private static final String ATTRIBUTE_ERROR = "error";

    /** Attribute to get previous url */
    private static final String ATTRIBUTE_URL = "url";

    /**
     * Method to delete user by id
     * we find data in database and if it exists we delete him otherwise we set error message where will shown
     * what was the problem
     * @param request
     *          Get userId,set attribute corresponding error message if necessary
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see by.epam.kimbar.service.ServiceProvider#getClientService()
     * @see by.epam.kimbar.service.ClientService#deleteUser(int)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        String userId = request.getParameter(ATTRIBUTE_ID);

        boolean res = false;
        if (userId.matches(REG_EXP_FOR_NUMBERS) && !userId.matches(NUMBER_OF_ID_WHICH_CANT_BE_CHANGED)) {
            try {
                res = clientService.deleteUser(Integer.parseInt(userId));
                if(res){
                    request.getSession(true).setAttribute(ATTRIBUTE_ERROR, "");
                }else {
                    request.getSession(true).setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE2);
                }

            } catch (ServiceException e) {
                log.error(e);
            }
        } else {
            request.getSession(true).setAttribute(ATTRIBUTE_ERROR, ERROR_MESSAGE);
        }
        response.sendRedirect(String.valueOf(request.getSession(false).getAttribute(ATTRIBUTE_URL)));
    }
}
