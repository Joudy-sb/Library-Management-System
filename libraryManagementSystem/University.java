package libraryManagementSystem;

public class University {

	public static void main(String[] args) throws InvalidInputInformation, UnauthorizedUserAction {
		final String CYAN = "\u001B[36m";
		final String RESET = "\u001B[0m"; 
		final String PURPLE = "\u001B[35m";
		
		// create three different faculties: engineering faculty, medical facutly, and business faculty
		Faculty msfea = new Faculty("Engineering");
		Faculty osb = new Faculty("Business");
		Faculty aubmc = new Faculty("Medicine");

		// create different students
		Student student1 = new Student("student1", "alc21@mail.aub.edu", "96181819191", "zouk mosbeh", "202209010");
		msfea.addStudents(student1);
		Student student2 = new Student("student2", "bob22@mail.aub.edu", "96181819292", "beirut", "202209011");
		msfea.addStudents(student2);
		Student student3 = new Student("student3", "charlie23@mail.aub.edu", "96181819393", "tripoli", "202209012");
		msfea.addStudents(student3);
		Student student4 = new Student("student4", "diana24@mail.aub.edu", "96181819494", "saida", "202209013");
		osb.addStudents(student4);
		Student student5 = new Student("student5", "edward25@mail.aub.edu", "96181819595", "byblos", "202209014");
		osb.addStudents(student5);
		Student student6 = new Student("student6", "fiona26@mail.aub.edu", "96181819696", "jounieh", "202209015");
		osb.addStudents(student6);
		Student student7 = new Student("student7", "george27@mail.aub.edu", "96181819797", "batroun", "202209016");
		aubmc.addStudents(student7);
		Student student8 = new Student("student8", "hannah28@mail.aub.edu", "96181819898", "tyre", "202209017");
		aubmc.addStudents(student8);
		Student student9 = new Student("student9", "ivy29@mail.aub.edu", "96181819999", "zahle", "202209018");	
		aubmc.addStudents(student9);
		
		// create different professors
		Professor professor1 = new Professor("Khaled", "khl21@mail.aub.edu", "96178767819", "trablos", "professor");
		msfea.addProfessors(professor1);
		Professor professor2 = new Professor("Lara", "lara22@mail.aub.edu", "96178767820", "beirut", "lecturer");
		msfea.addProfessors(professor2);
		Professor professor3 = new Professor("Mark", "mark23@mail.aub.edu", "96178767821", "saida", "professor");
		osb.addProfessors(professor3);
		Professor professor4 = new Professor("Nadine", "nadine24@mail.aub.edu", "96178767822", "tripoli", "lecturer");
		osb.addProfessors(professor4);
		Professor professor5 = new Professor("Omar", "omar25@mail.aub.edu", "96178767823", "zahle", "professor");
		aubmc.addProfessors(professor5);
		Professor professor6 = new Professor("Sara", "sara26@mail.aub.edu", "96178767824", "jounieh", "professor");
		aubmc.addProfessors(professor6);
		/*
		System.out.println(CYAN +"MSFEA STUDENTS" + RESET);
		for (Student student : msfea.getStudents()) {
		    System.out.println(student.toString());
		}
		System.out.println(CYAN +"MSFEA PROFESSORS" + RESET);
		for (Professor professor : msfea.getProfessors()) {
		    System.out.println(professor.toString());
		}/*
		System.out.println("OSB STUDENTS");
		for (Student student : osb.getStudents()) {
		    System.out.println(student.toString());
		}
		System.out.println("OSB PROFESSORS");
		for (Professor professor : osb.getProfessors()) {
		    System.out.println(professor.toString());
		}
		System.out.println("AUBMC STUDENTS");
		for (Student student : aubmc.getStudents()) {
		    System.out.println(student.toString());
		}
		System.out.println("AUBMC PROFESSORS");
		for (Professor professor : aubmc.getProfessors()) {
		    System.out.println(professor.toString());
		}*/
		
		// create different admins
		Admin admin1 = new Admin("Joe", "jbh13@mail.aub.edu", "96143243515", "ras beirut", "Monday-Friday from 9-5");
		Admin admin2 = new Admin("Anna", "anna14@mail.aub.edu", "96143243516", "hamra", "Monday-Friday from 8-4");
		Admin admin3 = new Admin("Sam", "sam15@mail.aub.edu", "96143243517", "saida", "Tuesday-Saturday from 10-6");
		Admin admin4 = new Admin("Maya", "maya16@mail.aub.edu", "96143243518", "tripoli", "Wednesday-Sunday from 7-3");
		Admin admin5 = new Admin("Alex", "alex17@mail.aub.edu", "96143243519", "jounieh", "Monday-Friday from 9-5");
		Admin admin6 = new Admin("Zara", "zara18@mail.aub.edu", "96143243520", "batroun", "Monday-Friday from 10-6");
		Admin admin7 = new Admin("Omar", "omar19@mail.aub.edu", "96143243521", "zahle", "Monday-Friday from 8-4");
		Admin admin8 = new Admin("Lina", "lina20@mail.aub.edu", "96143243522", "byblos", "Tuesday-Saturday from 9-5");
		Admin admin9 = new Admin("Peter", "peter21@mail.aub.edu", "96143243523", "tyre", "Wednesday-Sunday from 10-6");

		// creating libraries for msfea
		Library library1 = new Library(admin1);
		msfea.addLibraries(library1);
		Library library2 = new Library(admin2);
		msfea.addLibraries(library2);
		Library library3 = new Library(admin3);
		msfea.addLibraries(library3);
		// creating libraries for osb
		Library library4 = new Library(admin4);
		osb.addLibraries(library4);
		Library library5 = new Library(admin5);
		osb.addLibraries(library5);
		Library library6 = new Library(admin6);
		osb.addLibraries(library6);
		// creating libraries for aubmc
		Library library7 = new Library(admin7);
		aubmc.addLibraries(library7);
		Library library8 = new Library(admin8);
		aubmc.addLibraries(library8);
		Library library9 = new Library(admin9);
		aubmc.addLibraries(library9);
		/*
		System.out.println("MSFEA LIBRARY");
		for (Library library : msfea.getLibraries()) {
		    System.out.println(library.getFaculty());
		}
		System.out.println("OSB LIBRARY");
		for (Library library : osb.getLibraries()) {
		    System.out.println(library.getFaculty());
		}
		System.out.println("AUBMC LIBRARY");
		for (Library library : aubmc.getLibraries()) {
		    System.out.println(library.getFaculty());
		}*/
		
		
		// engineering books
		Book<User> book1 = new Book<User>("Introduction to Fluid Mechanics", "Bruce R. Munson", "Engineering", "A comprehensive guide to fluid mechanics concepts.", 
		    "Physical", "123456780", true, "2010");
		//System.out.println(CYAN + "Admin2 of library2 is trying to add book to the library1..." + RESET);
		library1.addBook(admin1, book1);
		book1.setLatePenaltyPeriodProfessor(admin1, 0);
		book1.setLatePenaltyPeriodStudent(admin1, 5);
		book1.setRenewalPassedProfessor(admin1, 2);
		book1.setRenewalPassedStudent(admin1, 1);
		book1.setRentPeriodProfessor(admin1, 10);
		book1.setRentPeriodStudent(admin1, 7);
		book1.setRenewalPeriodProfessor(admin1, 10);
		book1.setRenewalPeriodStudent(admin1, 5);
		Book<User> book2 = new Book<User>("Mechanics of Materials", "Ferdinand Beer", "Engineering", "A textbook covering stress, strain, and material behavior.", 
		    "Physical", "123456781", true, "2017");
		library1.addBook(admin1, book2);
		book2.setLatePenaltyPeriodProfessor(admin1, 0);
		book2.setLatePenaltyPeriodStudent(admin1, 5);
		book2.setRenewalPassedProfessor(admin1, 2);
		book2.setRenewalPassedStudent(admin1, 1);
		book2.setRentPeriodProfessor(admin1, 10);
		book2.setRentPeriodStudent(admin1, 7);
		book2.setRenewalPeriodProfessor(admin1, 10);
		book2.setRenewalPeriodStudent(admin1, 5);
		Book<User> book3 = new Book<User>("Electrical Engineering Principles", "Allan R. Hambley", "Engineering", "An overview of electrical engineering concepts.", 
		    "Physical", "123456782", true, "2013");
		library3.addBook(admin3, book3);
		book3.setLatePenaltyPeriodProfessor(admin3, 0);
		book3.setLatePenaltyPeriodStudent(admin3, 5);
		book3.setRenewalPassedProfessor(admin3, 2);
		book3.setRenewalPassedStudent(admin3, 1);
		book3.setRentPeriodProfessor(admin3, 10);
		book3.setRentPeriodStudent(admin3, 7);
		book3.setRenewalPeriodProfessor(admin3, 10);
		book3.setRenewalPeriodStudent(admin3, 5);
		
		// business books
		Book<User> book10 = new Book<User>("The Lean Startup", "Eric Ries", "Business", "A guide to building a startup using lean principles.", 
		    "Physical", "111111111", true, "2011");
		library4.addBook(admin4, book10);
		book10.setLatePenaltyPeriodProfessor(admin4, 0);
		book10.setLatePenaltyPeriodStudent(admin4, 7);
		book10.setRenewalPassedProfessor(admin4, 2);
		book10.setRenewalPassedStudent(admin4, 0);
		book10.setRentPeriodProfessor(admin4, 7);
		book10.setRentPeriodStudent(admin4, 5);
		book10.setRenewalPeriodProfessor(admin4, 5);
		book10.setRenewalPeriodStudent(admin4, 0);
		Book<User> book11 = new Book<User>("Think and Grow Rich", "Napoleon Hill", "Business", "A classic on achieving success through personal development.", 
		    "Physical", "222222222", true, "1937");
		library5.addBook(admin5, book11);
		book11.setLatePenaltyPeriodProfessor(admin5, 0);
		book11.setLatePenaltyPeriodStudent(admin5, 7);
		book11.setRenewalPassedProfessor(admin5, 2);
		book11.setRenewalPassedStudent(admin5, 0);
		book11.setRentPeriodProfessor(admin5, 7);
		book11.setRentPeriodStudent(admin5, 5);
		book11.setRenewalPeriodProfessor(admin5, 5);
		book11.setRenewalPeriodStudent(admin5, 0);
		Book<User> book12 = new Book<User>("Good to Great", "Jim Collins", "Business", "Insights into how companies transition from good to great.", 
		    "Physical", "333333333", true, "2001");
		library6.addBook(admin6, book12);
		book12.setLatePenaltyPeriodProfessor(admin6, 0);
		book12.setLatePenaltyPeriodStudent(admin6, 7);
		book12.setRenewalPassedProfessor(admin6, 2);
		book12.setRenewalPassedStudent(admin6, 0);
		book12.setRentPeriodProfessor(admin6, 7);
		book12.setRentPeriodStudent(admin6, 5);
		book12.setRenewalPeriodProfessor(admin6, 5);
		book12.setRenewalPeriodStudent(admin6, 0);
		
		
		// medical books
		Book<User> book20 = new Book<User>("Gray's Anatomy", "Henry Gray", "Medical", "The classic guide to human anatomy.", 
		    "Physical", "131313131", true, "1858");
		library7.addBook(admin7, book20);
		book20.setLatePenaltyPeriodProfessor(admin7, 0);
		book20.setLatePenaltyPeriodStudent(admin7, 20);
		book20.setRenewalPassedProfessor(admin7, 1);
		book20.setRenewalPassedStudent(admin7, 1);
		book20.setRentPeriodProfessor(admin7, 15);
		book20.setRentPeriodStudent(admin7, 10);
		book20.setRenewalPeriodProfessor(admin7, 5);
		book20.setRenewalPeriodStudent(admin7, 5);
		Book<User> book21 = new Book<User>("Harrison's Principles of Internal Medicine", "J. Larry Jameson", "Medical", "A comprehensive resource for internal medicine.", 
		    "Physical", "141414141", true, "1950");
		library8.addBook(admin8, book21);
		book21.setLatePenaltyPeriodProfessor(admin8, 0);
		book21.setLatePenaltyPeriodStudent(admin8, 20);
		book21.setRenewalPassedProfessor(admin8, 1);
		book21.setRenewalPassedStudent(admin8, 1);
		book21.setRentPeriodProfessor(admin8, 15);
		book21.setRentPeriodStudent(admin8, 10);
		book21.setRenewalPeriodProfessor(admin8, 5);
		book21.setRenewalPeriodStudent(admin8, 5);
		Book<User> book22 = new Book<User>("Guyton and Hall Textbook of Medical Physiology", "John E. Hall", "Medical", "A detailed explanation of human physiology.", 
		    "Physical", "151515151", true, "1956");
		library9.addBook(admin9, book22);
		book22.setLatePenaltyPeriodProfessor(admin9, 0);
		book22.setLatePenaltyPeriodStudent(admin9, 20);
		book22.setRenewalPassedProfessor(admin9, 1);
		book22.setRenewalPassedStudent(admin9, 1);
		book22.setRentPeriodProfessor(admin9, 15);
		book22.setRentPeriodStudent(admin9, 10);
		book22.setRenewalPeriodProfessor(admin9, 5);
		book22.setRenewalPeriodStudent(admin9, 5);
		
		// create electronic resources for library 1
		PC pc1 = new PC("HP", "110", "oxy201");
		ElectronicResource<ElectronicDevice, User> er1 = new ElectronicResource<ElectronicDevice, User>(pc1);
		Tablet t1 = new Tablet("Apple", "210", "1");
		ElectronicResource<ElectronicDevice, User> er2 = new ElectronicResource<ElectronicDevice, User>(t1);
		library1.addElectronicResource(admin1, er1);
		library1.addElectronicResource(admin1, er2);
		er1.setLatePenaltyPeriodProfessor(admin1, 0);
		er1.setLatePenaltyPeriodStudent(admin1, 5);
		er1.setRenewalPassedProfessor(admin1, 2);
		er1.setRenewalPassedStudent(admin1, 1);
		er1.setRentPeriodProfessor(admin1, 10);
		er1.setRentPeriodStudent(admin1, 7);
		er1.setRenewalPeriodProfessor(admin1, 10);
		er1.setRenewalPeriodStudent(admin1, 5);
		er2.setLatePenaltyPeriodProfessor(admin1, 0);
		er2.setLatePenaltyPeriodStudent(admin1, 5);
		er2.setRenewalPassedProfessor(admin1, 2);
		er2.setRenewalPassedStudent(admin1, 1);
		er2.setRentPeriodProfessor(admin1, 10);
		er2.setRentPeriodStudent(admin1, 7);
		er2.setRenewalPeriodProfessor(admin1, 10);
		er2.setRenewalPeriodStudent(admin1, 5);
		
		// create electronic resources for library 4
		PC pc4 = new PC("HP","120", "osb321");
		ElectronicResource<ElectronicDevice, User> er7 = new ElectronicResource<ElectronicDevice, User>(pc4);
		Tablet t4 = new Tablet("Apple", "220", "5");
		ElectronicResource<ElectronicDevice, User> er8 = new ElectronicResource<ElectronicDevice, User>(t4);
		library4.addElectronicResource(admin4, er7);
		library4.addElectronicResource(admin4, er8);
		er7.setLatePenaltyPeriodProfessor(admin4, 0);
		er7.setLatePenaltyPeriodStudent(admin4, 7);
		er7.setRenewalPassedProfessor(admin4, 2);
		er7.setRenewalPassedStudent(admin4, 0);
		er7.setRentPeriodProfessor(admin4, 7);
		er7.setRentPeriodStudent(admin4, 5);
		er7.setRenewalPeriodProfessor(admin4, 5);
		er7.setRenewalPeriodStudent(admin4, 0);
		er8.setLatePenaltyPeriodProfessor(admin4, 0);
		er8.setLatePenaltyPeriodStudent(admin4, 7);
		er8.setRenewalPassedProfessor(admin4, 2);
		er8.setRenewalPassedStudent(admin4, 0);
		er8.setRentPeriodProfessor(admin4, 7);
		er8.setRentPeriodStudent(admin4, 5);
		er8.setRenewalPeriodProfessor(admin4, 5);
		er8.setRenewalPeriodStudent(admin4, 0);
		
		// create electronic resources for library 7
		PC pc7 = new PC("HP", "130", "med909");
		ElectronicResource<ElectronicDevice, User> er13 = new ElectronicResource<ElectronicDevice, User>(pc7);
		Tablet t7 = new Tablet("Apple", "230", "2");
		ElectronicResource<ElectronicDevice, User> er14 = new ElectronicResource<ElectronicDevice, User>(t7);
		library7.addElectronicResource(admin7, er13);
		library7.addElectronicResource(admin7, er14);
		er13.setLatePenaltyPeriodProfessor(admin7, 0);
		er13.setLatePenaltyPeriodStudent(admin7, 20);
		er13.setRenewalPassedProfessor(admin7, 1);
		er13.setRenewalPassedStudent(admin7, 1);
		er13.setRentPeriodProfessor(admin7, 15);
		er13.setRentPeriodStudent(admin7, 10);
		er13.setRenewalPeriodProfessor(admin7, 5);
		er13.setRenewalPeriodStudent(admin7, 5);
		er14.setLatePenaltyPeriodProfessor(admin7, 0);
		er14.setLatePenaltyPeriodStudent(admin7, 20);
		er14.setRenewalPassedProfessor(admin7, 1);
		er14.setRenewalPassedStudent(admin7, 1);
		er14.setRentPeriodProfessor(admin7, 15);
		er14.setRentPeriodStudent(admin7, 10);
		er14.setRenewalPeriodProfessor(admin7, 5);
		er14.setRenewalPeriodStudent(admin7, 5);
		
		// create room for library 1-9
		MeetingRoom<User> m1 = new MeetingRoom<User>("101", "96112345678");
		library1.addMeetingRoom(admin1, m1);
		m1.setLatePenaltyPeriodProfessor(admin1, 0);
		m1.setLatePenaltyPeriodStudent(admin1, 5);
		m1.setRenewalPassedProfessor(admin1, 2);
		m1.setRenewalPassedStudent(admin1, 1);
		m1.setRentPeriodProfessor(admin1, 10);
		m1.setRentPeriodStudent(admin1, 7);
		m1.setRenewalPeriodProfessor(admin1, 10);
		m1.setRenewalPeriodStudent(admin1, 5);
		
		MeetingRoom<User> m4 = new MeetingRoom<User>("104", "96112345681");
		library4.addMeetingRoom(admin4, m4);
		m4.setLatePenaltyPeriodProfessor(admin4, 0);
		m4.setLatePenaltyPeriodStudent(admin4, 7);
		m4.setRenewalPassedProfessor(admin4, 2);
		m4.setRenewalPassedStudent(admin4, 0);
		m4.setRentPeriodProfessor(admin4, 7);
		m4.setRentPeriodStudent(admin4, 5);
		m4.setRenewalPeriodProfessor(admin4, 5);
		m4.setRenewalPeriodStudent(admin4, 0);
		
		MeetingRoom<User> m7 = new MeetingRoom<User>("107", "96112345684");
		library7.addMeetingRoom(admin7, m7);
		m7.setLatePenaltyPeriodProfessor(admin7, 0);
		m7.setLatePenaltyPeriodStudent(admin7, 20);
		m7.setRenewalPassedProfessor(admin7, 1);
		m7.setRenewalPassedStudent(admin7, 1);
		m7.setRentPeriodProfessor(admin7, 15);
		m7.setRentPeriodStudent(admin7, 10);
		m7.setRenewalPeriodProfessor(admin7, 5);
		m7.setRenewalPeriodStudent(admin7, 5);
		
		/*
		System.out.println(CYAN + "Professor1 tries to rent book1..." + RESET);
		book1.rentBook(professor1);
		System.out.println(CYAN + "Professor1 tries to get an extension for the rent of book1"+ RESET);
		book1.renewRental(professor1);
		System.out.println(CYAN + "Professor1 tries to get another extension for the rent of book1"+ RESET);
		book1.renewRental(professor1);
		
		System.out.println(PURPLE + "System takes 40 second break..." + RESET);
		try {
            Thread.sleep(40000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }
		System.out.println(CYAN + "Professor1 returns book1 late..." + RESET);
		book1.returnBook(professor1);
		try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }
		System.out.println(CYAN + "Professor1 tries to rent book1..." + RESET);
		book1.rentBook(professor1);
		
		/*
		
		System.out.println(CYAN + "Student1 tries to rent book1..." + RESET);
		book1.rentBook(student1);
		System.out.println(CYAN + "Student1 returns book1 on time..." + RESET);
		book1.returnBook(student1);
		System.out.println(CYAN + "Student2 tries to rent book1..." + RESET);
		book1.rentBook(student2);
		System.out.println(CYAN + "Student2 returns book1 on time..." + RESET);
		book1.returnBook(student2);
		System.out.println(CYAN + "Student3 tries to rent book2..." + RESET);
		book2.rentBook(student3);
		System.out.println(CYAN + "Student3 returns book2 on time..." + RESET);
		book2.returnBook(student3);
		
		
		System.out.println(CYAN + "Getting most popular book..." + RESET);
		System.out.println(library1.bestSellerBook());
		
		
		/*
		System.out.println(CYAN + "Student2 tries to book room m1  for 2 seconds..." + RESET);
		m1.bookRoom(student2, 2);
		
		System.out.println(PURPLE + "System takes 2 second break..."+ RESET);
		try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }

		System.out.println(CYAN + "Student2 tries to book room m1 again for 5 seconds..." + RESET);
		m1.bookRoom(student2, 5);
		
		/*
		System.out.println(CYAN + "Student1 books borrowed: " + RESET);
		for (String b: student1.getBorrowedBooks()) {
			System.out.println(b.toString());
		}
		
		System.out.println(CYAN + "Student1 books returned: " + RESET);
		for (String b: student1.getReturnedBooks()) {
			System.out.println(b.toString());
		}
		/*
		System.out.println(CYAN + "Engineering student1 tries to book osb room m4 for 5 seconds..." + RESET);
		m4.bookRoom(student1, 5);
		System.out.println(CYAN + "Engineering student1 tries to rent medical book20..." + RESET);
		book20.rentBook(student1);

		System.out.println(CYAN + "Business student5 tries to use device from engineering pc1 for 5 seconds..." + RESET);
		er1.useDevice(student5, 5);
		
		/*
		System.out.println(CYAN + "Student1 tries to use device pc1 for 5 seconds..." + RESET);
		er1.useDevice(student1, 5);
		
		System.out.println(CYAN + "Student2 tries to use device pc1 for 5 seconds..." + RESET);
		er1.useDevice(student2, 5);
		
		System.out.println(PURPLE + "System takes 10 second break..."+ RESET);
		try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }
		
		System.out.println(CYAN + "Student2 tries to use device pc1 for 5 seconds..." + RESET);
		er1.useDevice(student2, 5);
		
		System.out.println(CYAN + "Student7 tries to use device t7 for 25 seconds..." + RESET);
		er14.useDevice(student2, 25);
		
		/*
				
		/*
		System.out.println(CYAN + "Student1 tries to book room m1 for 5 seconds..." + RESET);
		m1.bookRoom(student1, 5);
		
		System.out.println(CYAN + "Student2 tries to book room m1  for 5 seconds..." + RESET);
		m1.bookRoom(student2, 5);
		
		System.out.println(PURPLE + "System takes 10 second break..."+ RESET);
		try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }
		
		System.out.println(CYAN + "Student2 tries to book room m1 for 8 seconds..." + RESET);
		m1.bookRoom(student2, 8);
		
		System.out.println(CYAN + "Student4 tries to book room m4 for 15 seconds..." + RESET);
		m1.bookRoom(student4, 15);
		
		/*
		System.out.println(CYAN + "Student1 tries to rent book10..." + RESET);
		book10.rentBook(student1);
		
		
		System.out.println(CYAN + "Student1 tries to rent book1..." + RESET);
		book1.rentBook(student1);

		System.out.println(CYAN + "Student2 tries to rent book1..."+ RESET);
		book1.rentBook(student2);
		
		System.out.println(PURPLE + "System takes 10 second break..."+ RESET);
		try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted."+ RESET);
        }
		
		System.out.println(CYAN + "Student1 returns book1 late..."+ RESET);
		book1.returnBook(student1);

		System.out.println(book1.getUser().getName()+ " has book1 now");
		System.out.println(CYAN + "Student2 tries to get an extension for the rent of book1"+ RESET);
		book1.renewRental(student2);
		System.out.println(CYAN + "Student2 tries to get another extension for the rent of book1"+ RESET);
		book1.renewRental(student2);
		
		System.out.println(CYAN + "Student2 returns book1 on time..."+ RESET);
		book1.returnBook(student2);
		
		System.out.println(CYAN + "Student1 tries to rent book2..."+ RESET);
		book2.rentBook(student1);
		
		System.out.println(PURPLE + "System takes 10 second break..."+ RESET);
		try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
		
		System.out.println(CYAN + "Student1 tries to rent book2 again..."+ RESET);
		book2.rentBook(student1);

		System.out.println(CYAN + "Student1 returns book2 on time ..." + RESET);
		book2.returnBook(student1);
		
		
		/*
		System.out.println(CYAN + "Student1 tries to rent book1..." + RESET);
		book1.rentBook(student1);
		System.out.println(CYAN + "Student2 tries to return book1..." + RESET);
		book1.returnBook(student2);
		/*
		
		System.out.println(CYAN + "User can search book by genre (ex: engineering)..." + RESET);
		System.out.println(library1.findBookbyGenre("Engineering"));
		*/
		
		
	}

}
