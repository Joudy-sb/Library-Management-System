package libraryManagementSystem;

/**
 * Represents a student in the library management system.
 * A student is a role type of user with an additional ID field.
 */
public class Student extends User {
    private String id;

    /**
     * Constructs a new Student object with the provided details.
     * Validates the input data and throws an exception if invalid.
     *
     * @param name        The name of the student. Must contain only alphanumeric characters.
     * @param email       The email of the student. Must match the format "user@mail.aub.edu".
     * @param phoneNumber The phone number of the student. Must be numeric and exactly 11 digits.
     * @param address     The address of the student. Must contain alphanumeric characters, spaces, and common symbols.
     * @param id          The ID of the student. Must be numeric and exactly 9 digits.
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public Student(String name, String email, String phoneNumber, String address, String id) throws InvalidInputInformation {
        super(name, email, phoneNumber, address);
        if (id.matches("\\d+") && id.length() == 9) {
            this.setId(id);
        } else {
            throw new InvalidInputInformation("ID input incorrect (ex: 123456789)");
        }
    }

    /**
     * Gets the ID of the student.
     *
     * @return The ID of the student.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id The new ID for the student.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the student's information.
     *
     * @return A formatted string containing the student's class name, user information, and ID.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + super.toString() + "\nID: " + this.id + "\n";
    }
}