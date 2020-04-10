package automation.errorsandexceptions.exceptions;

public class IllegalGradeException extends Exception {
    public IllegalGradeException(String description) {
        super(description);
    }

    public IllegalGradeException(String description, Throwable cause) {
        super(description, cause);
    }
}