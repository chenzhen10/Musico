package by.epam.kimbar.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final long serialVersionUID = 142555L;

    private static final String PARAMETER_COMMAND = "command";
    private static final String ERROR_PAGE = "error.jsp";
    private final CommandProvider provider = new CommandProvider();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(PARAMETER_COMMAND);


        if (commandName != null) {
            Command command = provider.getCommand(commandName);
            command.execute(request, response);
        }else{
          request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

}
