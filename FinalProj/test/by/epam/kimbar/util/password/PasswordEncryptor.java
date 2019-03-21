package by.epam.kimbar.util.password;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordEncryptor {
    public static String encryptPassword(String password)  {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }


}
