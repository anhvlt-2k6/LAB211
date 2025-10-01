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
public class MainUserInterface {
    
    /**
     * Scanner for user input
     * studentDb for student database handler
     * 
     * .. with some input validation under
     */
    private final Scanner sc;
    private final StudentDb studentDb;
    
    private final String nameValidation = "[A-Za-z0-9 ]{1,}";
    private final String classValidation = "FU([0-9]{1,2})";
    private final String markValidation = "([0-9]{1,32})(\\.[0-9]{1,32})?";
    
    /**
     *
     */
    public MainUserInterface() {
        
        // Initialize the user input and database
        sc = new Scanner(System.in);
        studentDb = new StudentDb();
        
        // add student into student database as sample
        studentDb.createStudent("Nghia", "FU1", 10, 10, 10);
        studentDb.createStudent("Nghia 2", "FU1", 10, 10, 10);
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
            String studentName = "";
            while (!studentName.matches(nameValidation)) {
                System.out.print("Name: ");
                String stuNameUI = sc.nextLine().trim(); // ask user for input
                
                // re-validate for user input
                if (stuNameUI.matches(nameValidation)) {
                    studentName = stuNameUI; // if matches, assign into above
                } else {
                    // Notify user for incorrect format
                    System.out.println("That is not a name. Please revalidate.");
                }
            }
            
            // Try to get the student class. Set the empty string, then loop 
            // for user enter correct format of class
            String studentClass = "";
            while (!studentClass.matches(classValidation)) {
                System.out.print("Class: ");
                String classUI = sc.nextLine().trim();  // ask user for input
                
                // re-validate for user input
                if (classUI.matches(classValidation)) {
                    studentClass = classUI; // if matches, assign into above
                } else {
                    // Notify user for incorrect format
                    System.out.println("Invalid class type. Must be in format \"FU\" and 1 to 2 digits");
                }
            }
            
            // Try to get the mark. Set the negative double, then loop 
            // for user enter correct format of double
            double chemistry = -1.0;
            while (chemistry == -1.0) {
                // assign for string
                String chemistryStr = "";
                while (!chemistryStr.matches(markValidation)) {
                    System.out.print("Chemistry: "); 
                    String chemistryStrInput = sc.nextLine().trim(); // ask user for input
                    
                    // Validate for mark String
                    if (chemistryStrInput.matches(markValidation)) {
                        chemistryStr = chemistryStrInput;
                    } else {
                        // Notify user for incorrect format
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                // try parse string, and validate that double later
                double chemistryPr = Double.parseDouble(chemistryStr);
                
                // Validate double, and pass if in range 0 - 10
                if (chemistryPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (chemistryPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    // If true, assign new double for argument
                    chemistry = chemistryPr;
                }
            }
            
            // Try to get the mark. Set the negative double, then loop 
            // for user enter correct format of double
            double math = -1.0;
            while (math == -1.0) {
                // assign for string
                String mathStr = "";
                while (!mathStr.matches(markValidation)) {
                    System.out.print("Math: ");
                    String mathStrInput = sc.nextLine().trim();
                    
                    // Validate for mark String
                    if (mathStrInput.matches(markValidation)) {
                        mathStr = mathStrInput;
                    } else {
                        // Notify user for incorrect format
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                // try parse string, and validate that double later
                double mathPr = Double.parseDouble(mathStr);
                
                // Validate double, and pass if in range 0 - 10
                if (mathPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (mathPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    // If true, assign new double for argument
                    math = mathPr;
                }
            }
            
            // Try to get the double. Set the empty double, then loop 
            // for user enter correct format of double
            double physics = -1.0;
            while (physics == -1.0) {
                // assign for string
                String physicsStr = "";
                
                while (!physicsStr.matches(markValidation)) {
                    System.out.print("Physics: ");
                    String physicsStrInput = sc.nextLine().trim();
                    
                    // Validate for mark String
                    if (physicsStrInput.matches(markValidation)) {
                        physicsStr = physicsStrInput;
                    } else {
                        // Notify user for incorrect format
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                // try parse string, and validate that double later
                double physicsPr = Double.parseDouble(physicsStr);
                
                // Validate double, and pass if in range 0 - 10
                if (physicsPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (physicsPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    // If true, assign new double for argument
                    physics = physicsPr;
                }
            }
            
            // Try adding student with arguments
            try {
                studentDb.createStudent(studentName, studentClass, chemistry, math, physics);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            // Asking for whether user want to enter other students
            String enterAnother = "";
            // if the string input is not Y or N, loop for correct answer
            while (!(enterAnother.equals("Y") || enterAnother.equals("N"))) {
                System.out.print("Do you want to enter more student information? (Y/N): ");
                String enterAnotherStr = sc.nextLine().trim();  // ask user for input
                
                // compare for correct answer
                if (enterAnotherStr.equals("Y") || enterAnotherStr.equals("N")) {
                    enterAnother = enterAnotherStr;
                } else {
                    System.out.println("Either \"Y\" or \"N\"");
                }
            }
            
            // If user wants, call this function again to add another student
            if (enterAnother.equals("Y")) {
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
