package automation.errorsandexceptions.exceptions;

public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String description) {
        super(description);
    }

    public CourseNotFoundException(String description, Throwable cause) {
        super(description, cause);
    }
}