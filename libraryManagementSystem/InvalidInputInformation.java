package libraryManagementSystem;

/**
 * Represents an exception thrown when invalid input information is provided.
 * This exception is used to indicate errors in input validation.
 */
public class InvalidInputInformation extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidInputInformation exception with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidInputInformation(String message) {
        super(message);
    }
}
