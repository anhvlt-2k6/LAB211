package long02.DataType;

public final class Intern extends Candidate {
    
    private final String major;
    private final String semester;
    private final String schoolName;
    
    public Intern(String CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String major, String semester, String schoolName) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 2);
        this.major = major;
        this.semester = semester;
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public String getSemester() {
        return semester;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
