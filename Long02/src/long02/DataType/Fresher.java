package long02.DataType;

/**
 * Long 02 - Fresher candidate data
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public final class Fresher extends Candidate {
    
    private final String graduationDate;
    private final String rank;
    private final String schoolName;
    
    /**
     * Constructor of the Fresher
     * @param CandidateID as candidate id
     * @param firstName as first name
     * @param lastName as last name
     * @param DoB as date of birth
     * @param address as address
     * @param phone as phone
     * @param email as email
     * @param graduationDate as Grad date
     * @param rank as school rank
     * @param schoolName as school name
     */
    public Fresher(int CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String graduationDate, String rank, String schoolName) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 1);
        this.graduationDate = graduationDate;
        this.rank = rank;
        this.schoolName = schoolName;
    }

    /**
     * Get Grad date
     * @return String
     */
    public String getGraduationDate() {
        return graduationDate;
    }

    /**
     * Get rank
     * @return String
     */
    public String getRank() {
        return rank;
    }
    
    /**
     * Get school name
     * @return String
     */
    public String getSchoolName() {
        return schoolName;
    }
}
