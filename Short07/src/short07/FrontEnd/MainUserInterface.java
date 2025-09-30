package short07.FrontEnd;

import java.util.HashMap;
import java.util.List;
import short07.BackEnd.StudentDb;
import java.util.Scanner;
import short07.BackEnd.Student;


public class MainUserInterface {
    
    private final Scanner sc;
    private final StudentDb studentDb;
    
    private final String nameValidation = "[A-Za-z0-9 ]{1,}";
    private final String classValidation = "FU([0-9]{1,2})";
    private final String markValidation = "([0-9]{1,32})(\\.[0-9]{1,32})?";
    
    public MainUserInterface() {
        sc = new Scanner(System.in);
        studentDb = new StudentDb();
        
        studentDb.createStudent("Nghia", "FU1", 10, 10, 10);
        studentDb.createStudent("Nghia 2", "FU1", 10, 10, 10);
    }
    
    private void getStudentInformation() {
        
        List<Student> students = studentDb.averageStudent();
        HashMap<String, Double> typeofStudents = studentDb.getPercentTypeStudent();
        
        if (students == null) {
            
        }
        
        if (typeofStudents == null) {
            
        }
        
        int count = 0;
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
            
            count += 1;
        }
        
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
    
    private void addNewStudent() {
        try {
            System.out.println("====== Management Student Program ======");
            
            String studentName = "";
            while (!studentName.matches(nameValidation)) {
                System.out.print("Name: ");
                String stuNameUI = sc.nextLine();
                
                if (stuNameUI.matches(nameValidation)) {
                    studentName = stuNameUI;
                } else {
                    System.out.println("That is not a name. Please revalidate.");
                }
            }
            
            String studentClass = "";
            while (!studentClass.matches(classValidation)) {
                System.out.print("Class: ");
                String classUI = sc.nextLine();
                
                if (classUI.matches(classValidation)) {
                    studentClass = classUI;
                } else {
                    System.out.println("Invalid class type. Must be in format \"FU\" and 1 to 2 digits");
                }
            }
            
            double chemistry = -1.0;
            while (chemistry == -1.0) {
                String chemistryStr = "";
                while (!chemistryStr.matches(markValidation)) {
                    System.out.print("Chemistry: ");
                    String chemistryStrInput = sc.nextLine();
                    
                    if (chemistryStrInput.matches(markValidation)) {
                        chemistryStr = chemistryStrInput;
                    } else {
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                double chemistryPr = Double.parseDouble(chemistryStr);
                
                if (chemistryPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (chemistryPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    chemistry = chemistryPr;
                }
            }
            
            double math = -1.0;
            while (math == -1.0) {
                String mathStr = "";
                while (!mathStr.matches(markValidation)) {
                    System.out.print("Math: ");
                    String mathStrInput = sc.nextLine();
                    
                    if (mathStrInput.matches(markValidation)) {
                        mathStr = mathStrInput;
                    } else {
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                double mathPr = Double.parseDouble(mathStr);
                
                if (mathPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (mathPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    math = mathPr;
                }
            }
            
            double physics = -1.0;
            while (physics == -1.0) {
                String physicsStr = "";
                while (!physicsStr.matches(markValidation)) {
                    System.out.print("Physics: ");
                    String physicsStrInput = sc.nextLine();
                    
                    if (physicsStrInput.matches(markValidation)) {
                        physicsStr = physicsStrInput;
                    } else {
                        System.out.println("Must be a number from 0 to 10");
                    }
                }
                
                double physicsPr = Double.parseDouble(physicsStr);
                
                if (physicsPr > 10) {
                    System.out.println("Math mark must smaller than 10");
                } else if (physicsPr < 0) {
                    System.out.println("Math mark must larger than 0");
                } else {
                    physics = physicsPr;
                }
            }
            
            try {
                studentDb.createStudent(studentName, studentClass, chemistry, math, physics);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            String enterAnother = "";
            while (!(enterAnother.equals("Y") || enterAnother.equals("N"))) {
                System.out.print("Do you want to enter more student information? (Y/N): ");
                String enterAnotherStr = sc.nextLine();
                
                if (enterAnotherStr.equals("Y") || enterAnotherStr.equals("N")) {
                    enterAnother = enterAnotherStr;
                } else {
                    System.out.println("Either \"Y\" or \"N\"");
                }
            }
            
            if (enterAnother.equals("Y")) {
                this.addNewStudent();
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getLocalizedMessage());   
        }
    }
    
    public void UserInterface() {
        while (true) {
            try {
                
                System.out.print(
                        "1. Add new Student\n"
                        + "2. Get student information\n"
                        + "Choose one: "
                );
                
                int choice = Integer.parseInt(sc.nextLine());
                
                switch (choice) {
                    case 1:
                        this.addNewStudent();
                        break;
                    case 2:
                        this.getStudentInformation();
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }    
}
