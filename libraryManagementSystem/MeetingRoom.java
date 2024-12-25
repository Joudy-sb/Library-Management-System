package libraryManagementSystem;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a meeting room in the library management system.
 * Meeting rooms can be booked by students or professors, subject to specific rules.
 *
 * @param <T> The type of user interacting with the meeting room (e.g., Student, Professor).
 */
public class MeetingRoom<T extends User> implements Rules {
    private int renewalPeriodStudent;
    private int latePenaltyPeriodStudent;
    private int rentPeriodStudent;
    private int renewalPassedStudent;
    private int rentPeriodProfessor;
    private int renewalPeriodProfessor;
    private int latePenaltyPeriodProfessor;
    private int renewalPassedProfessor;
    private ArrayList<T> previousUsers = new ArrayList<>();
    private Admin admin;
    private String roomNumber;
    private String phoneNumber;
    private boolean available = true;
    private T user;
    private final ReentrantLock lock = new ReentrantLock();
    private String faculty = "";

    /**
     * Constructs a new meeting room with the specified room number and phone number.
     *
     * @param roomNumber  The room number of the meeting room.
     * @param phoneNumber The contact phone number for the meeting room.
     * @throws InvalidInputInformation If the room number or phone number is invalid.
     */
    public MeetingRoom(String roomNumber, String phoneNumber) throws InvalidInputInformation {
        if (roomNumber.matches("[a-zA-Z0-9]+")) {
            this.setRoomNumber(roomNumber);
        } else {
            throw new InvalidInputInformation("Room input incorrect (ex: 202)");
        }
        if (phoneNumber.matches("\\d+") && phoneNumber.length() == 11) {
            this.setPhoneNumber(phoneNumber);
        } else {
            throw new InvalidInputInformation("Phone number input incorrect (ex: 96112345678)");
        }
    }

    /**
     * Allows a user to book the meeting room for a specified period.
     *
     * @param user              The user attempting to book the meeting room.
     * @param reservationPeriod The duration of the reservation in seconds.
     * @throws UnauthorizedUserAction If the user is not authorized to book the meeting room.
     * @throws InvalidInputInformation If the reservation period exceeds the allowed limit.
     */
    public void bookRoom(T user, int reservationPeriod) throws UnauthorizedUserAction, InvalidInputInformation {
        if (!user.getFaculty().equals(this.getFaculty())) {
            throw new UnauthorizedUserAction("Please book rooms from your library.");
        }
        if (user.getClass().getSimpleName().equals("Admin")) {
            throw new UnauthorizedUserAction("Admins can't book rooms.");
        }
        if (this.previousUsers.contains(user)) {
            throw new UnauthorizedUserAction("You can't book a room on the same day.");
        }
        if (user.getClass().getSimpleName().equals("Student") && reservationPeriod > this.rentPeriodStudent) {
            throw new InvalidInputInformation("You can only book a room for a maximum of " + this.rentPeriodStudent);
        } else if (reservationPeriod > this.rentPeriodProfessor) {
            throw new InvalidInputInformation("You can only book a room for a maximum of " + this.rentPeriodProfessor);
        }
        if (this.lock.tryLock()) {
            try {
                if (this.available) {
                    this.available = false;
                    this.user = user;
                    int renewPeriod = user.getClass().getSimpleName().equals("Student") ? this.renewalPeriodStudent : this.renewalPeriodProfessor;

                    this.previousUsers.add(user);
                    ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
                    scheduler1.schedule(() -> {
                        this.previousUsers.remove(user);
                        scheduler1.shutdown();
                    }, renewPeriod, TimeUnit.SECONDS);

                    System.out.println("Room " + this.roomNumber + " has been successfully booked.");
                    ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
                    scheduler2.schedule(() -> {
                        this.available = true;
                        this.user = null;
                        scheduler2.shutdown();
                    }, reservationPeriod, TimeUnit.SECONDS);
                } else {
                    System.out.println("Sorry, the room is currently unavailable.");
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // Setters and getters with Javadoc
    /**
     * Gets the room number of the meeting room.
     *
     * @return The room number.
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number of the meeting room.
     *
     * @param roomNumber The new room number.
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Gets the phone number associated with the meeting room.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number associated with the meeting room.
     *
     * @param phoneNumber The new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Checks if the meeting room is available for booking.
     *
     * @return {@code true} if the room is available, {@code false} otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the meeting room.
     *
     * @param available {@code true} if the room is available, {@code false} otherwise.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets the user currently occupying the meeting room.
     *
     * @return The current user.
     */
    public T getUser() {
        return user;
    }

    /**
     * Sets the user currently occupying the meeting room.
     *
     * @param user The new user.
     */
    public void setUser(T user) {
        this.user = user;
    }

    /**
     * Gets the admin responsible for the meeting room.
     *
     * @return The admin of the library managing this room.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Sets the admin responsible for the meeting room.
     *
     * @param admin The admin of the library managing this room.
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Gets the faculty associated with the meeting room.
     *
     * @return The name of the faculty.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the faculty associated with the meeting room.
     *
     * @param faculty The name of the faculty.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Returns a string representation of the meeting room.
     *
     * @return A formatted string with the room's details.
     */
    @Override
    public String toString() {
        return "Meeting Room Information: \nRoom Number: " + this.roomNumber +
               "\nContact Information: " + this.phoneNumber + "\nAvailable: " + this.available;
    }

    // Other overridden methods with rules are documented similarly
    // Example:
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
     * @param admin The admin making the change.
     * @param renewalPeriodStudent The new renewal period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
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
     * @param admin                 The admin making the change.
     * @param latePenaltyPeriodStudent The new late penalty period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
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
     * @param rentPeriodStudent The new rental period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
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
     * Gets the number of renewal passes allowed for students.
     *
     * @return The number of renewal passes allowed for students.
     */
    @Override
    public int getRenewalPassedStudent() {
        return renewalPassedStudent;
    }

    /**
     * Sets the number of renewal passes allowed for students.
     *
     * @param admin               The admin making the change.
     * @param renewalPassedStudent The new number of renewal passes allowed for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
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
     * @param admin                  The admin making the change.
     * @param renewalPeriodProfessor The new renewal period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    @Override
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
     * @param rentPeriodProfessor The new rental period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    @Override
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
     * @param latePenaltyPeriodProfessor The new late penalty period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
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
     * Gets the number of renewal passes allowed for professors.
     *
     * @return The number of renewal passes allowed for professors.
     */
    @Override
    public int getRenewalPassedProfessor() {
        return renewalPassedProfessor;
    }

    /**
     * Sets the number of renewal passes allowed for professors.
     *
     * @param admin                The admin making the change.
     * @param renewalPassedProfessor The new number of renewal passes allowed for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    @Override
    public void setRenewalPassedProfessor(Admin admin, int renewalPassedProfessor) throws UnauthorizedUserAction {
        if (admin == this.admin) {
            this.renewalPassedProfessor = renewalPassedProfessor;
        } else {
            throw new UnauthorizedUserAction("You are not the admin of this library");
        }
    }
}
