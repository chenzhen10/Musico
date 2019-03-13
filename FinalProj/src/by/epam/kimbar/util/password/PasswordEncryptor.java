package by.epam.kimbar.util.password;

import by.epam.kimbar.entity.User;
import org.mindrot.jbcrypt.BCrypt;



public class PasswordEncryptor {
    public static String encryptPassword(String password)  {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }


}
