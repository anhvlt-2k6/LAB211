package long02.DataType;

public final class Fresher extends Candidate {
    
    protected final String graduationDate;
    protected final String rank;
    protected final String schoolName;
    
    public Fresher(String CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String graduationDate, String rank, String schoolName) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 1);
        this.graduationDate = graduationDate;
        this.rank = rank;
        this.schoolName = schoolName;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public String getRank() {
        return rank;
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
