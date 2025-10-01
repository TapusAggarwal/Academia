package maths.abstract_algebra;

public class AccessSetBuilderException extends RuntimeException {
    public AccessSetBuilderException(String message) {
        super(message);
    }

    public AccessSetBuilderException(String summary, String details) {
        super(summary + (details == null || details.isEmpty() ? "" : ": " + details));
    }

    public AccessSetBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
