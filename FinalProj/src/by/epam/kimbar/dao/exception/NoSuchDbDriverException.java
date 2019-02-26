package by.epam.kimbar.dao.exception;

public class NoSuchDbDriverException extends RuntimeException {

    public NoSuchDbDriverException() {
    }

    public NoSuchDbDriverException(String message) {
        super(message);
    }

    public NoSuchDbDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDbDriverException(Throwable cause) {
        super(cause);
    }
}
