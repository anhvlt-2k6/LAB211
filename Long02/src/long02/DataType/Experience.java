package long02.DataType;

public final class Experience extends Candidate {
    
    protected final int expInYear;
    protected final String proSkill;
    
    public Experience(String CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, int expInYear, String proSkill) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public String getProSkill() {
        return proSkill;
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
