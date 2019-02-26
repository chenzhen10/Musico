package by.epam.kimbar.controller.impl;

import by.epam.kimbar.controller.Command;
import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindNewsCommand implements Command {
    private static final Logger log = Logger.getLogger(FindNewsCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> news = null;
        DaoProvider daoProvider = DaoProvider.getInstance();
        NewsDao newsDao = daoProvider.getNewsDao();

        String tag = request.getParameter("tag");

        try {
           news =  newsDao.findNews(tag);
           request.setAttribute("findedNews",news);
        } catch (DaoException e) {
            log.error(e);
        }

        request.getRequestDispatcher("jsp/default.jsp").forward(request,response);
    }
}
