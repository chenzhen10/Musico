package by.epam.kimbar.util.password;

import by.epam.kimbar.entity.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * This class contains a method which validate has of password
 */
public class PasswordValidator {
    /** private empty constructor  don't need to create an entity  */
    private  PasswordValidator(){}

    /**
     * Method validate has which was get from hashing password by JBcrypt
     * @param password
     * @param user
     * @return boolean - if password the same , false - if not
     */
    public static boolean isPasswordCorrect (String password, User user){
        return BCrypt.checkpw(password,user.getPassword());
    }
}
