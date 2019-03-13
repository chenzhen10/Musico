package by.epam.kimbar.service.validation;

public class CredentialValidator {


    //some restriction of login length or password containing
    public static boolean isCorrect(String login, String password) {
        return isLoginCorrect(login) && isPasswordCorrect(password);
    }

    private static boolean isLoginCorrect(String login) {
        return login.matches("^[a-z0-9]+$");
    }


    private static boolean isPasswordCorrect(String password) {
        return password.matches("^[a-z0-9]+$");
    }



}
