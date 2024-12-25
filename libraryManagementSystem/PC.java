package libraryManagementSystem;

/**
 * Represents a PC device in the library management system.
 * A PC is a specific type of electronic device and includes additional information about its lab room.
 */
public class PC extends ElectronicDevice {
    private String labRoom;

    /**
     * Constructs a new PC object with the provided brand, reference number, and lab room.
     * Validates the input data and throws an exception if invalid.
     *
     * @param brand           The brand of the PC. Must contain only alphabetic characters.
     * @param referenceNumber The reference number of the PC. Must be numeric.
     * @param labRoom         The lab room where the PC is located. Must contain alphanumeric characters.
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public PC(String brand, String referenceNumber, String labRoom) throws InvalidInputInformation {
        super(brand, referenceNumber);
        if (labRoom.matches("[a-zA-Z0-9]+")) {
            this.setLabRoom(labRoom);
        } else {
            throw new InvalidInputInformation("Lab room input incorrect (ex: oxy509)");
        }
    }

    /**
     * Gets the lab room of the PC.
     *
     * @return The lab room where the PC is located.
     */
    public String getLabRoom() {
        return labRoom;
    }

    /**
     * Sets the lab room of the PC.
     *
     * @param labRoom The new lab room for the PC.
     */
    public void setLabRoom(String labRoom) {
        this.labRoom = labRoom;
    }

    /**
     * Returns a string representation of the PC's information.
     *
     * @return A formatted string containing the PC's class name, device information, and lab room.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + super.toString() + "\n Lab Room: " + this.labRoom;
    }
}
