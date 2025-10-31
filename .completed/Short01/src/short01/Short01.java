package short01;

import java.util.Scanner;

import short01.StudentManagementPackage.StudentList;

/**
 * Short 01 - Main entry
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Short01 {
    
    /**
    * Global variables
    * sc - SCanner (General I/O for Program)
    * studentList - StudentList
    */
    
    private final Scanner sc;
    private final StudentList studentList;
    
    /**
     * Constructor of the program
     */
    public Short01() {
        sc = new Scanner(System.in);
        studentList = new StudentList();
    }
    
    /**
     * Get information from the file "Student.txt"
     */
    public void getInfo() {
        studentList.readFromFile();
    }
    
    /**
     * Displaying Main User Interface
     */
    public void displayMainMenu() {
        while (true) {
            System.out.print(
                " 1. Enter student list\n" +
                " 2. Look up student\n" +
                " 3. Display student list\n" +
                " 4. Exit\n" + 
                " 5. Delete all student\n" +
                "Please choose menu (1–5): "
            );

            try {
                // Try asking user s for selection. Default is 6 as faillback
                int selection = 6;
                String selecStr = sc.nextLine();

                // Validate that input
                if (selecStr == null || !selecStr.matches("[1-5]{1}")) {
                    System.out.println("Please validate your input\n");
                } else {
                    selection = Integer.parseInt(selecStr);
                }
                
                switch (selection) {
                    case 1:

                        /**
                         * Student information must follow this rule
                         * 
                         * Student Code: SV{3 to 6 Numbers}
                         * Student Name: Only letters and space
                         * Date of Birth: {2}-{(Months in 3 letters)}-{4}
                         * Learning Point: Numbers only
                         */
                        
                        System.out.print("Student code: ");
                        String stuCode = sc.nextLine();
                        
                        // If detect any informal, reject to call backend
                        if (stuCode.isEmpty() || !stuCode.matches("(SV)([0-9]{3,6})")) {
                            System.out.println("Please validate your input (SV with 3 to 6 numbers only)\n");
                            break;
                        }
                        
                        System.out.print("Student name: ");
                        String stuName1 = sc.nextLine();
                        
                        // If detect any informal, reject to call backend
                        if (stuName1 == null || !stuName1.matches("[A-Za-z ]{1,255}")) {
                            System.out.println("Please validate your input (Only letters and spaces)\n");
                            break;
                        }
                        
                        System.out.print("Date of birth: ");
                        String stuDoB = sc.nextLine();
                        
                        // If detect any informal, reject to call backend
                        if (stuDoB == null || !stuDoB.matches("(([0-3]{1})([0-9]{1}))-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})")) {
                            System.out.println("Please validate your input (in format DD-MMM-YYYY)\n");
                            break;
                        }

                        System.out.print("Learning point: ");
                        String stuLearnP = sc.nextLine();
                        
                        // If detect any informal, reject to call backend
                        if (stuLearnP == null || !stuLearnP.matches("([0-4]{1})\\.([0-9]{1})")) {
                            System.out.println("Please validate your input (D.D)\n");
                            break;
                        }
                        
                        studentList.addStudent(stuCode, stuName1, stuDoB, stuLearnP);
                        System.out.println("Added new student!");

                        break;
                    case 2:
                        System.out.print("Please enter student name: ");
                        String stuName2 = sc.nextLine();
                        
                        // Validate user input
                        if (stuName2 == null || !stuName2.matches("[A-Za-z ]{1,}")) {
                            System.out.println("Please validate your input (Only letters and spaces)\n");
                        } else {
                            // Call student List backend for student
                            studentList.lookForStudent(stuName2);
                        }

                        break;
                    case 3:
                        // Call student List backend to display all student information
                        studentList.displayStudents();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    case 5:
                        studentList.deleteAllStudent();
                        System.out.println("Deleted all students");
                        break;
                    default:
                        // Fall-back if the input not-get
                        break;
                }
            } catch (NumberFormatException e) {
                // Exception will handle when unable to parse int from str of user io
            }
        }
    }
    
    /**
     * Main entry of program
     * @param args Useless
     */
    public static void main(String[] args) {
        // initialize the interface
        Short01 sr = new Short01();
        
        // get the information from the file
        sr.getInfo();
        
        // display the user interface
        sr.displayMainMenu();
    }
}
