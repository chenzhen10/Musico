package by.epam.kimbar.service.validation;

public class CredentialValidator {

    private static final String REG_EXP_TO_CHECK_LATIN_LETTERS_AND_NUMBERS = "^[a-z0-9]+$";


    public static boolean isCorrect(String login, String password) {
        return isLoginCorrect(login) && isPasswordCorrect(password);
    }

    private static boolean isLoginCorrect(String login) {
        return login.matches(REG_EXP_TO_CHECK_LATIN_LETTERS_AND_NUMBERS);
    }


    private static boolean isPasswordCorrect(String password) {
        return password.matches(REG_EXP_TO_CHECK_LATIN_LETTERS_AND_NUMBERS);
    }



}
