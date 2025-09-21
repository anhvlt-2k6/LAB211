package Backend;

public class Student {
    
    private final int id;
    private String studentName;
    private final int semester;
    private final String courseName;
    
    public Student(int id, String studentName, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getId() {
        return id;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
