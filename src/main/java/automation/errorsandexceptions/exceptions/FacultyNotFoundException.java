package automation.errorsandexceptions.exceptions;

public class FacultyNotFoundException extends Exception {
    public FacultyNotFoundException(String description) {
        super(description);
    }

    public FacultyNotFoundException(String description, Throwable cause) {
        super(description, cause);
    }
}