package automation.errorsandexceptions.exceptions;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(String description) {
        super(description);
    }

    public GroupNotFoundException(String description, Throwable cause) {
        super(description, cause);
    }
}