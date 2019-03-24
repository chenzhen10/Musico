package by.epam.kimbar.controller;


import by.epam.kimbar.dao.exception.DaoException;

import by.epam.kimbar.dao.impl.SqlNewsDao;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class execute all our commands
 */

public class Controller extends HttpServlet {
    /** Serial version */
    private static final long serialVersionUID = 142555L;


    private static final String PARAMETER_COMMAND = "command";


    private static final String ERROR_PAGE = "error.jsp";


    private final CommandProvider provider = new CommandProvider();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);

    }

    /**
     * Method execute concrete commands which getting from request and forward to error page if command was not found
     * @param request
     *       Get command and forward to error page if error occur
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see by.epam.kimbar.controller.CommandProvider#getCommand(String)
     */
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
