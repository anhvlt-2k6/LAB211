package short01.StudentManagementPackage;

/**
 * Short 01
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Student {
    /**
    * Global variables
    * studentCode - Student Code
    * studentName - Name of Student
    * dateOfBirth - Date of Birth
    * learningPoint - Learning Point
    */
    
    private final String studentCode;
    private final String studentName;
    private final String dateOfBirth;
    private final double learningPoint;
    
    /**
     * Constructor of Student class. All value must be provided
     * @param studentCode Student Code
     * @param studentName Name of Student
     * @param dateOfBirth Date of Birth
     * @param learningPoint Learning Point
     */
    public Student(String studentCode, String studentName, String dateOfBirth, double learningPoint) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.learningPoint = learningPoint;
    }

    /**
     * 
     * @return a String of student code
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     *
     * @return a String of student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     *
     * @return a String of Date of Birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @return a Double of Learning Point
     */
    public double getLearningPoint() {
        return learningPoint;
    }
}
