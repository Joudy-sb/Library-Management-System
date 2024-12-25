package libraryManagementSystem;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents an electronic resource in the library management system.
 * Associates an electronic device with a user and handles reservation and usage rules.
 *
 * @param <T> The type of electronic device (e.g., Tablet, PC).
 * @param <U> The type of user (e.g., Student, Professor).
 */
public class ElectronicResource <T extends ElectronicDevice, U extends User> implements Rules{
	private int renewalPeriodStudent;
	private int latePenaltyPeriodStudent;
	private int renewalPassedStudent;
	private int rentPeriodStudent;
	private int rentPeriodProfessor;
	private int renewalPeriodProfessor;
	private int latePenaltyPeriodProfessor;
	private int renewalPassedProfessor;
	private ArrayList<U> previousUsers = new ArrayList<>();
	private Admin admin;
	private T device; // can be only table or PC
	private boolean available = true;
	private U user;
	private final ReentrantLock lock = new ReentrantLock();
	private String faculty = ""; 
	
	/**
     * Constructs an electronic resource for the specified device.
     *
     * @param device The electronic device associated with this resource.
     */
	public ElectronicResource(T device) {
		this.setDevice(device);
	}
	 /**
     * Allows a user to use the electronic device for a given reservation period.
     * Ensures the user is authorized and the reservation complies with usage rules.
     *
     * @param user              The user attempting to reserve the device.
     * @param reservationPeriod The duration of the reservation in seconds.
     * @throws UnauthorizedUserAction If the user is not authorized to use the device.
     * @throws InvalidInputInformation If the reservation period exceeds allowed limits.
     */
	public void useDevice(U user, int reservationPeriod) throws UnauthorizedUserAction, InvalidInputInformation {
		if (user.getFaculty()!=this.getFaculty()) {
			throw new UnauthorizedUserAction("Please rent books from your library");
		}
		// only students and professors can book the room
		if (user.getClass().getSimpleName()=="Admin") {
			throw new UnauthorizedUserAction("Admins can't use devices");
		}
		if (this.previousUsers.contains(user)) {
			throw new UnauthorizedUserAction("You can't book a device in the same day");
		}
		if (user.getClass().getSimpleName()=="Student" && reservationPeriod>this.rentPeriodStudent) {
			throw new InvalidInputInformation("You can only book a device for maximum " +this.rentPeriodStudent);
		} else if (reservationPeriod>this.rentPeriodProfessor){
			throw new InvalidInputInformation("You can only book a device for maximum " +this.rentPeriodProfessor);
		}
		if (lock.tryLock()) {
			try {
				if(this.available==true) {
					this.available = false;
					this.user = user;
					int renewPeriod;
					if (user.getClass().getSimpleName()=="Student") {
						renewPeriod = this.renewalPeriodStudent;
					} else {
						renewPeriod = this.renewalPeriodProfessor;
					}
					// this user can't reserve a room for one day
					this.previousUsers.add(user);
					ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
		            scheduler1.schedule(() -> {
		            	this.previousUsers.remove(user);
		                scheduler1.shutdown(); 
		            }, renewPeriod, TimeUnit.SECONDS);
					System.out.println("Device " + this.device.getReferenceNumber() + " had been successfully booked.");
					ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
		            scheduler2.schedule(() -> {
		            	this.available = true;
		            	this.user= null;
		                scheduler2.shutdown(); 
		            }, reservationPeriod, TimeUnit.SECONDS);
				} else {
					System.out.println("Someone else is using the device.");
				}
			} finally {
					lock.unlock();
			}
		} 
	}
	
	/**
     * Gets the electronic device associated with this resource.
     *
     * @return The electronic device.
     */
	public T getDevice() {
		return device;
	}
	/**
     * Sets the electronic device associated with this resource.
     *
     * @param device The new electronic device.
     */
	public void setDevice(T device) {
		this.device = device;
	}
	/**
     * Checks if the device is available.
     *
     * @return {@code true} if the device is available, {@code false} otherwise.
     */
	public boolean isAvailable() {
		return available;
	}
	/**
     * Sets the availability of the device.
     *
     * @param available The availability status.
     */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	/**
     * Gets the user currently using the device.
     *
     * @return The user.
     */
	public U getUser() {
		return user;
	}
	/**
     * Sets the user currently using the device.
     *
     * @param user The user.
     */
	public void setUser(U user) {
		this.user = user;
	}

	/**
     * Returns a string representation of this resource, including device information.
     *
     * @return A string representation of the resource.
     */
	@Override
	public String toString() {
		return this.device.toString();
	}

	 /**
     * Gets the admin associated with this resource.
     *
     * @return The admin.
     */
	public Admin getAdmin() {
		return admin;
	}
	/**
     * Sets the admin associated with this resource.
     *
     * @param admin The new admin.
     */
	public void setAdmin(Admin admin) {
		this.admin = admin;
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
     * Gets the list of previous users of this resource.
     *
     * @return A list of previous users.
     */
	public ArrayList<U> getPreviousUsers() {
		return previousUsers;
	}
	 /**
     * Sets the list of previous users of this resource.
     *
     * @param previousUsers The list of previous users.
     */
	public void setPreviousUsers(ArrayList<U> previousUsers) {
		this.previousUsers = previousUsers;
	}
	/**
     * Gets the faculty associated with this resource.
     *
     * @return The faculty.
     */
	public String getFaculty() {
		return faculty;
	}
	/**
     * Sets the faculty associated with this resource.
     *
     * @param faculty The new faculty.
     */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
}
