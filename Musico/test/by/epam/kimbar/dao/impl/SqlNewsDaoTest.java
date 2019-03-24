package by.epam.kimbar.dao.impl;

import by.epam.kimbar.dao.DaoProvider;
import by.epam.kimbar.dao.NewsDao;
import by.epam.kimbar.dao.exception.DaoException;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SqlNewsDaoTest {
    private static final Logger log = Logger.getLogger(SqlNewsDaoTest.class);
    private static DaoProvider provider = null;
    private static NewsDao newsDao = null;

    @BeforeClass
    public static void init() {
        provider = DaoProvider.getInstance();
        newsDao = provider.getNewsDao();
    }

    @Test
    public void news() {
        int expectedId = 1;
        int resultId = 0;
        try {
            resultId = newsDao.news().get(0).getIdNews();
        } catch (DaoException e) {
           log.error(e);
        }

        assertEquals(expectedId, resultId);
    }

    @Test
    public void paginationNews() {
        int expectedResult = 2;
        int result = 2;


        try {
           result = newsDao.paginationNews(1,2).size();
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void findNews() {
        int result = 0;
        int expectedResult = 3;
        try {
            result = newsDao.findNews("HipHop").size();
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void create() {
        boolean result = false ;
        boolean expectedResult = true;
        try {
           result =  newsDao.create("This is test text","TestNews12","Jazz");
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void delete() {
        boolean result = false;
        boolean expectedResult = true;
        try {
            result =  newsDao.delete(16);
        } catch (DaoException e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }

    @Test
    public void findNewsById() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = null;
        Date date = null;
        String expectedResult = null;
        try {
             date = dateFormat.parse("2019-03-18");
             expectedResult = dateFormat.format(date);
             result =  newsDao.findNewsById(12).getAdded_date().toString();
        } catch (DaoException | ParseException  e) {
            log.error(e);
        }
        assertEquals(expectedResult,result);
    }
}