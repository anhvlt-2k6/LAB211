package long02.DataType;

public final class Fresher extends Candidate {
    
    private final String graduationDate;
    private final String rank;
    private final String schoolName;
    
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
}
