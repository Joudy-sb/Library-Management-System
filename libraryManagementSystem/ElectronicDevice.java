package libraryManagementSystem;

/**
 * Represents an abstract electronic device in the library management system.
 * Each electronic device has a brand and a reference number.
 */
public abstract class ElectronicDevice {
    private String brand;
    private String referenceNumber;

    /**
     * Constructs a new ElectronicDevice object with the provided brand and reference number.
     * Validates the input data and throws an exception if invalid.
     *
     * @param brand           The brand of the device. Must contain only alphabetic characters.
     * @param referenceNumber The reference number of the device. Must be numeric.
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public ElectronicDevice(String brand, String referenceNumber) throws InvalidInputInformation {
        if (brand.matches("[a-zA-Z]+")) {
            this.setBrand(brand);
        } else {
            throw new InvalidInputInformation("Brand input incorrect (ex: HP, Apple etc.)");
        }
        if (referenceNumber.matches("\\d+")) {
            this.setReferenceNumber(referenceNumber);
        } else {
            throw new InvalidInputInformation("Reference number input incorrect (ex: 1234)");
        }
    }

    /**
     * Gets the brand of the electronic device.
     *
     * @return The brand of the device.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Sets the brand of the electronic device.
     *
     * @param brand The new brand for the device.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the reference number of the electronic device.
     *
     * @return The reference number of the device.
     */
    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    /**
     * Sets the reference number of the electronic device.
     *
     * @param referenceNumber The new reference number for the device.
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     * Returns a string representation of the electronic device's information.
     *
     * @return A formatted string containing the brand and reference number of the device.
     */
    @Override
    public String toString() {
        return " information: \n Brand: " + this.brand + "\n Reference Number: " + this.referenceNumber;
    }
}