package libraryManagementSystem;

import java.util.ArrayList;

/**
 * Represents a library in the library management system.
 * A library contains books, electronic resources, and meeting rooms.
 * It is managed by an admin and is associated with a specific faculty.
 */
public class Library {
    private String faculty = "";
    private Admin admin;
    private ArrayList<Book<User>> books = new ArrayList<>();
    private ArrayList<ElectronicResource<ElectronicDevice, User>> electronicResources = new ArrayList<>();
    private ArrayList<MeetingRoom<User>> meetingRooms = new ArrayList<>();

    /**
     * Constructs a new Library managed by the specified admin.
     *
     * @param admin The admin managing this library.
     */
    public Library(Admin admin) {
        this.setAdmin(admin);
    }

    /**
     * Adds a book to the library.
     * Only the admin of this library can add books.
     *
     * @param admin The admin adding the book.
     * @param book  The book to add to the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to add books to this library.
     */
    public void addBook(Admin admin, Book<User> book) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            if (book.getFaculty().isEmpty()) {
                this.books.add(book);
                book.setFaculty(this.getFaculty());
                book.setAdmin(admin);
            } else {
                System.out.println("Book already belongs to " + this.getClass().getName());
            }
        } else {
            throw new UnauthorizedUserAction("You can't add books to this library");
        }
    }

    /**
     * Deletes a book from the library.
     * Only the admin of this library can delete books.
     *
     * @param admin The admin deleting the book.
     * @param book  The book to remove from the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to delete books from this library.
     */
    public void deleteBook(Admin admin, Book<User> book) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            this.books.remove(book);
            book.setAdmin(null);
        } else {
            throw new UnauthorizedUserAction("You can't delete books from this library");
        }
    }

    /**
     * Adds an electronic resource to the library.
     * Only the admin of this library can add electronic resources.
     *
     * @param admin             The admin adding the electronic resource.
     * @param electronicResource The electronic resource to add to the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to add electronic resources to this library.
     */
    public void addElectronicResource(Admin admin, ElectronicResource<ElectronicDevice, User> electronicResource) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            this.electronicResources.add(electronicResource);
            electronicResource.setAdmin(admin);
            electronicResource.setFaculty(this.getFaculty());
        } else {
            throw new UnauthorizedUserAction("You can't add electronic resources to this library");
        }
    }

    /**
     * Deletes an electronic resource from the library.
     * Only the admin of this library can delete electronic resources.
     *
     * @param admin             The admin deleting the electronic resource.
     * @param electronicResource The electronic resource to remove from the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to delete electronic resources from this library.
     */
    public void deleteElectronicResource(Admin admin, ElectronicResource<ElectronicDevice, User> electronicResource) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            this.electronicResources.remove(electronicResource);
            electronicResource.setAdmin(null);
        } else {
            throw new UnauthorizedUserAction("You can't delete electronic resources from this library");
        }
    }

    /**
     * Adds a meeting room to the library.
     * Only the admin of this library can add meeting rooms.
     *
     * @param admin       The admin adding the meeting room.
     * @param meetingRoom The meeting room to add to the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to add meeting rooms to this library.
     */
    public void addMeetingRoom(Admin admin, MeetingRoom<User> meetingRoom) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            this.meetingRooms.add(meetingRoom);
            meetingRoom.setAdmin(admin);
            meetingRoom.setFaculty(this.getFaculty());
        } else {
            throw new UnauthorizedUserAction("You can't add meeting rooms to this library");
        }
    }

    /**
     * Deletes a meeting room from the library.
     * Only the admin of this library can delete meeting rooms.
     *
     * @param admin       The admin deleting the meeting room.
     * @param meetingRoom The meeting room to remove from the library.
     * @throws UnauthorizedUserAction If the admin is not authorized to delete meeting rooms from this library.
     */
    public void deleteMeetingRoom(Admin admin, MeetingRoom<User> meetingRoom) throws UnauthorizedUserAction {
        if (this.admin == admin) {
            this.meetingRooms.remove(meetingRoom);
            meetingRoom.setAdmin(null);
        } else {
            throw new UnauthorizedUserAction("You can't delete meeting rooms from this library");
        }
    }

    /**
     * Finds all books in the library with the specified genre.
     *
     * @param genre The genre to search for.
     * @return A list of books with the specified genre.
     */
    public ArrayList<Book<User>> findBookbyGenre(String genre) {
        ArrayList<Book<User>> booksByGenre = new ArrayList<>();
        for (Book<User> book : books) {
            if (book.getGenre().equals(genre)) {
                booksByGenre.add(book);
            }
        }
        return booksByGenre;
    }

    /**
     * Finds a book in the library with the specified ISBN.
     *
     * @param ISBN The ISBN to search for.
     * @return The book with the specified ISBN, or {@code null} if not found.
     */
    public Book<User> findBookbyISBN(String ISBN) {
        for (Book<User> book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        System.out.println("We don't have that book");
        return null;
    }

    /**
     * Finds all books in the library by the specified author.
     *
     * @param author The author to search for.
     * @return A list of books by the specified author.
     */
    public ArrayList<Book<User>> findBookbyAuthor(String author) {
        ArrayList<Book<User>> booksByAuthor = new ArrayList<>();
        for (Book<User> book : books) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    /**
     * Finds all books in the library with the specified title.
     *
     * @param title The title to search for.
     * @return A list of books with the specified title.
     */
    public ArrayList<Book<User>> findBookbyTitle(String title) {
        ArrayList<Book<User>> booksByTitle = new ArrayList<>();
        for (Book<User> book : books) {
            if (book.getTitle().equals(title)) {
                booksByTitle.add(book);
            }
        }
        return booksByTitle;
    }

    /**
     * Gets the best-selling book in the library.
     * For simplicity, this method returns the top 1 book based on the number of times borrowed.
     *
     * @return A list containing the best-selling book.
     */
    public ArrayList<Book<User>> bestSellerBook() {
        ArrayList<Book<User>> books = this.getBooks();
        books.sort((b1, b2) -> b2.getNbTimesBorrowed() - b1.getNbTimesBorrowed());
        ArrayList<Book<User>> topBooks = new ArrayList<>();
        for (int i = 0; i < Math.min(1, books.size()); i++) {
            topBooks.add(books.get(i));
        }
        return topBooks;
    }

    /**
     * Gets the admin managing this library.
     *
     * @return The admin of the library.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Sets the admin managing this library.
     *
     * @param admin The new admin of the library.
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Gets the list of books in the library.
     *
     * @return A list of books in the library.
     */
    public ArrayList<Book<User>> getBooks() {
        return books;
    }

    /**
     * Gets the list of electronic resources in the library.
     *
     * @return A list of electronic resources in the library.
     */
    public ArrayList<ElectronicResource<ElectronicDevice, User>> getElectronicResources() {
        return electronicResources;
    }

    /**
     * Gets the list of meeting rooms in the library.
     *
     * @return A list of meeting rooms in the library.
     */
    public ArrayList<MeetingRoom<User>> getMeetingRooms() {
        return meetingRooms;
    }

    /**
     * Gets the faculty associated with this library.
     *
     * @return The name of the faculty.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the faculty associated with this library.
     *
     * @param faculty The name of the faculty.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
