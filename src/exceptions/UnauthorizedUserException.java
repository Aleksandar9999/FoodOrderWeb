package exceptions;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("Unauthorized user.");
    }
    
}
