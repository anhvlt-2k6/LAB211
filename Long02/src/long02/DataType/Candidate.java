package long02.DataType;

public abstract class Candidate {
    
    protected final String CandidateID;
    protected final String firstName;
    protected final String lastName;
    protected final String DoB;
    protected final String address;
    protected final String phone;
    protected final String email;
    protected final int cadidateType;
    
    public Candidate(String CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, int cadidateType) {
        this.CandidateID = CandidateID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DoB = DoB;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cadidateType = cadidateType;
    }
}
