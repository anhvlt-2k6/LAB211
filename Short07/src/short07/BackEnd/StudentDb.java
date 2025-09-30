package short07.BackEnd;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public final class StudentDb {
    
    private List<Student> studentDb;
    
    public StudentDb() {
        studentDb = new ArrayList<>();
    }
    
    public void createStudent(String studentName, String studentClass, double chemistry, double math, double physics) {
        boolean isDuplicate = false;
        
        for (Student st : studentDb) {
            if (st.getStudentName().equals(studentName)) {
                isDuplicate = true;
                break;
            }
        }
        
        if (!isDuplicate) {
            studentDb.add(new Student(studentName, studentClass, chemistry, math, physics));
        } else {
            System.out.println("A student with a same name is detected.");
        }
    }

    public List<Student> averageStudent() {
        return (studentDb);
    }
    
    public HashMap<String, Double> getPercentTypeStudent() {
        HashMap<String, Double> typeofStudent = new HashMap<>();
        
        double CountA = 0.0, CountB = 0.0, CountC = 0.0, CountD = 0.0;
        
        for (Student st : studentDb) {
            String stuType = st.getType();
        
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
                default:
                    CountD += 1.0;
                    break;
            }
        }
        
        double total = CountA + CountB + CountC + CountD;
        
        typeofStudent.put("A", (CountA / total * 100));
        typeofStudent.put("B", (CountB / total * 100));
        typeofStudent.put("C", (CountC / total * 100));
        typeofStudent.put("D", (CountD / total * 100));
        
        return (typeofStudent);
    }
}
