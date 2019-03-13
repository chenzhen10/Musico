package by.epam.kimbar.entity;

import java.io.Serializable;
import java.util.Objects;

public class Music implements Serializable {
    private static final long serialVersionUID = 52L;

    private int idartist;
    private int idgenre;
    private String name;

    public Music(){}
    public Music(int idartist, int idgenre, String name) {
        this.idartist = idartist;
        this.idgenre = idgenre;
        this.name = name;
    }

    public int getIdartist() {
        return idartist;
    }

    public void setIdartist(int idartist) {
        this.idartist = idartist;
    }

    public int getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(int idgenre) {
        this.idgenre = idgenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return idartist == music.idartist &&
                idgenre == music.idgenre &&
                Objects.equals(name, music.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idartist, idgenre, name);
    }

    @Override
    public String toString() {
        return "Music{" +
                "idartist=" + idartist +
                ", idgenre=" + idgenre +
                ", name='" + name + '\'' +
                '}';
    }
}
