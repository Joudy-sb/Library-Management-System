package libraryManagementSystem;

/**
 * Represents an admin in the library management system.
 * Admin is a role type of user with a specific schedule 
 */
public class Admin extends User {
    private String schedule;

    /**
     * Constructs a new Admin object with the provided details.
     * Validates the input data and throws an exception if invalid.
     *
     * @param name        The name of the admin. Must contain only alphanumeric characters.
     * @param email       The email of the admin. Must match the format "user@mail.aub.edu".
     * @param phoneNumber The phone number of the admin. Must be numeric and exactly 11 digits.
     * @param address     The address of the admin. Must contain alphanumeric characters, spaces, and common symbols.
     * @param schedule    The schedule of the admin. Must follow the format "Monday-Friday from 9-5".
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public Admin(String name, String email, String phoneNumber, String address, String schedule) throws InvalidInputInformation {
        super(name, email, phoneNumber, address);
        if (schedule.matches("([a-zA-Z]+-[a-zA-Z]+ from \\d{1,2}-\\d{1,2})")) {
            this.setSchedule(schedule);
        } else {
            throw new InvalidInputInformation("Schedule input incorrect (ex: Monday-Friday from 9-5)");
        }
    }

    /**
     * Gets the schedule of the admin.
     *
     * @return The schedule of the admin.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Sets the schedule of the admin.
     *
     * @param schedule The new schedule for the admin.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Returns a string representation of the admin's information.
     *
     * @return A formatted string containing the admin's class name, user information, and schedule.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + super.toString() + "\nSchedule: " + this.schedule + "\n";
    }
}