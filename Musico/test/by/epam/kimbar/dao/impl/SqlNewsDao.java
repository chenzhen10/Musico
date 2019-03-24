package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import by.epam.kimbar.dao.impl.connection.ConnectionPool1;
import by.epam.kimbar.entity.News;
import by.epam.kimbar.util.ConnectionCloser;
import by.epam.kimbar.util.cheeker.TagChecker;
import by.epam.kimbar.util.file.NewsFileReader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlNewsDao implements NewsDao {
    private static final String PATH = "D:\\News\\";
    private static final String FILE_EXTENSION = ".txt";
    private static final int NUMBER_OF_VALID_OPERATION = 1;

    private static final String NEWS_ID_COLUMN = "idnews";
    private static final String TITLE_COLUMN = "title";
    private static final String FILE_PATH_COLUMN = "content";
    private static final String CONTENT_COLUMN = "content";
    private static final String ADDED_DATE_COLUMN = "added_date";
    private static final String ID_TAG_COLUMN = "idtag";
    private static final String QUERY_TO_SHOW_ALL_NEWS = "SELECT * FROM news;";
    private static final String QUERY_TO_FIND_NEWS_BY_TAG = "SELECT DISTINCT *\n" +
            "FROM news n WHERE n.idtag = (SELECT idtag\n" +
            "                                     FROM tags\n" +
            "                                     where tag_name like ?) OR n.title = ? ORDER BY n.added_date DESC ;";


    private static final String QUERY_TO_CREATE_NEWS
            = "INSERT INTO news (title, content, added_date, idtag) VALUES (?,?,CURRENT_DATE(),?);";

    private static final String QUERY_TO_DELETE_NEWS = "DELETE FROM news WHERE idnews = ? ;";

    private static final String QUERY_TO_FIND_NEWS_PATH_BY_TAG = "SELECT * FROM news WHERE idnews = ?";


    @Override
    public List<News> news() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try {
            con =  ConnectionPool1.getInstance().takeConnection();;
            st = con.createStatement();
            rs = st.executeQuery(QUERY_TO_SHOW_ALL_NEWS);
            while (rs.next()) {
                news.add(createNews(rs));
            }

        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, st);
        }

        return news;
    }


    @Override
    public List<News> paginationNews(int start, int total) throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try {
            con =  ConnectionPool1.getInstance().takeConnection();;

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM news  LIMIT " + (start) + "," + total);
            while (rs.next()) {
                news.add(createNews(rs));
            }

        } catch (SQLException | IOException e) {
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
            con =  ConnectionPool1.getInstance().takeConnection();;
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
            con =  ConnectionPool1.getInstance().takeConnection();;
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

    @Override
    public boolean delete(int idNews) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        int res = 0;
        try {
            con =  ConnectionPool1.getInstance().takeConnection();;
            con.setAutoCommit(false);
            prepSt = con.prepareStatement(QUERY_TO_DELETE_NEWS);

            prepSt.setString(1, String.valueOf(idNews));


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



    @Override
    public News findNewsById(int id) throws DaoException {
        Connection con = null;
        PreparedStatement prepSt = null;
        ResultSet rs = null;
        News news = null;
        try {
            con =  ConnectionPool1.getInstance().takeConnection();;
            prepSt = con.prepareStatement(QUERY_TO_FIND_NEWS_PATH_BY_TAG);

            prepSt.setInt(1, id);

            rs = prepSt.executeQuery();

            while (rs.next()) {
                news = createNews(rs);
            }

        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        } finally {
            ConnectionCloser.close(con, rs, prepSt);

        }
        return news;
    }

    private News createNews(ResultSet rs) throws SQLException, IOException {
        News news = new News();
        news.setIdNews(rs.getInt(NEWS_ID_COLUMN));
        news.setTitle(rs.getString(TITLE_COLUMN));
        news.setFilePath(rs.getString(FILE_PATH_COLUMN));
        news.setContent(NewsFileReader.readData(rs.getString(CONTENT_COLUMN)));
        news.setAdded_date(rs.getDate(ADDED_DATE_COLUMN));
        news.setIdTag(rs.getInt(ID_TAG_COLUMN));
        return news;
    }


}
