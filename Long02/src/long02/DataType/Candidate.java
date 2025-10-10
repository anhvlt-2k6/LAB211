package long02.DataType;

public abstract class Candidate {
    
    private final int CandidateID;
    private final String firstName;
    private final String lastName;
    private final String DoB;
    private final String address;
    private final String phone;
    private final String email;
    private final int cadidateType;
    
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

    public int getCandidateID() {
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
