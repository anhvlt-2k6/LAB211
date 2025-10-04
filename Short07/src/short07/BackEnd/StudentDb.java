package short07.BackEnd;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Short 07 - Student Database
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-01
 */
public final class StudentDb {
    
    // studentDb as the student database, that hold user data
    private final List<Student> studentDb;
    
    /**
     * Constructor of student database
     */
    public StudentDb() {
        // Initialize the List as ArrayList
        studentDb = new ArrayList<>();
    }
    
    /**
     * Check if the student is already exist
     * @param studentName String as student name
     * @return boolean if the student exist
     */
    public boolean isDuplicatedNameFound(String studentName) {
        // Default value if there is any duplicate in student name
        boolean isDuplicatedNameFound = false;
        
        // Loop for students in database
        for (Student st : studentDb) {
            // If found, set the isDuplicatedNameFound to true and exit the loop
            if (st.getStudentName().equals(studentName)) {
                isDuplicatedNameFound = true;
                break;
            }
        }
        
        // Return the value
        return (isDuplicatedNameFound);
    }
    
    /**
     * Add new student into the database
     * @param studentName as String for student name
     * @param studentClass as String for student class
     * @param chemistry as double for mark of chemistry
     * @param math as double for mark of math
     * @param physics double for mark of physics
     */
    public void createStudent(String studentName, String studentClass, double chemistry, double math, double physics) {
        // Default value if there is any duplicate in student name
        boolean isDuplicate = isDuplicatedNameFound(studentName);
        
        // Only add new Student if no duplicate in student name
        if (!isDuplicate) {
            studentDb.add(new Student(studentName, studentClass, chemistry, math, physics));
        } else {
            // Notify user for duplicate
            System.out.println("A student with a same name is detected.");
        }
    }

    /**
     * current database of student
     * @return List of student
     */
    public List<Student> averageStudent() {
        return (studentDb);
    }
    
    /**
     * Get percentage of student (%A, %B, %C, %D)
     * @return HashMap of percentage, where the key is "A", "B", "C", "D", and value is double of %
     */
    public HashMap<String, Double> getPercentTypeStudent() {
        // initialize the hashmap
        HashMap<String, Double> typeofStudent = new HashMap<>();
        
        // initialize the counter, double for easier to control data
        double CountA = 0.0, CountB = 0.0, CountC = 0.0, CountD = 0.0;
        
        // Loop for student 
        for (Student st : studentDb) {
            // get student type
            String stuType = st.getType();
        
            // for each student type, +1 for its counter
            switch (stuType) {
                case "A":
                    CountA += 1.0;
                    break;
                case "B":
                    CountB += 1.0;
                    break;
                case "C":
                    CountC += 1.0;
                    break;
                case "D":
                    CountD += 1.0;
                    break;
                default:
                    // fallback, no action
                    break;
            }
        }
        // Calculate for total
        double total = CountA + CountB + CountC + CountD;
        
        // put with key and value of percentage
        typeofStudent.put("A", (CountA / total * 100)); 
        typeofStudent.put("B", (CountB / total * 100));
        typeofStudent.put("C", (CountC / total * 100));
        typeofStudent.put("D", (CountD / total * 100));
        
        // return value
        return (typeofStudent);
    }
}
