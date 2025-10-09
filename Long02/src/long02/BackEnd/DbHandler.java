package long02.BackEnd;

import java.util.ArrayList;
import long02.DataType.Candidate;

public class DbHandler {
    
    private final ArrayList<Candidate> candidates;
    private final FileHandler fh;
    
    public DbHandler() {
        candidates = new ArrayList<>();
        fh = new FileHandler();
    }
    
    public void addCandidate(int candidateType, String[] commonParams, String[] exclusiveParams) {
        
        
        
    }
}
