package libraryManagementSystem;

/**
 * Represents a tablet device in the library management system.
 * A tablet is a specific type of electronic device and includes additional information about its shelf location.
 */
public class Tablet extends ElectronicDevice {
    private String shelf;

    /**
     * Constructs a new Tablet object with the provided brand, reference number, and shelf location.
     * Validates the input data and throws an exception if invalid.
     *
     * @param brand           The brand of the tablet. Must contain only alphabetic characters.
     * @param referenceNumber The reference number of the tablet. Must be numeric.
     * @param shelf           The shelf location of the tablet. Must be numeric.
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public Tablet(String brand, String referenceNumber, String shelf) throws InvalidInputInformation {
        super(brand, referenceNumber);
        if (shelf.matches("\\d+")) {
            this.setShelf(shelf);
        } else {
            throw new InvalidInputInformation("Shelf input incorrect (ex: 5)");
        }
    }

    /**
     * Gets the shelf location of the tablet.
     *
     * @return The shelf location of the tablet.
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * Sets the shelf location of the tablet.
     *
     * @param shelf The new shelf location for the tablet.
     */
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    /**
     * Returns a string representation of the tablet's information.
     *
     * @return A formatted string containing the tablet's class name, device information, and shelf location.
     */
    @Override
    public String toString() {
        return this.getClass().getName() + super.toString() + "\n Shelf: " + this.shelf;
    }
}