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
import java.util.Arrays;

/**
 * This class was created to give administrator privilege if  necessary
 */
public class GiveAdministrationPrivilegeCommand implements Command {

    /**Logger to log exceptions */
    private static final Logger log = Logger.getLogger(GiveAdministrationPrivilegeCommand.class);

    /** Attribute to get previous url */
    private static String ATTRIBUTE_URL = "url";

    /** Attribute to set privilege */
    private static String ATTRIBUTE_PRIVILEGE = "privilege";

    /** Reg exp to check numbers */
    private static final String REG_EXP_FOR_NUMBERS = "[0-9]+";

    /** Id of immutable user */
    private static final String NUMBER_OF_ID_WHICH_CANT_BE_CHANGED = "1";

    /** Attribute to set error if necessary  */
    private static final String ATTRIBUTE_ERROR = "error";

    /** Error message if input was incorrect */
    private static final String ERROR_MESSAGE = "Please input integer here and also you can't upgrade user with 1 id ";

    /** Error message2 if input was not an integer */
    private static final String ERROR_MESSAGE2 = "Please input correct data";


    /**
     * Method to update user by id
     * we find data in database and if it exists we give user administrator privilege
     * otherwise we set error message where will shown what was the problem
     * @param request
     *         Get userId,set error attribute if necessary and set previous url
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see  by.epam.kimbar.service.ServiceProvider#getInstance()
     * @see  by.epam.kimbar.service.ServiceProvider#getClientService()
     * @see   by.epam.kimbar.service.ClientService#updateUser(int)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        String userId = request.getParameter(ATTRIBUTE_PRIVILEGE);


        boolean res = false;
        if (userId.matches(REG_EXP_FOR_NUMBERS) && !userId.matches(NUMBER_OF_ID_WHICH_CANT_BE_CHANGED)) {
            try {
                res = clientService.updateUser(Integer.parseInt(userId));
                if(res){
                    request.getSession(true).setAttribute(ATTRIBUTE_ERROR, "");
                }else{
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
