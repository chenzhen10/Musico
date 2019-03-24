package by.epam.kimbar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * This class is entity of News
 */
public class News implements Serializable {
    /** Serial version */
    private static final long serialVersionUID = 51L;

    /** Fields of the News  */
    private int idNews;
    private String title;
    private String content;
    private Date added_date;
    private String filePath;
    private int idTag;

    /** Empty constructor */
    public News() {
    }

    /** Constructor with parameters */
    public News(int idNews, String title, String content, Date added_date, String filePath, int idTag) {
        this.idNews = idNews;
        this.title = title;
        this.content = content;
        this.added_date = added_date;
        this.filePath = filePath;
        this.idTag = idTag;
    }

    /** Return title of the file with news */
    public String getTitle() {
        return title;
    }

    /** Set title of the file with news */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Return filename and in the further we can read data from it */
    public String getContent() {
        return content;
    }

    /** Set filepath */
    public void setContent(String content) {
        this.content = content;
    }

    /** Return added date of the news in DB {@code CURRENT_DATE()} */
    public Date getAdded_date() {
        return added_date;
    }

    /** Set added date */
    public void setAdded_date(Date added_date) {
        this.added_date = added_date;
    }

    /** Return tag of the news */
    public int getIdTag() {
        return idTag;
    }

    /** Set tag of the news use it when  create news */
    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }


    /** Return id of the news */
    public int getIdNews() {
        return idNews;
    }

    /** Set news id use it when  create news  */
    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    /** Return file path to read news */
    public String getFilePath() {
        return filePath;
    }

    /** Set file path use it when  create news */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /** Check if the news equals another one   */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return idTag == news.idTag &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(added_date, news.added_date);
    }

    /** Check the hashcode of the news */
    @Override
    public int hashCode() {
        return Objects.hash(title, content, added_date, idTag,idNews);
    }

    /** Default display of the news */
    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", added_date=" + added_date +
                ", idTag=" + idTag +
                '}';
    }


}
