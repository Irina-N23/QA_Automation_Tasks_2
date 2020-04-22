package automation.threads;

class WaitingPeriodExceededException extends Exception {
    WaitingPeriodExceededException(String description) {
        super(description);
    }

    WaitingPeriodExceededException(String description, Throwable cause) {
        super(description, cause);
    }
}