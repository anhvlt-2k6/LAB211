package short07.BackEnd;

public class Student {
    
    private final String studentName;
    private final String studentClass;
    private final double chemistry;
    private final double math;
    private final double physics;
    
    private final double average;
    private final String type;
    
    public Student(String studentName, String studentClass, double chemistry, double math, double physics) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
        
        this.average = (this.math + this.physics + this.chemistry) / 3.0;
        
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

    public String getStudentName() {
        return studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public double getMath() {
        return math;
    }

    public double getPhysics() {
        return physics;
    }

    public double getChemistry() {
        return chemistry;
    }

    public double getAverage() {
        return average;
    }

    public String getType() {
        return type;
    }
}
