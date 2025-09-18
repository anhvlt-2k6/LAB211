package short01;

import java.util.Scanner;

import short01.StudentManagementPackage.StudentList;

/**
 * Short 01
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Short01 {
    
    /**
    * Global variables
    * sc - SCanner (General I/O for Program)
    * studentList - StudentList
    */
    
    Scanner sc;
    
    StudentList studentList;
    
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
            int selection = Integer.parseInt(sc.next());
            switch (selection) {
                case 1:
                    System.out.print("Student code: ");
                    String stuCode = sc.next();
                    
                    System.out.print("Student name: ");
                    String stuName1 = sc.next();
                    
                    System.out.print("Date of birth: ");
                    String stuDoB = sc.next();
                    
                    System.out.print("Learning point: ");
                    double stuLearnP = Double.parseDouble(sc.next());
                    
                    if (stuCode.isEmpty() || stuName1.isEmpty() || stuDoB.isEmpty()) {
                        System.out.print("Please validate your input");
                    }
                    
                    studentList.addStudent(stuCode, stuName1, stuDoB, stuLearnP);
                    
                    break;
                case 2:
                    System.out.print("Please enter student name: ");
                    String stuName2 = sc.next();
                    
                    studentList.lookForStudent(stuName2);
                    
                    break;
                case 3:
                    studentList.displayStudents();
                    
                    break;
                case 4:
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
