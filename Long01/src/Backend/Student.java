package Backend;

/**
 * Long 01 - Student class
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-21
 */
public class Student {
    
    private final int id;
    private String studentName;
    private final int semester;
    private final String courseName;
    
    /**
     * Constructor
     * @param id as student id
     * @param studentName as student name
     * @param semester as semester
     * @param courseName as course name student enroll
     */
    public Student(int id, String studentName, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Get student name
     * @return String in student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Get student id
     * @return int as id
     */
    public int getId() {
        return id;
    }

    /**
     * Get Semester
     * @return int as semester
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Get course Name
     * @return String as course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set student name
     * @param studentName as student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
