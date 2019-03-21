package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.file.NewsFileReader;
import by.epam.kimbar.util.cheeker.TagChecker;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlNewsDao implements NewsDao {
    private static final String PATH = "D:\\News\\";
    private static final String FILE_EXTENSION = ".txt";
    private static final int NUMBER_OF_VALID_OPERATION = 1;
    private static final String QUERY_TO_SHOW_ALL_NEWS = "SELECT * FROM news;";
    private static final String QUERY_TO_FIND_NEWS_BY_TAG = "SELECT DISTINCT *\n" +
            "FROM news n WHERE n.idtag = (SELECT idtag\n" +
            "                                     FROM tags\n" +
            "                                     where tag_name like ?) OR n.title = ? ORDER BY n.added_date DESC ;";


    private static final String QUERY_TO_CREATE_NEWS
            = "INSERT INTO news (title, content, added_date, idtag) VALUES (?,?,CURRENT_DATE(),?);";


    @Override
    public List<News> News() throws DaoException {
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
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, st);
        }

        return news;
    }


    @Override
    public List<News> PaginationNews(int start, int total) throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM news  LIMIT " + (start) + "," + total);
            while (rs.next()) {
                news.add(createNews(rs));
            }
        } catch (DaoException | SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, st);
        }

        return news;
    }

    @Override
    public List<News> findNews(String searchParameter) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        List<News> findedNews = new ArrayList<>();
        News news = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            prepSt = con.prepareStatement(QUERY_TO_FIND_NEWS_BY_TAG);

            prepSt.setString(1, searchParameter);
            prepSt.setString(2, searchParameter);

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

    @Override
    public boolean create(String filename, String content, String tag) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res = 0;
        try {
            con = ConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            prepSt = con.prepareStatement(QUERY_TO_CREATE_NEWS);

            prepSt.setString(1, filename);
            prepSt.setString(2,PATH +  content + FILE_EXTENSION);
            prepSt.setInt(3, TagChecker.check(tag));

           res = prepSt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException  e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);
        }
        return res ==  NUMBER_OF_VALID_OPERATION;
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
