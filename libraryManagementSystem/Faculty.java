package libraryManagementSystem;

import java.util.ArrayList;

/**
 * Represents a faculty in the library management system.
 * A faculty contains professors, students, and libraries.
 */
public class Faculty {
    private String name;
    private ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Library> libraries = new ArrayList<>();

    /**
     * Constructs a new Faculty with the specified name.
     *
     * @param name The name of the faculty.
     */
    public Faculty(String name) {
        this.setName(name);
    }

    /**
     * Gets the name of the faculty.
     *
     * @return The name of the faculty.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the faculty.
     *
     * @param name The new name of the faculty.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of professors in the faculty.
     *
     * @return A list of professors in the faculty.
     */
    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    /**
     * Adds a professor to the faculty if they do not already belong to a faculty.
     *
     * @param professor The professor to add to the faculty.
     */
    public void addProfessors(Professor professor) {
        if (professor.getFaculty().isEmpty()) {
            this.professors.add(professor);
            professor.setFaculty(this.name);
        } else {
            System.out.println("Professor already belongs to " + this.getClass().getName());
        }
    }

    /**
     * Gets the list of students in the faculty.
     *
     * @return A list of students in the faculty.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Adds a student to the faculty if they do not already belong to a faculty.
     *
     * @param student The student to add to the faculty.
     */
    public void addStudents(Student student) {
        if (student.getFaculty().isEmpty()) {
            this.students.add(student);
            student.setFaculty(this.name);
        } else {
            System.out.println("Student already belongs to " + this.getClass().getName());
        }
    }

    /**
     * Gets the list of libraries associated with the faculty.
     *
     * @return A list of libraries associated with the faculty.
     */
    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    /**
     * Adds a library to the faculty if it does not already belong to a faculty.
     *
     * @param library The library to add to the faculty.
     */
    public void addLibraries(Library library) {
        if (library.getFaculty().isEmpty()) {
            this.libraries.add(library);
            library.setFaculty(this.name);
        } else {
            System.out.println("Library already belongs to " + this.getClass().getName());
        }
    }
}