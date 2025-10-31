package long02.DataType;

/**
 * Long 02 - Experienced candidate data
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public final class Experience extends Candidate {
    
    private final String expInYear;
    private final String proSkill;
    
    /**
     * Constructor of the Candidate
     * @param CandidateID as candidate id
     * @param firstName as first name
     * @param lastName as last name
     * @param DoB as date of birth
     * @param address as address
     * @param phone as phone
     * @param email as email
     * @param expInYear as year of experience
     * @param proSkill as professional skill
     */
    public Experience(int CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String expInYear, String proSkill) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    /**
     * Get experience year
     * @return String
     */
    public String getExpInYear() {
        return expInYear;
    }
    
    /**
     * Get Professional Skill 
     * @return String
     */
    public String getProSkill() {
        return proSkill;
    }
}
