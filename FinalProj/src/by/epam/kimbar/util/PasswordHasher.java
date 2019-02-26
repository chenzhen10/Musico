package by.epam.kimbar.util;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String password)  {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
