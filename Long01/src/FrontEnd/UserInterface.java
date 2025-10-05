package FrontEnd;

import Backend.Student;
import Backend.StudentDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Long 01 - Main user interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-21
 */
public class UserInterface {
    
    // Validators. prefix as their value it validates
    
    /**
     * What does it really validate?
        * studentIdValidation - it validates the student id, and only true when it is an integer
        * nameValidation - it validates name, only when it contains digits, letters, dot, and spaces
        * semesterValidation - it validates semester, only from 00-99
        * courseValidation - Only when ...
     */
    
    private final String studentIdValidation = "[0-9]{1,9}";
    private final String nameValidation = "[a-zA-Z0-9 .]{1,254}";
    private final String semesterValidation = "[0-9]{1,2}";
    private final String courseValidation = "(Java|.Net|C/C\\+\\+)";
    
    // The minimum number of students user must enter in a row
    private final int minUserInput = 10;
    
    // User input. See in constructor
    private final Scanner sc;
    
    // User database
    private final StudentDb studentDb;
    
    /**
     * Constructor of the user interface.
     * It initialize the student database and user input
     */
    public UserInterface() {
        studentDb = new StudentDb();
        sc = new Scanner(System.in);
    }
    
    /**
     * Selection One
     */
    private void selectionOne() {
        
        // Initialize the default value of number of students
        int numberofStudents = -1;
        
        // Get the number of students input.
        while (numberofStudents < minUserInput) {
            System.out.print("Please enter number of student you want to add: ");
            
            try {
                // Let user input. Parse to integer and store in the 'numberofStudents'
                numberofStudents = Integer.parseInt(sc.nextLine());
                
                // If user enters a number of student lower than the required number
                //  notify and "gently" ask them to reenter the value
                if (numberofStudents < minUserInput) {
                    System.out.println("Invalid input, please try again.");
                }
                
            } catch (NumberFormatException ex) {
                // If user enters non-digit value, notify and "gently" ask them to reenter the value
                System.out.println("Invalid input, please try again.");
            }
        }
        
        for (int i = 0; i < numberofStudents; i++) {
            
            // Assume if the student data is broken and unable to add
            boolean isAddingSuccess = false;
            
            while (!isAddingSuccess)  {
                System.out.println("Information for student: " + (i + 1));
                
                // Ask user to enter id. If they enter invalid data, 
                //  notify and "gently" ask them to reenter the value
                System.out.print("Enter student Id: ");
                String studentId = sc.nextLine();
                while (!studentId.matches(studentIdValidation)) {
                    System.out.print("Invalid student Id.\n Re-enter student Id: ");
                    studentId = sc.nextLine();
                }
                
                // Ask user to enter name. If they enter invalid data, 
                //  notify and "gently" ask them to reenter the value
                System.out.print("Enter student name: ");
                String studentName = sc.nextLine();
                while (!studentName.matches(nameValidation)) {
                    System.out.print("Invalid student name.\nEnter student name: ");
                    studentName = sc.nextLine();
                }
                
                // Ask user to enter semester. If they enter invalid data, 
                //  notify and "gently" ask them to reenter the value
                System.out.print("Enter semester: ");
                String semester = sc.nextLine();
                while (!semester.matches(semesterValidation)) {
                    System.out.print("Invalid semester.\nEnter semester: ");
                    semester = sc.nextLine();
                }
                
                // Ask user to enter course name. If they enter invalid data, 
                //  notify and "gently" ask them to reenter the value
                System.out.print("Enter course name: ");
                String courseName = sc.nextLine();
                while (!courseName.matches(courseValidation)) {
                    System.out.print("Wrong course name (Only 'Java', '.Net', or 'C/C++' are accepted).\nEnter course name: ");
                    courseName = sc.nextLine();
                }
                
                // If unsuccess
                if (!studentDb.addStudent(studentId, studentName, semester, courseName)) {
                    isAddingSuccess = false;
                    System.out.println(
                            "Unable to add the student " + 
                            studentName + 
                            "\nPlease re-enter the student information"
                    );
                } else {
                    // Break the inner while loop and continue with the next student
                    isAddingSuccess = true; 
                }
            }
        }
        
        // Ask user if they want to sort the data by student name
        
        // Default value as an empty string.
        String orderSelection = "";
        
        // Ask user to choose if they want to sort data. 
        // If they enter invalid data,  notify and "gently" ask them to reenter the value
        while (!orderSelection.toLowerCase().equals("y") && !orderSelection.toLowerCase().equals("n")) {
            System.out.print("Do you want to order now (Y/N): ");
            orderSelection = sc.nextLine();
        }
        
        // Sort the data by student name
        if (orderSelection.toLowerCase().equals("y")) {
            studentDb.sortStudent();
            System.out.println("Order is completed.");
        }
    }
    
    /**
     * Selection Two
     */
    private void selectionTwo() {
        // Sort the student first
        studentDb.sortStudent();
        
        // Ask user to enter name. If they enter invalid data, 
        //  notify and "gently" ask them to reenter the value
        System.out.print("Enter student name: ");
        String studentName = sc.nextLine();
        while (!studentName.matches(nameValidation)) {
            System.out.print("Invalid student name.\nEnter student name: ");
            studentName = sc.nextLine();
        }
        
        // Get the student info from the database backend
        ArrayList<Student> foundStudents = studentDb.findStudent(studentName);
        
        // Check if the found is empty or not. (To prevent empty or null)
        if (foundStudents.isEmpty()) {
            System.out.println("No student found.");
        } else {
            System.out.println(
                    "+--------------+----------+-------------+\n" +
                    "| Student name | Semester | Course Name |\n" +
                    "+--------------+----------+-------------+"
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
    
    private void selectionThree() {
        
        // Ask user to enter id. If they enter invalid data, 
        //  notify and "gently" ask them to reenter the value
        System.out.print("Enter student Id: ");
        String studentId = sc.nextLine();
        while (!studentId.matches(studentIdValidation)) {
            System.out.print("Invalid student Id.\n Re-enter student Id: ");
            studentId = sc.nextLine();
        }
        
        // Ask user to choose if they want to delete, or update the existing data
        // If they enter invalid data, notify and "gently" ask them to reenter the value
        String dataSelection = "";
        while (!dataSelection.toLowerCase().equals("u") && !dataSelection.toLowerCase().equals("d")) {
            System.out.print("Do you want to update (U) or delete (D) student: ");
            dataSelection = sc.nextLine();
        }
        
        if (dataSelection.toLowerCase().equals("u")) {
            // Update user, call the student database backend
            System.out.print("Enter student name: ");
            String studentName = sc.nextLine();
            while (!studentName.matches(nameValidation)) {
                System.out.print("Invalid student name.\nEnter student name: ");
                studentName = sc.nextLine();
            }
            
            // Perform the update
            studentDb.updateStudentDb(studentId, studentName);
        } else {
            // Delete user, call the student database backend 
            studentDb.updateStudentDb(studentId);
        }
    }
    
    private void selectionFour() {
        // Generate the report result, from the student database backend
        HashMap<String, Integer> reportResult = studentDb.reportData();
        
        // Count for iteration
        int iterationCount = 1;
        
        System.out.println(
            "+-----+--------------+--------+-----------------+\n" +
            "| No. | Student name | Course | Total of course |\n" +
            "+-----+--------------+--------+-----------------+"
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
                studentKey[1],
                studentKey[2],
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
     *
     */
    public void displayUI() {
        // The main user interface
        while (true) {
            try {
                System.out.print(
                    " WELCOME TO STUDENT MANAGEMENT\n" +
                    " 1. Create\n" +
                    " 2. Find and Sort\n" +
                    " 3. Update/Delete\n" +
                    " 4. Report\n" +
                    " 5. Exit\n" +
                    "Please choose: "
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
