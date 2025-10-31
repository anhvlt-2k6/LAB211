package long02.DataType;

/**
 * Long 02 - Candidate abstract data
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public abstract class Candidate {
    
    private final int CandidateID;
    private final String firstName;
    private final String lastName;
    private final String DoB;
    private final String address;
    private final String phone;
    private final String email;
    private final int cadidateType;
    
    /**
     * Constructor of the Candidate
     * @param CandidateID as candidate id
     * @param firstName as first name
     * @param lastName as last name
     * @param DoB as date of birth
     * @param address as address
     * @param phone as phone
     * @param email as email
     * @param cadidateType as candidate type
     */
    public Candidate(int CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, int cadidateType) {
        this.CandidateID = CandidateID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DoB = DoB;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cadidateType = cadidateType;
    }

    /**
     * Get the candidate ID
     * @return int
     */
    public int getCandidateID() {
        return CandidateID;
    }

    /**
     * Get first name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get date of birth
     * @return String
     */
    public String getDoB() {
        return DoB;
    }

    /**
     * Get the address
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get phone number
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * get email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get candidate type
     * @return int
     */
    public int getCadidateType() {
        return cadidateType;
    }
}
