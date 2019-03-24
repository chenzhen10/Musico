package by.epam.kimbar.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is entity of User
 */
public class User implements Serializable {
    /** Serial version */
    private static final long serialVersionUID = 50L;

    /** Fields of the User  */
    private int idUser;
    private String login;
    private String password;
    private String username;
    private int idRole;

    /** Empty constructor */
    public User() {
    }

    /** Constructor with parameters */
    public User(int idUser, String login, String password, String username, int idRole) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.username = username;
        this.idRole = idRole;
    }


    /** Return login of the user */
    public String getLogin() {
        return login;
    }

    /** Set users login */
    public void setLogin(String login) {
        this.login = login;
    }

    /** Return password of the user */
    public String getPassword() {
        return password;
    }

    /** Set password of user */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Return username */
    public String getUsername() {
        return username;
    }

    /** Set username */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Return id role 1-admin,2-user */
    public int getIdRole() {
        return idRole;
    }

    /** Set id role 1-admin,2-user */
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    /** Return id of the user */
    public int getIdUser() {
        return idUser;
    }

    /** Set users id */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /** Check if the user equals another one   */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idRole == user.idRole &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(username, user.username);
    }

    /** Check the hashcode of the user */
    @Override
    public int hashCode() {
        return Objects.hash(login, password, username, idRole);
    }

    /** Default display of the user */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", idRole=" + idRole +
                '}';
    }


}
