package libraryManagementSystem;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a book in the library management system.
 * The book can be rented, returned, and renewed based on user rules.
 *
 * @param <T> The type of the user interacting with the book (e.g., Student, Professor).
 */
public class Book<T extends User> implements Rules {
    private int nbTimesBorrowed = 0;
    private int renewalPeriodStudent;
    private int rentPeriodStudent;
    private int latePenaltyPeriodStudent;
    private int renewalPassedStudent;
    private int rentPeriodProfessor;
    private int renewalPeriodProfessor;
    private int latePenaltyPeriodProfessor;
    private int usedRenewalPass = 0;
    private int renewalPassedProfessor;
    private Admin admin;
    private String faculty = ""; 
    private String title;
    private String author;
    private String genre;
    private String description;
    private String ISBN;
    private String format;
    private boolean rentable;
    private String publicationYear;
    private boolean available = true;
    private Instant rentedTime;
    private Instant returnTime;
    private T user;
    private final ReentrantLock lock = new ReentrantLock();
    private ArrayList<T> waitlist = new ArrayList<>();

    /**
     * Constructs a new Book object with the specified details.
     *
     * @param title          The title of the book.
     * @param author         The author of the book.
     * @param genre          The genre of the book.
     * @param description    A short description of the book.
     * @param format         The format of the book (e.g., "physical", "ebook").
     * @param isbn           The ISBN of the book.
     * @param rentable       Whether the book can be rented.
     * @param publicationYear The publication year of the book.
     * @throws InvalidInputInformation If any input is invalid.
     */
    public Book(String title, String author, String genre, String description, String format, String isbn, boolean rentable, String publicationYear) throws InvalidInputInformation {
        if (title.matches("[a-zA-Z0-9.,:'\\-\\s]+")) {
            this.setTitle(title);
        } else {
            throw new InvalidInputInformation("Title input incorrect (ex: To Kill a Mockingbird)");
        }
        if (author.matches("[a-zA-Z.,\\-\\s]+")) {
            this.setAuthor(author);
        } else {
            throw new InvalidInputInformation("Author input incorrect (ex: Harper Lee)");
        }
        if (genre.matches("[a-zA-Z\\s]+")) {
            this.setGenre(genre);
        } else {
            throw new InvalidInputInformation("Genre input incorrect (ex: academics, fiction etc...)");
        }
        if (description.matches("[a-zA-Z0-9.,\\-\\s]+")) {
            this.setDescription(description);
        } else {
            throw new InvalidInputInformation("Description input incorrect (ex: A novel about injustice)");
        }
        if (format.equalsIgnoreCase("physical") || format.equalsIgnoreCase("ebook")) {
            this.setFormat(format);
        } else {
            throw new InvalidInputInformation("Format input incorrect (ex: Physical or Ebook)");
        }
        if (isbn.matches("\\d+")) {
            this.setISBN(isbn);
        } else {
            throw new InvalidInputInformation("ISBN input incorrect (ex: 1234567890)");
        }
        if (publicationYear.matches("\\d{4}")) {
            this.setPublicationYear(publicationYear);
        } else {
            throw new InvalidInputInformation("Publication year input incorrect (ex: 1960)");
        }
        this.setRentable(rentable);
    }

    /**
     * Allows a user to rent the book if eligible.
     *
     * @param user The user renting the book.
     * @throws UnauthorizedUserAction If the user is not authorized to rent the book.
     */
    public void rentBook(T user) throws UnauthorizedUserAction {
        if (user.getFaculty() != this.getFaculty()) {
            throw new UnauthorizedUserAction("Please rent books from your library");
        }
        if (user.getClass().getSimpleName() == "Admin") {
            throw new UnauthorizedUserAction("Admins can't rent books from libraries");
        }
        if (user.isBlacklisted()) {
            System.out.println("You are blacklisted! Next time return your book on time.");
        } else if (this.rentable) {
            if (this.lock.tryLock()) {
                try {
                    if (this.available) {
                        this.available = false;
                        this.user = user;
                        this.user.addBorrowedBooks(this.title);
                        this.nbTimesBorrowed++;
                        this.rentedTime = Instant.now();
                        if (user.getClass().getSimpleName() == "Student") {
                            this.returnTime = this.rentedTime.plus(this.getRentPeriodStudent(), ChronoUnit.SECONDS);
                        } else {
                            this.returnTime = this.rentedTime.plus(this.getRentPeriodProfessor(), ChronoUnit.SECONDS);
                        }
                        System.out.println("You have successfully rented " + this.title + " and have till " + this.returnTime + " to return the book.");
                    } else {
                        System.out.println("Sorry, this book is currently being rented. You have joined the waitlist.");
                        this.addToWaitlist(user);
                    }
                } finally {
                    lock.unlock();
                }
            }
        } else {
            System.out.println("This book is not for rent");
        }
    }

    /**
     * Allows the user to return the book.
     *
     * @param user The user returning the book.
     * @throws UnauthorizedUserAction If the user is not authorized to return the book.
     */
    public void returnBook(T user) throws UnauthorizedUserAction {
        if (this.user != user) {
            throw new UnauthorizedUserAction("You cannot return the book on behalf of someone.");
        } else {
            Instant time = Instant.now();
            this.user.addReturnedBooks(this.title);
            if (time.isAfter(this.returnTime)) {
                int returnPenalty;
                if (user.getClass().getSimpleName().equalsIgnoreCase("Student")) {
                    returnPenalty = this.latePenaltyPeriodStudent;
                } else {
                    returnPenalty = this.latePenaltyPeriodProfessor;
                }
                System.out.println("You have returned the book late. You must wait " + returnPenalty + " seconds before renting another book.");
                user.setBlacklisted(true);
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> {
                    user.setBlacklisted(false);
                    scheduler.shutdown();
                }, returnPenalty, TimeUnit.SECONDS);
            } else {
                System.out.println("You have successfully returned " + this.title + " on time.");
            }
            this.user = null;
            this.usedRenewalPass = 0;
        }

        if (!this.waitlist.isEmpty()) {
            this.user = this.waitlist.get(0);
            this.waitlist.remove(this.user);
            this.rentedTime = Instant.now();
            this.returnTime = this.rentedTime.plus(10, ChronoUnit.SECONDS);
        } else {
            this.available = true;
        }
    }

    /**
     * Renews the rental period for the book.
     *
     * @param user The user renewing the book rental.
     * @throws UnauthorizedUserAction If the user is not authorized to renew the rental.
     */
    public void renewRental(T user) throws UnauthorizedUserAction {
        if (this.user != user) {
            throw new UnauthorizedUserAction("You cannot renew book rent period on behalf of someone.");
        }
        if (user.getClass().getSimpleName().equalsIgnoreCase("Student")) {
            if (this.usedRenewalPass < this.renewalPassedStudent) {
                this.returnTime = this.returnTime.plus(this.renewalPeriodStudent, ChronoUnit.SECONDS);
                System.out.println("Return date extended, you have till " + this.returnTime + " to return the book.");
                this.usedRenewalPass++;
            } else {
                System.out.println("Sorry, cannot renew your book rent period.");
            }
        } else {
            if (this.usedRenewalPass < this.renewalPassedProfessor) {
                this.returnTime = this.returnTime.plus(this.renewalPeriodProfessor, ChronoUnit.SECONDS);
                System.out.println("Return date extended, you have till " + this.returnTime + " to return the book.");
                this.usedRenewalPass++;
            } else {
                System.out.println("Sorry, cannot renew your book rent period.");
            }
        }
    }

    /**
     * Adds the user to the waitlist for the book.
     *
     * @param user The user to be added to the waitlist.
     */
    public void addToWaitlist(T user) {
        this.waitlist.add(user);
    }
	
    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre The genre of the book.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the description of the book.
     *
     * @return A short description of the book.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description A short description of the book.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param iSBN The ISBN of the book.
     */
    public void setISBN(String iSBN) {
        this.ISBN = iSBN;
    }

    /**
     * Checks if the book is rentable.
     *
     * @return {@code true} if the book is rentable, {@code false} otherwise.
     */
    public boolean isRentable() {
        return rentable;
    }

    /**
     * Sets whether the book is rentable.
     *
     * @param rentable {@code true} if the book is rentable, {@code false} otherwise.
     */
    public void setRentable(boolean rentable) {
        this.rentable = rentable;
    }

    /**
     * Gets the format of the book (e.g., "physical" or "ebook").
     *
     * @return The format of the book.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format of the book.
     *
     * @param format The format of the book (e.g., "physical" or "ebook").
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Checks if the book is currently available.
     *
     * @return {@code true} if the book is available, {@code false} otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the book.
     *
     * @param available {@code true} if the book is available, {@code false} otherwise.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets the time when the book was rented.
     *
     * @return The {@link Instant} representing the rental time.
     */
    public Instant getRentedTime() {
        return rentedTime;
    }

    /**
     * Sets the time when the book was rented.
     *
     * @param rentedTime The {@link Instant} representing the rental time.
     */
    public void setRentedTime(Instant rentedTime) {
        this.rentedTime = rentedTime;
    }

    /**
     * Gets the time when the book is expected to be returned.
     *
     * @return The {@link Instant} representing the return time.
     */
    public Instant getReturnedTime() {
        return returnTime;
    }

    /**
     * Sets the time when the book is expected to be returned.
     *
     * @param returnTime The {@link Instant} representing the return time.
     */
    public void setReturnedTime(Instant returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * Gets the publication year of the book.
     *
     * @return The publication year of the book as a string.
     */
    public String getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     *
     * @param publicationYear The publication year of the book as a string.
     */
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets the user who currently has the book.
     *
     * @return The user who currently has the book.
     */
    public T getUser() {
        return user;
    }

    /**
     * Sets the user who currently has the book.
     *
     * @param user The user who currently has the book.
     */
    public void setUser(T user) {
        this.user = user;
    }
	
    /**
     * Gets the renewal period for students.
     *
     * @return The renewal period for students in seconds.
     */
    @Override
    public int getRenewalPeriodStudent() {
        return renewalPeriodStudent;
    }

    /**
     * Sets the renewal period for students.
     *
     * @param admin               The admin making the change.
     * @param renewalPeriodStudent The new renewal period for students in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setRenewalPeriodStudent(Admin admin, int renewalPeriodStudent) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.renewalPeriodStudent = renewalPeriodStudent;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the rental period for students.
     *
     * @return The rental period for students in seconds.
     */
    @Override
    public int getRentPeriodStudent() {
        return rentPeriodStudent;
    }

    /**
     * Sets the rental period for students.
     *
     * @param admin           The admin making the change.
     * @param rentPeriodStudent The new rental period for students in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setRentPeriodStudent(Admin admin, int rentPeriodStudent) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.rentPeriodStudent = rentPeriodStudent;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the late penalty period for students.
     *
     * @return The late penalty period for students in seconds.
     */
    @Override
    public int getLatePenaltyPeriodStudent() {
        return latePenaltyPeriodStudent;
    }

    /**
     * Sets the late penalty period for students.
     *
     * @param admin                  The admin making the change.
     * @param latePenaltyPeriodStudent The new late penalty period for students in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setLatePenaltyPeriodStudent(Admin admin, int latePenaltyPeriodStudent) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.latePenaltyPeriodStudent = latePenaltyPeriodStudent;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the renewal passed period for students.
     *
     * @return The renewal passed period for students in seconds.
     */
    @Override
    public int getRenewalPassedStudent() {
        return renewalPassedStudent;
    }

    /**
     * Sets the renewal passed period for students.
     *
     * @param admin                The admin making the change.
     * @param renewalPassedStudent The new renewal passed period for students in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setRenewalPassedStudent(Admin admin, int renewalPassedStudent) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.renewalPassedStudent = renewalPassedStudent;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the renewal period for professors.
     *
     * @return The renewal period for professors in seconds.
     */
    @Override
    public int getRenewalPeriodProfessor() {
        return renewalPeriodProfessor;
    }

    /**
     * Sets the renewal period for professors.
     *
     * @param admin                 The admin making the change.
     * @param renewalPeriodProfessor The new renewal period for professors in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    public void setRenewalPeriodProfessor(Admin admin, int renewalPeriodProfessor) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.renewalPeriodProfessor = renewalPeriodProfessor;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the rental period for professors.
     *
     * @return The rental period for professors in seconds.
     */
    @Override
    public int getRentPeriodProfessor() {
        return rentPeriodProfessor;
    }

    /**
     * Sets the rental period for professors.
     *
     * @param admin            The admin making the change.
     * @param rentPeriodProfessor The new rental period for professors in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    public void setRentPeriodProfessor(Admin admin, int rentPeriodProfessor) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.rentPeriodProfessor = rentPeriodProfessor;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the late penalty period for professors.
     *
     * @return The late penalty period for professors in seconds.
     */
    @Override
    public int getLatePenaltyPeriodProfessor() {
        return latePenaltyPeriodProfessor;
    }

    /**
     * Sets the late penalty period for professors.
     *
     * @param admin                   The admin making the change.
     * @param latePenaltyPeriodProfessor The new late penalty period for professors in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setLatePenaltyPeriodProfessor(Admin admin, int latePenaltyPeriodProfessor) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.latePenaltyPeriodProfessor = latePenaltyPeriodProfessor;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Gets the renewal passed period for professors.
     *
     * @return The renewal passed period for professors in seconds.
     */
    @Override
    public int getRenewalPassedProfessor() {
        return renewalPassedProfessor;
    }

    /**
     * Sets the renewal passed period for professors.
     *
     * @param admin                 The admin making the change.
     * @param renewalPassedProfessor The new renewal passed period for professors in seconds.
     * @throws UnauthorizedUserAction If the admin is not authorized to modify this setting.
     */
    @Override
    public void setRenewalPassedProfessor(Admin admin, int renewalPassedProfessor) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.renewalPassedProfessor = renewalPassedProfessor;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }

    /**
     * Returns a string representation of the book's details.
     *
     * @return A formatted string containing all the book's information.
     */
    @Override
    public String toString() {
        return "Book Information: \nTitle: " + this.title + "\nAuthor: " + this.author + "\nGenre: " + this.genre +
               "\nDescription: " + this.description + "\nYear Published: " + this.publicationYear +
               "\nISBN number: " + this.ISBN + "\nFormat: " + this.format + "\nRentable: " + this.rentable +
               "\nAvailable: " + this.available;
    }

    /**
     * Gets the faculty associated with the book.
     *
     * @return The faculty name.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the faculty associated with the book.
     *
     * @param faculty The name of the faculty.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the admin responsible for managing the book.
     *
     * @return The admin of the library managing the book.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Sets the admin responsible for managing the book.
     *
     * @param admin The admin of the library managing the book.
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Gets the number of times the book's rental period has been renewed.
     *
     * @return The number of renewal passes used.
     */
    public int getUsedRenewalPass() {
        return usedRenewalPass;
    }

    /**
     * Sets the number of times the book's rental period has been renewed.
     *
     * @param usedRenewalPass The number of renewal passes used.
     */
    public void setUsedRenewalPass(int usedRenewalPass) {
        this.usedRenewalPass = usedRenewalPass;
    }

    /**
     * Gets the number of times the book has been borrowed.
     *
     * @return The total number of times the book has been borrowed.
     */
    public int getNbTimesBorrowed() {
        return nbTimesBorrowed;
    }

    /**
     * Sets the number of times the book has been borrowed.
     *
     * @param nbTimesBorrowed The total number of times the book has been borrowed.
     */
    public void setNbTimesBorrowed(int nbTimesBorrowed) {
        this.nbTimesBorrowed = nbTimesBorrowed;
    }
	
}
