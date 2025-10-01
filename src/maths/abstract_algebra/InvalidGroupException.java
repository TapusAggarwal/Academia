package maths.abstract_algebra;

public class InvalidGroupException extends RuntimeException {
    public InvalidGroupException(String message) {
        super(message);
    }

    public InvalidGroupException(String summary, String details) {
        super(summary + (details == null || details.isEmpty() ? "" : ": " + details));
    }

    public InvalidGroupException(String message, Throwable cause) {
        super(message, cause);
    }
}

