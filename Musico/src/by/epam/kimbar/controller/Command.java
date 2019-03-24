package by.epam.kimbar.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * This interface for all commands which will be executed in application
 * */
public interface Command {
    /**
     * Method which implements all commands
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException;
}
