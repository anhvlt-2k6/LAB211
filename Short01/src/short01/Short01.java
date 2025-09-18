package short01;

import java.util.Scanner;

import short01.StudentManagementPackage.StudentList;

/**
 * Short 01 - Main entry
 * @author CE200360 - Vo Luu Tuong Anh
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
        System.out.print(
            " 1. Enter student list\n" +
            " 2. Look up student\n" +
            " 3. Display student list\n" +
            " 4. Exit\n" + 
            "Please choose menu (1–4): "
        );
        
        try {
            
            // Try asking user s for selection
            int selection = Integer.parseInt(sc.next());
            
            sc.nextLine(); // Consume for trash input
            
            switch (selection) {
                case 1:
                    
                    /**
                     * Student information must follow this rule
                     * 
                     * Student Code: SV-{3 to 6 Numbers}
                     * Student Name: Only letters and space
                     * Date of Birth: {2}-{(Months in 3 letters)}-{4}
                     * Learning Point: Numbers only
                     */
                    
                    System.out.print("Student code: ");
                    String stuCode = sc.nextLine();
                    
                    System.out.print("Student name: ");
                    String stuName1 = sc.nextLine();
                    
                    System.out.print("Date of birth: ");
                    String stuDoB = sc.nextLine();
                    
                    System.out.print("Learning point: ");
                    String stuLearnP = sc.nextLine();
                    
                    // Check for informal format
                    boolean isInformal = 
                            stuCode.isEmpty() || !stuCode.matches("(SV)-([0-9]{3,6})") ||
                            stuName1 == null || !stuName1.matches("[A-Za-z ]{1,255}") ||
                            stuDoB == null || !stuDoB.matches("(([0-3]{1})([0-9]{1}))-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})") ||
                            stuLearnP == null || !stuLearnP.matches("([0-4]{1}).([0-9]{1})"); 
                    
                    // If detect any informal, reject to call backend
                    if (isInformal) {
                        System.out.println("Please validate your input");
                    } else {
                        studentList.addStudent(stuCode, stuName1, stuDoB, stuLearnP);
                    }
                    
                    break;
                case 2:
                    System.out.print("Please enter student name: ");
                    String stuName2 = sc.next();
                    
                    if (stuName2 == null || !stuName2.matches("[A-Za-z ]{1,}")) {
                        System.out.println("Please validate your input");
                    } else {
                        // 
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
                default:
                    System.out.println("Invalid input");
                    
                    break;
            }
        } catch (NumberFormatException e) {
            // Exception will handle when unable to parse int from str of user io
        }
        
        System.exit(0);
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
