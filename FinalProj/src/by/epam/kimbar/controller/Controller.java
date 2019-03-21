package by.epam.kimbar.controller;


import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.impl.ConnectionPool;
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


public class Controller extends HttpServlet {
    private static final long serialVersionUID = 142555L;

    private static final String PARAMETER_COMMAND = "command";
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
            //error forward
        }
    }

}
