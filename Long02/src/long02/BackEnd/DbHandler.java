package long02.BackEnd;

import java.util.ArrayList;
import long02.DataType.Candidate;
import long02.DataType.Experience;
import long02.DataType.Fresher;
import long02.DataType.Intern;

public class DbHandler {
    
    private final ArrayList<Candidate> candidates;
    private final FileHandler fh;
    
    public DbHandler() {
        candidates = new ArrayList<>();
        fh = new FileHandler();
    }
    
    public void addCandidate(int candidateType, String[] commonParams, String[] exclusiveParams) throws Exception {
        
        int CandidateID = candidates.size();
        
        if (commonParams.length != 6) {
            throw new Exception("commonParams is not enough data");
        }
        
        String firstName = commonParams[0];
        String lastName = commonParams[1];
        String dateofBirth = commonParams[2];
        String address = commonParams[3];
        String phone = commonParams[4];
        String email = commonParams[5];
        
        switch (candidateType) {
            case 0:
                if (exclusiveParams.length != 2) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                candidates.add(new Experience(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        commonParams[0],
                        commonParams[1]
                ));
                break;
            case 1:
                if (exclusiveParams.length != 3) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                candidates.add(new Fresher(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        commonParams[0],
                        commonParams[1],
                        commonParams[2]
                ));
                break;
            case 2:
                if (exclusiveParams.length != 3) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                candidates.add(new Intern(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        commonParams[0],
                        commonParams[1],
                        commonParams[2]
                ));
                break;
            default:
                throw new Exception("Invalid data type");
        }
    }
}
