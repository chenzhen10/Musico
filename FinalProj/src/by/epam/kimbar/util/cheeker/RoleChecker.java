package by.epam.kimbar.util.cheeker;

import by.epam.kimbar.entity.User;

public class RoleChecker {

    private RoleChecker() {
    }



    public static String getRole(User user) {
        String role = null;
        if (user.getIdRole() == 1) {
            role = "admin";
        } else if (user.getIdRole() == 2) {
            role = "user";
        }
        return role;
    }

}
