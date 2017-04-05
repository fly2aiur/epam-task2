package by.epam.cherednik.exception;

public class DataFileException extends Exception {
    public DataFileException() {
    }

    public DataFileException(String message) {
        super(message);
    }

    public DataFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFileException(Throwable cause) {
        super(cause);
    }
}
