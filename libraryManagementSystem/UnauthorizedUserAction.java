package libraryManagementSystem;

/**
 * Represents an exception thrown when a user attempts an unauthorized action.
 * This exception is used to enforce access control and prevent invalid operations.
 */
public class UnauthorizedUserAction extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UnauthorizedUserAction exception with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public UnauthorizedUserAction(String message) {
        super(message);
    }
}
