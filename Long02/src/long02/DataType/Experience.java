package long02.DataType;

public final class Experience extends Candidate {
    
    private final String expInYear;
    private final String proSkill;
    
    public Experience(String CandidateID, String firstName, String lastName, String DoB, String address, String phone, String email, String expInYear, String proSkill) {
        super(CandidateID, firstName, lastName, DoB, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public String getExpInYear() {
        return expInYear;
    }
    
    public String getProSkill() {
        return proSkill;
    }
}
