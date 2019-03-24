package by.epam.kimbar.util.cheeker;

import by.epam.kimbar.entity.User;

/**
 * This class is for determine role name
 */
public class RoleChecker {
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_USER = "User";

    private static final int ADMIN_ID = 1;
    private static final int USER_ID = 2;

    /** private empty constructor  don't need to create an entity  */
    private RoleChecker() {
    }

    /**
     * Method return name of the role from user
     * @param user
     *         user from where we get our role name
     * @return Return name of the role
     */
    public static String getRole(User user) {
        String role = null;
        if (user.getIdRole() == ADMIN_ID) {
            role = ROLE_ADMIN;
        } else if (user.getIdRole() == USER_ID) {
            role = ROLE_USER;
        }
        return role;
    }

}
