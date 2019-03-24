package by.epam.kimbar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class News implements Serializable {
    private static final long serialVersionUID = 51L;

    private int idNews;
    private String title;
    private String content;
    private Date added_date;
    private String filePath;
    private int idTag;

    public News() {
    }

    public News(int idNews, String title, String content, Date added_date, String filePath, int idTag) {
        this.idNews = idNews;
        this.title = title;
        this.content = content;
        this.added_date = added_date;
        this.filePath = filePath;
        this.idTag = idTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAdded_date() {
        return added_date;
    }

    public void setAdded_date(Date added_date) {
        this.added_date = added_date;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }


    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

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

    @Override
    public int hashCode() {

        return Objects.hash(title, content, added_date, idTag,idNews);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", added_date=" + added_date +
                ", idTag=" + idTag +
                '}';
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
