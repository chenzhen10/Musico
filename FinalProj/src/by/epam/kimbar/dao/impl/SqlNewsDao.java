package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.entity.User;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.NewsFileReader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlNewsDao implements NewsDao {
    private static final String QUERY_TO_SHOW_ALL_NEWS = "SELECT * FROM news;";
    private static final String QUERY_TO_FIND_NEWS_BY_TAG = "SELECT DISTINCT *\n" +
            "FROM news n WHERE n.idtag = (SELECT idtag\n" +
            "                                     FROM tags\n" +
            "                                     where tag_name like ?) ORDER BY n.added_date DESC ;";

    @Override
    public List<News> showNews() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(QUERY_TO_SHOW_ALL_NEWS);
            while (rs.next()) {
                news.add(createNews(rs));
            }

        } catch (DaoException | SQLException | IOException e) {
            //log
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, st);
        }

        return news;
    }

    @Override
    public List<News> findNews(String tag) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        List<News> findedNews = new ArrayList<>();
        News news = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            prepSt = con.prepareStatement(QUERY_TO_FIND_NEWS_BY_TAG);

            prepSt.setString(1, tag);


            rs = prepSt.executeQuery();

            while (rs.next()) {
                news = createNews(rs);
                findedNews.add(news);
            }
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }
        return findedNews;
    }

    private News createNews(ResultSet rs) throws SQLException, IOException {
        News news = new News();
        news.setTitle(rs.getString("title"));
        news.setContent(NewsFileReader.readData(rs.getString("content")));
        news.setAdded_date(rs.getDate("added_date"));
        news.setIdTag(rs.getInt("idtag"));
        return news;
    }


}
