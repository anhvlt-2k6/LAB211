package short07.FrontEnd;

import java.util.HashMap;
import java.util.List;
import short07.BackEnd.StudentDb;
import java.util.Scanner;
import short07.BackEnd.Student;

/**
 * Short 07 - User Interface
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-01
 */
public final class MainUserInterface {
    
    /**
     * Scanner for user input
     * studentDb for student database handler
     * 
     * .. with some input validation under
     */
    private final Scanner sc;
    private final StudentDb studentDb;
    
    private final String nameValidation = "[A-Za-z ]{1,}";
    private final String classValidation = "FU([0-9]{1,2})";
    private final String markValidation = "([0-9]{1,32})(\\.[0-9]{1,32})?";
    private final String yesnoValidation = "y|Y|n|N";
    
    /**
     * Constructor of User Interface
     */
    public MainUserInterface() {
        // Initialize the user input and database
        sc = new Scanner(System.in);
        studentDb = new StudentDb();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String validation, String error, boolean isTrim) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
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
     * Enter a mark from user
     * @param subjectName String as the subject name so user know which object it is targetting
     * @return a valid mark in double
     */
    private double enterAMark(String subjectName) {
        // Set the default value (invalid for loop input)
        double mark = -1.0;
        
        // Try to get the mark. Set the negative double, then loop 
        // for user enter correct format of double
        while (mark == -1.0) {
            // assign for string
            String markStr = this.enterAValue(subjectName, markValidation, "Must be a number from 1 to 10", true);

            // try parse string, and validate that double later
            double markPr = Double.parseDouble(markStr);

            // Validate double, and pass if in range 0 - 10
            if (markPr > 10) {
                System.out.println("Mark must smaller than 10");
            } else if (markPr < 1) {
                System.out.println("Mark must larger than 1");
            } else {
                // If true, assign new double for argument
                mark = markPr;
            }
        }
        
        // return valid mark
        return (mark);
    }
    
    /**
     * Option - get student information
     */
    private void getStudentInformation() {
        
        // Get student list and type of Student in percentages (percentage of A, B, C, D)
        List<Student> students = studentDb.averageStudent();
        HashMap<String, Double> typeofStudents = studentDb.getPercentTypeStudent();
        
        // Count for printing
        int count = 0;
        
        // Loop for students and get information (with format specs)
        for (Student st : students) {
            System.out.println(
                 String.format(
                          "------Student%d Info------\n"
                        + "Name: %s\n"
                        + "Class: %s\n"
                        + "AVG: %.2f\n"
                        + "Type: %s\n",
                        (count + 1),
                        st.getStudentName(),
                        st.getStudentClass(),
                        st.getAverage(),
                        st.getType())
           );
            // each loop, count to one
            count += 1;
        }
        
        // Print the percentage information
        System.out.println(
                "-------- Classification Info ----\n" +
                String.format(
                          "A: %.2f %%\n"
                        + "B: %.2f %%\n"
                        + "C: %.2f %%\n"
                        + "D: %.2f %%",
                        typeofStudents.get("A"),
                        typeofStudents.get("B"),
                        typeofStudents.get("C"),
                        typeofStudents.get("D")
                )
        );
    }
    
    /**
     * Add new student into list
     */
    private void addNewStudent() {
        try {
            System.out.println("====== Management Student Program ======");
            
            // Try to get the student name. Set the empty string, then loop 
            // for user enter correct format of name
            String studentName = this.enterAValue("Name: ", nameValidation, "Name must only be letters.", true);
            while (studentDb.isDuplicatedNameFound(studentName)) {
                System.out.println("A student with a same name is detected.");
                studentName = this.enterAValue("Name: ", nameValidation, "Name must only be letters.", true);
            }
            
            // Try to get the student class. Set the empty string, then loop 
            // for user enter correct format of class
            String studentClass = this.enterAValue("Class: ", classValidation, "Invalid class type. Must be in format \"FU\" and 1 to 2 digits", true);
            
            // Try to get the mark.
            double chemistry = this.enterAMark("Chemistry: ");
            double math = this.enterAMark("Math: ");
            double physics = this.enterAMark("Physics: ");
            
            // Try adding student with arguments
            try {
                studentDb.createStudent(studentName, studentClass, chemistry, math, physics);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            // Asking for whether user want to enter other students
            String enterAnother = this.enterAValue("Do you want to enter more student information? (Y/N): ", yesnoValidation, "Either \"Y\" or \"N\"", true);
            
            // If user wants, call this function again to add another student
            if (enterAnother.toLowerCase().equals("y")) {
                this.addNewStudent();
            }  
        } catch (NumberFormatException e) {
            // Notify user for incorrect format
            System.out.println("Error: " + e.getLocalizedMessage());   
        }
    }
    
    /**
     * User Interface
     */
    public void UserInterface() {
        while (true) {
            try {
                // Display for option
                System.out.print(
                        "1. Add new Student\n"
                        + "2. Get student information\n"
                        + "Choose one: "
                );
                
                // Ask user for choice
                int choice = Integer.parseInt(sc.nextLine().trim());
                
                // For each choice, action in each call
                switch (choice) {
                    case 1:
                        this.addNewStudent();
                        break;
                    case 2:
                        this.getStudentInformation();
                        break;
                    default:
                        // Notify user for choice
                        System.out.println("Invalid input");
                        break;
                }
                
            } catch (NumberFormatException e) {
                // Notify user for incorrect format
                System.out.println("Invalid input");
            }
        }
    }    
}
