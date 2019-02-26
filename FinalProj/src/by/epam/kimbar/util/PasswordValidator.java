package by.epam.kimbar.util;

import by.epam.kimbar.entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordValidator {
    public static boolean isPasswordCorrect (String password, User user){
        return BCrypt.checkpw(password,user.getPassword());
    }
}
