package short07.BackEnd;

/**
 * Short 07 - Student class as blueprint
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-01
 */
public class Student {
    
    private final String studentName;
    private final String studentClass;
    private final double chemistry;
    private final double math;
    private final double physics;
    
    private final double average;
    private final String type;
    
    /**
     * Constructor of student name
     * @param studentName as String for student name
     * @param studentClass as String for student class
     * @param chemistry as double for mark of chemistry
     * @param math as double for mark of math
     * @param physics double for mark of physics
     */
    public Student(String studentName, String studentClass, double chemistry, double math, double physics) {
        // set properties by constructor reference
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
        
        // Calculate average
        this.average = (this.math + this.physics + this.chemistry) / 3.0;
        
        // Set the type by condition
        if (this.average > 7.5) {
            this.type = "A";
        } else if (this.average >= 6 && this.average <= 7.5)  {
            this.type = "B";
        } else if (this.average < 6 && this.average >= 4) {
            this.type = "C";
        } else {
            this.type = "D";
        }
    }

    /**
     * Get student Name 
     * @return String
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Get student class
     * @return String
     */
    public String getStudentClass() {
        return studentClass;
    }

    /**
     * Get math mark
     * @return double
     */
    public double getMath() {
        return math;
    }

    /**
     * Get Physics mark
     * @return double
     */
    public double getPhysics() {
        return physics;
    }

    /**
     * Get Chemistry mark
     * @return double
     */
    public double getChemistry() {
        return chemistry;
    }

    /**
     * Get average mark
     * @return double
     */
    public double getAverage() {
        return average;
    }

    /**
     * Get type of student. Possible are "A", "B", "C", "D"
     * @return String
     */
    public String getType() {
        return type;
    }
}
