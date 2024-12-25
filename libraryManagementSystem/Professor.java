package libraryManagementSystem;

/**
 * Represents a professor in the library management system.
 * Professors have a rank in addition to the basic user attributes.
 */
public class Professor extends User {
    private String rank;

    /**
     * Constructs a new Professor object with the provided details.
     * Validates the input data and throws an exception if invalid.
     *
     * @param name        The name of the professor. Must contain only alphanumeric characters.
     * @param email       The email of the professor. Must match the format "user@mail.aub.edu".
     * @param phoneNumber The phone number of the professor. Must be numeric and exactly 11 digits.
     * @param address     The address of the professor. Must contain alphanumeric characters, spaces, and common symbols.
     * @param rank        The rank of the professor. Must be either "professor" or "lecturer".
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public Professor(String name, String email, String phoneNumber, String address, String rank) throws InvalidInputInformation {
        super(name, email, phoneNumber, address);
        if (rank.equalsIgnoreCase("professor") || rank.equalsIgnoreCase("lecturer")) {
            this.setRank(rank);
        } else {
            throw new InvalidInputInformation("Rank must be either professor or lecturer");
        }
    }

    /**
     * Gets the rank of the professor.
     *
     * @return The rank of the professor.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Sets the rank of the professor.
     * Automatically sets the rank to "professor" or "instructor" based on input.
     *
     * @param rank The new rank for the professor.
     */
    public void setRank(String rank) {
        if (rank.equalsIgnoreCase("professor")) {
            this.rank = "professor";
        } else {
            this.rank = "instructor";
        }
    }

    /**
     * Returns a string representation of the professor's information.
     *
     * @return A formatted string containing the professor's class name, user information, and rank.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + super.toString() + "\nRank: " + this.rank + "\n";
    }
}