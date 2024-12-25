package libraryManagementSystem;

import java.util.ArrayList;

/**
 * Represents an abstract user in the library management system.
 * A user can borrow and return books, and may belong to a specific faculty.
 * This class includes basic information such as name, email, phone number, and address.
 */
public abstract class User {
    private String faculty = "";
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean blacklisted = false;
    private ArrayList<String> borrowedBooks = new ArrayList<>();
    private ArrayList<String> returnedBooks = new ArrayList<>();

    /**
     * Constructs a new User object with the provided details.
     * Validates the input data and throws an exception if invalid.
     *
     * @param name        The name of the user. Must contain only alphanumeric characters.
     * @param email       The email of the user. Must match the format "user@mail.aub.edu".
     * @param phoneNumber The phone number of the user. Must be numeric and exactly 11 digits.
     * @param address     The address of the user. Must contain alphanumeric characters, spaces, and common symbols.
     * @throws InvalidInputInformation if any of the input data is invalid.
     */
    public User(String name, String email, String phoneNumber, String address) throws InvalidInputInformation {
        if (email.matches("[a-zA-Z0-9.+-]+@mail\\.aub\\.edu")) {
            this.setEmail(email);
        } else {
            throw new InvalidInputInformation("Email input incorrect (ex: user@mail.aub.edu)");
        }
        if (phoneNumber.matches("\\d+") && phoneNumber.length() == 11) {
            this.setPhoneNumber(phoneNumber);
        } else {
            throw new InvalidInputInformation("Phone number input incorrect (ex: 96112345678)");
        }
        if (name.matches("[a-zA-Z0-9]+")) {
            this.setName(name);
        } else {
            throw new InvalidInputInformation("Name input incorrect (ex: carl)");
        }
        if (address.matches("[a-zA-Z0-9.,\\-\\s]+")) {
            this.setAddress(address);
        } else {
            throw new InvalidInputInformation("Address input incorrect (ex: ouzai street house 1B)");
        }
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The new phone number for the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the address of the user.
     *
     * @return The address of the user.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address The new address for the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Checks if the user is blacklisted.
     *
     * @return {@code true} if the user is blacklisted, {@code false} otherwise.
     */
    public boolean isBlacklisted() {
        return blacklisted;
    }

    /**
     * Sets the blacklist status of the user.
     *
     * @param blacklisted The new blacklist status for the user.
     */
    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    /**
     * Gets the list of books borrowed by the user.
     *
     * @return A list of borrowed book titles.
     */
    public ArrayList<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Adds a book to the list of borrowed books.
     *
     * @param borrowedBook The title of the book being borrowed.
     */
    public void addBorrowedBooks(String borrowedBook) {
        this.borrowedBooks.add(borrowedBook);
    }

    /**
     * Gets the list of books returned by the user.
     *
     * @return A list of returned book titles.
     */
    public ArrayList<String> getReturnedBooks() {
        return returnedBooks;
    }

    /**
     * Adds a book to the list of returned books.
     *
     * @param returnedBooks The title of the book being returned.
     */
    public void addReturnedBooks(String returnedBooks) {
        this.returnedBooks.add(returnedBooks);
    }

    /**
     * Gets the faculty of the user.
     *
     * @return The faculty of the user.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the faculty of the user.
     *
     * @param faculty The new faculty for the user.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Returns a string representation of the user's basic information.
     *
     * @return A formatted string containing the user's name, email, phone number, and address.
     */
    @Override
    public String toString() {
        return " information: \nName: " + this.getName() + "\nEmail: " + this.getEmail() +
               "\nPhone Number: " + this.getPhoneNumber() + "\nAddress: " + this.getAddress();
    }
}