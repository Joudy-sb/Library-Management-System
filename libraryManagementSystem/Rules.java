package libraryManagementSystem;

/**
 * Defines the rules for managing rental periods, late penalties, and renewal passes for students and professors.
 * This interface is implemented by classes to enforce specific library policies.
 */
public interface Rules {

    /**
     * Gets the renewal period for students.
     *
     * @return The renewal period for students in seconds.
     */
    public int getRenewalPeriodStudent();

    /**
     * Sets the renewal period for students.
     *
     * @param admin               The admin making the change.
     * @param renewalPeriodStudent The new renewal period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRenewalPeriodStudent(Admin admin, int renewalPeriodStudent) throws UnauthorizedUserAction;

    /**
     * Gets the rental period for students.
     *
     * @return The rental period for students in seconds.
     */
    public int getRentPeriodStudent();

    /**
     * Sets the rental period for students.
     *
     * @param admin           The admin making the change.
     * @param rentPeriodStudent The new rental period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRentPeriodStudent(Admin admin, int rentPeriodStudent) throws UnauthorizedUserAction;

    /**
     * Gets the late penalty period for students.
     *
     * @return The late penalty period for students in seconds.
     */
    public int getLatePenaltyPeriodStudent();

    /**
     * Sets the late penalty period for students.
     *
     * @param admin                  The admin making the change.
     * @param latePenaltyPeriodStudent The new late penalty period for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setLatePenaltyPeriodStudent(Admin admin, int latePenaltyPeriodStudent) throws UnauthorizedUserAction;

    /**
     * Gets the number of renewal passes allowed for students.
     *
     * @return The number of renewal passes allowed for students.
     */
    public int getRenewalPassedStudent();

    /**
     * Sets the number of renewal passes allowed for students.
     *
     * @param admin               The admin making the change.
     * @param renewalPassedStudent The new number of renewal passes allowed for students.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRenewalPassedStudent(Admin admin, int renewalPassedStudent) throws UnauthorizedUserAction;

    /**
     * Gets the renewal period for professors.
     *
     * @return The renewal period for professors in seconds.
     */
    public int getRenewalPeriodProfessor();

    /**
     * Sets the renewal period for professors.
     *
     * @param admin                  The admin making the change.
     * @param renewalPeriodProfessor The new renewal period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRenewalPeriodProfessor(Admin admin, int renewalPeriodProfessor) throws UnauthorizedUserAction;

    /**
     * Gets the rental period for professors.
     *
     * @return The rental period for professors in seconds.
     */
    public int getRentPeriodProfessor();

    /**
     * Sets the rental period for professors.
     *
     * @param admin            The admin making the change.
     * @param rentPeriodProfessor The new rental period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRentPeriodProfessor(Admin admin, int rentPeriodProfessor) throws UnauthorizedUserAction;

    /**
     * Gets the late penalty period for professors.
     *
     * @return The late penalty period for professors in seconds.
     */
    public int getLatePenaltyPeriodProfessor();

    /**
     * Sets the late penalty period for professors.
     *
     * @param admin                   The admin making the change.
     * @param latePenaltyPeriodProfessor The new late penalty period for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setLatePenaltyPeriodProfessor(Admin admin, int latePenaltyPeriodProfessor) throws UnauthorizedUserAction;

    /**
     * Gets the number of renewal passes allowed for professors.
     *
     * @return The number of renewal passes allowed for professors.
     */
    public int getRenewalPassedProfessor();

    /**
     * Sets the number of renewal passes allowed for professors.
     *
     * @param admin                 The admin making the change.
     * @param renewalPassedProfessor The new number of renewal passes allowed for professors.
     * @throws UnauthorizedUserAction If the admin is not authorized to make this change.
     */
    public void setRenewalPassedProfessor(Admin admin, int renewalPassedProfessor) throws UnauthorizedUserAction;
}
