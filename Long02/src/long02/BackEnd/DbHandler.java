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

public class DbHandler {
    
    private final String[] dbs = new String[] {"experienceDb.csv", "fresherDb.csv", "internDb.csv"};
    
    private final ArrayList<Candidate> candidates;
    
    private Scanner dbReader;
    private FileWriter dbWriter;

    public DbHandler() {
        candidates = new ArrayList<>();
        try {
            readDb();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void readDb() throws Exception {
        int dataType = -1;
        
        for (String databaseName : dbs)  {
            try {
                if (databaseName.equals("experienceDb.csv")) {
                    dataType = 0;
                } else if (databaseName.equals("fresherDb.csv")) {
                    dataType = 1;
                } else if (databaseName.equals("internDb.csv")) {
                    dataType = 2;
                }
                
                // Initialize the database file object
                File fw = new File(databaseName);

                // If the file does not exist and/or cannot read, skip the operation
                if (!fw.exists() || !fw.canRead()) {
                    throw new FileNotFoundException(""); // Stop right here
                }

                // Initialize the database file reader
                dbReader = new Scanner(fw);
                
                
                
                // Loop through the database
                while (dbReader.hasNext()) {
                    String[] data = dbReader.nextLine().split(",");
                    switch (dataType) {
                        case 0:
                            if (data.length != 9) {
                                throw new Exception("Data is invalid");
                            }

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
                            if (data.length != 10) {
                                throw new Exception("Data is invalid");
                            }

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
                            if (data.length != 10) {
                                throw new Exception("Data is invalid");
                            }

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
                            throw new Exception("Invalid data type");
                    }
                }
            }
            catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException file_ex) {
                // Exception on file not found, out of bound, or unable to parse number
            }
        }
    }
    
    private void writeDb() {
        // In order - experienceStr, fresherStr, internStr
        String[] expStr = new String[] {"", "", ""};
        
        for (Candidate c : candidates) {
            
            int CandidateID = c.getCandidateID();
            String firstName = c.getFirstName();
            String lastName = c.getLastName();
            String DoB = c.getDoB();
            String address = c.getAddress();
            String phone = c.getPhone();
            String email = c.getEmail();
            
            if (c instanceof Experience || c.getCadidateType() == 0) {
            
                Experience e = (Experience) c;
                
                String expInYear = e.getExpInYear();
                String proSkill = e.getProSkill();
                
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
            } else if (c instanceof Fresher || c.getCadidateType() == 1) {
                
                Fresher f = (Fresher) c;
                
                String graduationDate = f.getGraduationDate();
                String rank = f.getRank();
                String schoolName = f.getSchoolName();
                
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
            } else if (c instanceof Intern || c.getCadidateType() == 2) {
                
                Intern i = (Intern) c;
                
                String major = i.getMajor();
                String semester = i.getSemester();
                String schoolName = i.getSchoolName();
                
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
        }
        
        for (int i = 0; i < 3; i++) {
            String dbFileName = dbs[i];
            String data = expStr[i];
            
            try {
                // Now initialize the studentDbWriter.
                dbWriter = new FileWriter(dbFileName);

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
        
        writeDb();
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }
    
    public void sort() {
        Collections.sort(
                candidates, 
                (c1, c2) -> (c1.getFirstName() + " " + c1.getLastName()).compareToIgnoreCase(c2.getFirstName() + " " + c2.getLastName())
        );
        
        writeDb();
    }
}
