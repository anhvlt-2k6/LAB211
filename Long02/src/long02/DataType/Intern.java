package long02.DataType;

/**
 * Long 02 - Intern candidate data
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public final class Intern extends Candidate {
    
    private final String major;
    private final String semester;
    private final String schoolName;
    
    /**
     * Constructor of the Intern
     * @param CandidateID as candidate id
     * @param firstName as first name
     * @param lastName as last name
     * @param DoB as date of birth
     * @param address as address
     * @param phone as phone
     * @param email as email
     * @param major as major
     * @param semester as semester
     * @param schoolName as school name
     */
    public Intern(int CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String major, String semester, String schoolName) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 2);
        this.major = major;
        this.semester = semester;
        this.schoolName = schoolName;
    }

    /**
     * Get major
     * @return String
     */
    public String getMajor() {
        return major;
    }

    /**
     * Get semester
     * @return String
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Get school name
     * @return String
     */
    public String getSchoolName() {
        return schoolName;
    }
}
