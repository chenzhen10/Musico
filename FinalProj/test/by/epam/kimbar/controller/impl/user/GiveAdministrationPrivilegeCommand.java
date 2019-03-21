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

public class GiveAdministrationPrivilegeCommand implements Command {
    private static final Logger log = Logger.getLogger(GiveAdministrationPrivilegeCommand.class);
    private static String ATTRIBUTE_URL = "url";
    private static String ATTRIBUTE_PRIVILEGE = "privilege";
    private static final String REG_EXP_FOR_NUMBERS = "[0-9]+";
    private static final String NUMBER_OF_ID_WHICH_CANT_BE_CHANGED = "1";
    private static final String ATTRIBUTE_ERROR = "error";
    private static final String ERROR_MESSAGE = "Please input integer here and also you can't upgrade user with 1 id ";
    private static final String ERROR_MESSAGE2 = "Please input correct data";

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
