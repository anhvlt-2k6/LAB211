package FrontEnd;

import Backend.Student;
import Backend.StudentDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    
    private final String studentIdValidation = "[0-9]{1,9}";
    private final String nameValidation = "[a-zA-z0-9 \\.]{1,254}";
    private final String semesterValidation = "[0-9]{1,2}";
    private final String courseValidation = "(Java|.Net|C/C\\+\\+)";
    
    private final int minUserInput = 10;
    
    private final Scanner sc;
    
    private final StudentDb studentDb;
    
    public UserInterface() {
        studentDb = new StudentDb();
        sc = new Scanner(System.in);
    }
    
    private void selectionOne() {
        
        int numberofStudents = -1;
        
        while (numberofStudents < minUserInput) {
            System.out.print("Please enter number of student you want to add: ");
            
            try {
                numberofStudents = Integer.parseInt(sc.nextLine());
                
                if (numberofStudents < minUserInput) {
                    System.out.println("Invalid input, please try again.");
                }
                
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        
        for (int i = 0; i < numberofStudents; i++) {
            
            boolean isAddingSuccess = false;
            
            while (!isAddingSuccess)  {
                System.out.println("Information for student: " + (i + 1));
            
                System.out.print("Enter student Id: ");
                String studentId = sc.nextLine();
                while (!studentId.matches(studentIdValidation)) {
                    System.out.print("Invalid student Id.\n Re-enter student Id: ");
                    studentId = sc.nextLine();
                }

                System.out.print("Enter student name: ");
                String studentName = sc.nextLine();
                while (!studentName.matches(nameValidation)) {
                    System.out.print("Invalid student name.\nEnter student name: ");
                    studentName = sc.nextLine();
                }

                System.out.print("Enter semester: ");
                String semester = sc.nextLine();
                while (!semester.matches(semesterValidation)) {
                    System.out.print("Invalid semester.\nEnter semester: ");
                    semester = sc.nextLine();
                }

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
                    isAddingSuccess = true; // break the inner while loop
                }
            }
        }
        
        String orderSelection = "";
        while (!orderSelection.toLowerCase().equals("y") && !orderSelection.toLowerCase().equals("n")) {
            System.out.print("Do you want to order now (Y/N): ");
            orderSelection = sc.nextLine();
        }
        
        if (orderSelection.toLowerCase().equals("y")) {
            studentDb.sortStudent();
            System.out.println("Order is completed.");
        }
    }
    
    private void selectionTwo() {
        studentDb.sortStudent();
        
        System.out.print("Enter student name: ");
        String studentName = sc.nextLine();
        while (!studentName.matches(nameValidation)) {
            System.out.print("Invalid student name.\nEnter student name: ");
            studentName = sc.nextLine();
        }
        
        ArrayList<Student> foundStudents = studentDb.findStudent(studentName);
        if (foundStudents.isEmpty()) {
            System.out.println("No student found.");
        } else {
            System.out.println(
                    "+--------------+----------+-------------+\n" +
                    "| Student name | Semester | Course Name |\n" +
                    "+--------------+----------+-------------+"
            );
            
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
        System.out.print("Enter student Id: ");
        String studentId = sc.nextLine();
        while (!studentId.matches(studentIdValidation)) {
            System.out.print("Invalid student Id.\n Re-enter student Id: ");
            studentId = sc.nextLine();
        }

        String dataSelection = "";
        while (!dataSelection.toLowerCase().equals("u") && !dataSelection.toLowerCase().equals("d")) {
            System.out.print("Do you want to update (U) or delete (D) student: ");
            dataSelection = sc.nextLine();
        }
        
        if (dataSelection.toLowerCase().equals("u")) {
            // Update user
            
            System.out.print("Enter student name: ");
            String studentName = sc.nextLine();
            while (!studentName.matches(nameValidation)) {
                System.out.print("Invalid student name.\nEnter student name: ");
                studentName = sc.nextLine();
            }
            
            studentDb.updateStudentDb(studentId, studentName);
        } else {
            // Delete user
            studentDb.updateStudentDb(studentId);
        }
    }
    
    private void selectionFour() {
        HashMap<String, Integer> reportResult = studentDb.reportData();
        
        int iterationCount = 1;
        System.out.println(
            "+-----+--------------+--------+-----------------+\n" +
            "| No. | Student name | Course | Total of course |\n" +
            "+-----+--------------+--------+-----------------+"
        );
        
        for (HashMap.Entry<String, Integer> i : reportResult.entrySet()) {
            String[] studentKey = i.getKey().split(",");
            int totalCourse = i.getValue();
            
            System.out.println(String.format(
                "| %3d | %12.12s | %6.6s | %15d |", 
                iterationCount,
                studentKey[1],
                studentKey[2],
                totalCourse)
            );
            
            iterationCount += 1;
        }
        
        System.out.println(
            "+-----+--------------+--------+-----------------+"
        );
    }
    
    public void displayUI() {
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
                        System.out.println("Invalid input.");
                        break;      
                }
                
            } catch (NumberFormatException num_ex) {
                System.out.println("Invalid input.");
            }
        }
    }
}
