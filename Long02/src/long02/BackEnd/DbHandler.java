package long02.BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import long02.DataType.Candidate;
import long02.DataType.Experience;
import long02.DataType.Fresher;
import long02.DataType.Intern;

/**
 * Long 02 - Database File Handler
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public class DbHandler {
    
    // db files, in the order (0 - experienced, 1 - fresher, 2 - intern)
    private final String[] dbs = new String[] {"experienceDb.csv", "fresherDb.csv", "internDb.csv"};
    
    // An array contains all candidates
    private final ArrayList<Candidate> candidates;
    
    /**
     * Constructor of the database handler
     */
    public DbHandler() {
        // initialize new array list
        candidates = new ArrayList<>();
        
        // try to import database
        try {
            readDb();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Try read database
     * @throws Exception on any data of the file 
     */
    private void readDb() throws Exception {
        // Intialize the data type as invalid value
        // It is also the cadidateType
        int dataType = -1;
        
        // For each database, try read and add data into the database
        for (String databaseName : dbs)  {  
            try (Scanner dbReader = new Scanner(new File(databaseName))) {
                
                // for each case of the database, set the data type to its value (see above)
                switch (databaseName) {
                    case "experienceDb.csv":
                        dataType = 0;
                        break;
                    case "fresherDb.csv":
                        dataType = 1;
                        break;
                    case "internDb.csv":
                        dataType = 2;
                        break;
                    default:
                        break;
                }
                
                // Loop through the database
                while (dbReader.hasNext()) {
                    // Spilit data of the line into array of data
                    String[] data = dbReader.nextLine().split(",");
                    // for each case of data type, try add
                    switch (dataType) {
                        case 0:
                            // Experience candidates requries 9 params.
                            // If not meet, throw exception for database handle wrong type
                            if (data.length != 9) {
                                throw new Exception("Data is invalid");
                            }
                            
                            // Add new candidate with params (not built-in db function, its slow)
                            candidates.add(new Experience(
                                    Integer.parseInt(data[0]), 
                                    data[1], 
                                    data[2], 
                                    data[3], 
                                    data[4],
                                    data[5],
                                    data[6],
                                    data[7],
                                    data[8]
                            ));
                            break;
                        case 1:
                            // Fresher candidates requries 10 params.
                            // If not meet, throw exception for database handle wrong type
                            if (data.length != 10) {
                                throw new Exception("Data is invalid");
                            }
                            
                            // Add new candidate with params (not built-in db function, its slow)
                            candidates.add(new Fresher(
                                    Integer.parseInt(data[0]), 
                                    data[1], 
                                    data[2], 
                                    data[3], 
                                    data[4],
                                    data[5],
                                    data[6],
                                    data[7],
                                    data[8],
                                    data[9]
                            ));
                            break;
                        case 2:
                            // Intern candidates requries 10 params.
                            // If not meet, throw exception for database handle wrong type
                            if (data.length != 10) {
                                throw new Exception("Data is invalid");
                            }

                            // Add new candidate with params (not built-in db function, its slow)
                            candidates.add(new Intern(
                                    Integer.parseInt(data[0]), 
                                    data[1], 
                                    data[2], 
                                    data[3], 
                                    data[4],
                                    data[5],
                                    data[6],
                                    data[7],
                                    data[8],
                                    data[9]
                            ));
                            break;
                        default:
                            // In case of any invalid data type, try throw exception and stop handling
                            throw new Exception("Invalid data type");
                    }
                }
                
                // reset the datatype to default invalid value
                dataType = -1;
            }
            catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException file_ex) {
                // Exception on file not found, out of bound, or unable to parse number
            }
        }
    }
    
    private void writeDb() {
        // In order string to be written - experienceStr, fresherStr, internStr
        String[] expStr = new String[] {"", "", ""};
        
        // For each 'object' of the database array list, loop for get the value
        candidates.forEach((c) -> {
            // Default value of the objects (inheritance from Candidate)
            int CandidateID = c.getCandidateID();
            String firstName = c.getFirstName();
            String lastName = c.getLastName();
            String DoB = c.getDoB();
            String address = c.getAddress();
            String phone = c.getPhone();
            String email = c.getEmail();
            
            // Special types, must go in case
            
            // First case: Experience users
            if (c instanceof Experience || c.getCadidateType() == 0) {
                
                // casting to c to experience type
                Experience e = (Experience) c;
                
                // Get the sepcial values of the Experience candidate
                String expInYear = e.getExpInYear();
                String proSkill = e.getProSkill();
                
                // Format in-string and add it into string
                expStr[0] += String.format(
                        "%d,%s,%s,%s,%s,%s,%s,%s,%s\n", 
                        CandidateID,
                        firstName,
                        lastName,
                        DoB,
                        address,
                        phone,
                        email,
                        expInYear,
                        proSkill
                );
            } 
            // Second case: Fresher users
            else if (c instanceof Fresher || c.getCadidateType() == 1) {
                
                // casting to c to fresher type
                Fresher f = (Fresher) c;
                
                // Get the sepcial values of the Fresher candidate
                String graduationDate = f.getGraduationDate();
                String rank = f.getRank();
                String schoolName = f.getSchoolName();
                
                // Format in-string and add it into string
                expStr[1] += String.format(
                        "%d,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                        CandidateID,
                        firstName,
                        lastName,
                        DoB,
                        address,
                        phone,
                        email,
                        graduationDate,
                        rank,
                        schoolName
                );
            }
            // Third case: Intern users
            else if (c instanceof Intern || c.getCadidateType() == 2) {
                
                // casting to c to intern type
                Intern i = (Intern) c;
                
                // Get the sepcial values of the intern candidate
                String major = i.getMajor();
                String semester = i.getSemester();
                String schoolName = i.getSchoolName();
                
                // Format in-string and add it into string
                expStr[2] += String.format(
                        "%d,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                        CandidateID,
                        firstName,
                        lastName,
                        DoB,
                        address,
                        phone,
                        email,
                        major,
                        semester,
                        schoolName
                );
            }
        });
        
        // For each case of data type, write data into file
        for (int i = 0; i < 3; i++) {
            String dbFileName = dbs[i];
            String data = expStr[i];
            
            // Now initialize the db writer.
            try (FileWriter dbWriter = new FileWriter(dbFileName)) {    
                // Write to file and close
                dbWriter.write(data);
                dbWriter.close();
            }
            catch (FileNotFoundException ex_fnf) {
                // Exception will handle when file not found
                // The program will ignore and create new file if there is new input anyways
            }
            catch (IOException io_e) {
                // Exception will handle when IO Error with file
            }
        }
    }
    
    /**
     * Add candidate into the database
     * @param candidateType as candidate type
     * @param commonParams String[] common params
     * @param exclusiveParams String[] exclusive params (params only in object)
     * @throws Exception in case of not enough param
     */
    public void addCandidate(int candidateType, String[] commonParams, String[] exclusiveParams) throws Exception {
        
        // Get the candidate ID (as candidate size)
        int CandidateID = candidates.size();
        
        // If not enough param, throw exception
        if (commonParams.length != 6) {
            throw new Exception("commonParams is not enough data");
        }
        
        // Set the param
        String firstName = commonParams[0];
        String lastName = commonParams[1];
        String dateofBirth = commonParams[2];
        String address = commonParams[3];
        String phone = commonParams[4];
        String email = commonParams[5];
        
        // For each case of candidate, try adding into the database
        switch (candidateType) {
            case 0:
                // If not enough param, throw exception
                if (exclusiveParams.length != 2) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                // add into the database
                candidates.add(new Experience(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        exclusiveParams[0],
                        exclusiveParams[1]
                ));
                break;
            case 1:
                // If not enough param, throw exception
                if (exclusiveParams.length != 3) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                // add into the database
                candidates.add(new Fresher(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        exclusiveParams[0],
                        exclusiveParams[1],
                        exclusiveParams[2]
                ));
                break;
            case 2:
                // If not enough param, throw exception
                if (exclusiveParams.length != 3) {
                    throw new Exception("exclusiveParams is not enough data");
                }
                
                // add into the database
                candidates.add(new Intern(
                        CandidateID, 
                        firstName, 
                        lastName, 
                        dateofBirth, 
                        address,
                        phone,
                        email,
                        exclusiveParams[0],
                        exclusiveParams[1],
                        exclusiveParams[2]
                ));
                break;
            default:
                // throw for wrong data type
                throw new Exception("Invalid data type");
        }
        
        // Write back to the database
        writeDb();
    }

    /**
     * Get the candidate whole db
     * @return array list
     */
    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }
    
    /**
     * Sort for name
     */
    public void sort() {
        // Call the sorting func, with the the name as the target
        Collections.sort(
                candidates, 
                (c1, c2) -> (c1.getFirstName() + " " + c1.getLastName()).compareToIgnoreCase(c2.getFirstName() + " " + c2.getLastName())
        );
        
        // Write back to the database
        writeDb();
    }
}
