package by.epam.kimbar.util.password;

import by.epam.kimbar.entity.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * This class encrypt the password by JBcrypt library
 */
public class PasswordEncryptor {
    /** private empty constructor  don't need to create an entity  */
    private PasswordEncryptor(){}

    /**
     * Method encrypt password
     * @param password
     *         Password which must be encrypted
     * @return encrypted password
     */
    public static String encryptPassword(String password)  {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }


}
