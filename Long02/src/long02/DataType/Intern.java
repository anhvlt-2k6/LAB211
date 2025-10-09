package long02.DataType;

public final class Intern extends Candidate {
    
    protected final String major;
    protected final String semester;
    protected final String schoolName;
    
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

    public String getCandidateID() {
        return CandidateID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDoB() {
        return DoB;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getCadidateType() {
        return cadidateType;
    }
}
