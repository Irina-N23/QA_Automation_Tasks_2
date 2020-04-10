package automation.errorsandexceptions.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String description) {
        super(description);
    }

    public StudentNotFoundException(String description, Throwable cause) {
        super(description, cause);
    }
}