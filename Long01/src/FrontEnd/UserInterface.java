package FrontEnd;

import Backend.Student;
import Backend.StudentDb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Long 01 - Main user interface
 *
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-21
 */
public class UserInterface {

    // Validators. prefix as their value it validates
    
    /**
     * What does it really validate? studentIdValidation - it validates the
     * student id, and only true when it is an integer nameValidation - it
     * validates name, only when it contains digits, letters, dot, and spaces
     * semesterValidation - it validates semester, only from 00-99
     * courseValidation - Only when "Java", or ".Net", or 'C/C++"
     */
    private final String studentIdValidation = "[0-9]{1,10}";
    private final String nameValidation = "[a-zA-Z ]{1,254}";
    private final String semesterValidation = "[0-9]{1,2}";
    private final String courseValidation = "(Java|\\.Net|C/C\\+\\+)";
    private final String countValidation = "[0-9]{1,}";
    private final String updateOrDeleteValidation = "U|D|u|d";

    // The minimum number of students user must enter in a row
    private final int minUserInput = 10;

    // User input. See in constructor
    private final Scanner sc;

    // User database
    private final StudentDb studentDb;

    /**
     * Constructor of the user interface. It initialize the student database and
     * user input
     */
    public UserInterface() {
        studentDb = new StudentDb();
        sc = new Scanner(System.in);
    }

    /**
     * Enter a value that correspond the value that it called from
     *
     * @param message as customized input message
     * @param validation as string validation
     * @param error In case of having error, show that error
     * @param isTrim If you want to trim the string (no respect to user input)
     * @return String as validated user input
     */
    private String enterAValue(String message, String validation, String error, boolean isTrim) {
        // Assume the value is empty string
        String value = "";
        // Loop for correct format
        while (!value.matches(validation)) {
            System.out.print(message);
            String valueUI = (isTrim) ? (sc.nextLine().trim()) : (sc.nextLine());

            // Validate user input, if match with the criteria, assign with the 
            // return value, if not, asking user again
            if (!valueUI.matches(validation)) {
                // Notify user
                System.out.println(error);
            } else {
                // assign value
                value = valueUI;
            }
        }

        // return value
        return (value);
    }

    /**
     * Selection One - add student to the database
     */
    private void selectionOne() {
        // Initialize the default value of number of students
        int numberofStudents = -1;

        // Get the number of students input.
        while (numberofStudents < minUserInput) {
            try {
                // Let user input. Parse to integer and store in the 'numberofStudents'
                numberofStudents = Integer.parseInt(this.enterAValue("Please enter number of student you want to add: ", countValidation, "Must be a number (>= 10)", true));

                // If user enters a number of student lower than the required number
                if (numberofStudents < minUserInput) {
                    System.out.println("Must be a number (>= 10)");
                }
            } catch (NumberFormatException ex) {
                // If user enters non-digit value, notify and "gently" ask them to reenter the value
                System.out.println("Must be a number (>= 10)");
            }
        }

        for (int i = 0; i < numberofStudents; i++) {
            // Assume if the student data is broken and unable to add
            boolean isAddingSuccess = false;

            while (!isAddingSuccess) {
                System.out.println("Information for student: " + (i + 1));

                // Ask user to enter id
                String studentId = this.enterAValue("Enter student Id: ", studentIdValidation, "Must be a number (1 to 10 digits)", true);
                while (studentDb.isIdExisted(studentId)) {
                    System.out.println("That id is already claimed. Please re-enter");
                    studentId = this.enterAValue("Enter student Id: ", studentIdValidation, "Must be a number (1 to 10 digits)", true);
                }

                // Ask user to enter name
                String studentName = this.enterAValue("Enter student name: ", nameValidation, "Must be letters (Max 254 characters)", true);

                // Ask user to enter semester.
                String semester = this.enterAValue("Enter semester: ", semesterValidation, "Must be a number (1 to 2 digits)", true);

                // Ask user to enter course name.
                String courseName = this.enterAValue("Enter course name: ", courseValidation, "Either \"Java\" or \".Net\" or \"C/C++\" (case sensitive)", true);

                // If unsuccess
                if (!studentDb.addStudent(studentId, studentName, semester, courseName)) {
                    System.out.println("Unable to add the student " + studentName + ". Please re-enter the student information");
                } else {
                    // Break the inner while loop and continue with the next student
                    isAddingSuccess = true;
                }
            }
        }

        // Default value as an empty string.
        String orderSelection = this.enterAValue("Do you want to order now (Y/N): ", "Y|N|y|n", "Only Y or N (case insensitive)", true);

        // If yes, Sort the data by student name
        if (orderSelection.matches("Y|y")) {
            studentDb.sortStudent();
            System.out.println("Order is completed.");
        }
    }

    /**
     * Selection Two - from student part-name find its semester and course enrolled
     */
    private void selectionTwo() {
        // Sort the student first
        studentDb.sortStudent();

        // Ask user to enter name.
        String studentName = this.enterAValue("Enter student name: ", nameValidation, "Must be letters (Max 254 characters)", true);

        // Get the student info from the database backend
        ArrayList<Student> foundStudents = studentDb.findStudent(studentName);

        // Check if the found is empty or not. (To prevent empty or null)
        if (foundStudents.isEmpty()) {
            System.out.println("No student found.");
        } else {
            System.out.println(
                    "+--------------+----------+-------------+\n"
                    + "| Student name | Semester | Course Name |\n"
                    + "+--------------+----------+-------------+"
            );

            // If found, iterate through and get student information
            for (Student st : foundStudents) {
                System.out.println(String.format(
                        "| %12.12s | %8d | %11s |",
                        st.getStudentName(),
                        st.getSemester(),
                        st.getCourseName()
                ));
            }

            System.out.println(
                    "+--------------+----------+-------------+"
            );
        }
    }
    
    /**
     * Selection Three - Update or Delete student based on ID
     */
    private void selectionThree() {

        // Ask user to enter id.
        String studentId = this.enterAValue("Enter student Id: ", studentIdValidation, "Must be a number (1 to 10 digits)", true);
        while (!studentDb.isIdExisted(studentId)) {
            System.out.println("That id is not found. Please re-enter");
            studentId = this.enterAValue("Enter student Id: ", studentIdValidation, "Must be a number (1 to 10 digits)", true);
        }

        // Ask user to choose if they want to delete, or update the existing data
        String dataSelection = this.enterAValue("Do you want to update (U) or delete (D) student: ", updateOrDeleteValidation, "Must be U or D only (case insensitive).", true);

        if (dataSelection.matches("u|U")) {
            // Update user, call the student database backend
            String studentName = this.enterAValue("Enter student name: ", nameValidation, "Must be letters (Max 254 characters)", true);

            // Perform the update
            studentDb.updateStudentDb(studentId, studentName);
        } else if (dataSelection.matches("d|D")) {
            // Delete user, call the student database backend 
            studentDb.updateStudentDb(studentId);
        }
    }

    /**
     * Selection Four - View report
     */
    private void selectionFour() {
        // Generate the report result, from the student database backend
        HashMap<String, Integer> reportResult = studentDb.reportData();

        // Count for iteration
        int iterationCount = 1;

        System.out.println(
                "+-----+--------------+--------+-----------------+\n"
                + "| No. | Student name | Course | Total of course |\n"
                + "+-----+--------------+--------+-----------------+"
        );

        // Do the iteration
        for (HashMap.Entry<String, Integer> i : reportResult.entrySet()) {
            // Split the raw data into segments (as key)
            String[] studentKey = i.getKey().split(",");

            // As Value
            int totalCourse = i.getValue();

            // Print out that value
            System.out.println(String.format(
                    "| %3d | %12.12s | %6.6s | %15d |",
                    iterationCount,
                    studentKey[0],
                    studentKey[1],
                    totalCourse)
            );

            // Raise the interation value by one
            iterationCount += 1;
        }

        System.out.println(
                "+-----+--------------+--------+-----------------+"
        );
    }

    /**
     * Display user interface
     */
    public void displayUI() {
        // The main user interface
        while (true) {
            try {
                System.out.print(
                        " WELCOME TO STUDENT MANAGEMENT\n"
                        + " 1. Create\n"
                        + " 2. Find and Sort\n"
                        + " 3. Update/Delete\n"
                        + " 4. Report\n"
                        + " 5. Exit\n"
                        + "Please choose: "
                );

                // Ask user what they want
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        selectionOne();
                        break;
                    case 2:
                        selectionTwo();
                        break;
                    case 3:
                        selectionThree();
                        break;
                    case 4:
                        selectionFour();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        // Fallback
                        System.out.println("Invalid input.");
                        break;
                }

            } catch (NumberFormatException num_ex) {
                // Fallback
                System.out.println("Invalid input.");
            }
        }
    }
}
