package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public final class StudentDb extends ArrayList<Student> {

    private static final long serialVersionUID = 1L;
    
    private final String databaseName = "studentDb.csv";
    
    private Scanner studentDbReader;
    private FileWriter studentDbWriter;
    
    public StudentDb() {
        this.readFromFile();
    }
    
    ///////////////////////////////////////////////////
    /////// Non-selection-based methods
    ///////////////////////////////////////////////////
    
    public void sortStudent() {
        Collections.sort(this, (o1, o2) -> (o1.getStudentName().compareTo(o2.getStudentName())));
    }
    
    public void readFromFile() {
        try {
            File fw = new File(databaseName);
            
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            studentDbReader = new Scanner(fw);
            
            while (studentDbReader.hasNext()) {
                String student = studentDbReader.nextLine();
                
                // If the line has a "#" at the start, ignore (Comment in Py3)
                if (!student.startsWith("#")) {
                    
                    String[] studentData = student.split(",");

                    this.add(new Student(
                        Integer.parseInt(studentData[0]),
                        studentData[1],
                        Integer.parseInt(studentData[2]),
                        studentData[3]
                    ));
                }
            }
        }
        catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException file_ex) {
            // Exception on file not found
        }
    }
    
    public void writeToFile() {
        // First line is the header (also the pseudo line)
        String outStr = "#Id,Name,Semester,CourseName";
        
        try {
            // Now initialize the studentDbWriter.
            studentDbWriter = new FileWriter(databaseName);
            
            /**
             * Comparator (<list>, <comparator condition>)
             * 
             * The comparator takes if two element and get the property of int
             * You can multiply by -1 to reverse the order
             */
            
            Collections.sort(this, (o1, o2) -> (o1.getStudentName().compareTo(o2.getStudentName())));
            
            // Just a loop through the ArrayList
            for (Student st : this) {
                outStr += String.format(
                        "\n%d,%s,%d,%s",
                        st.getId(),
                        st.getStudentName(),
                        st.getSemester(),
                        st.getCourseName()
                );
            }
            
            studentDbWriter.write(outStr);
            studentDbWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            // Exception will handle when file not found
            // The program will ignore and create new file if there is new input anyways
        }
        catch (IOException io_e) {
            // Exception will handle when IO Error with file
        }
    }
    
    ///////////////////////////////////////////////////
    /////// Selection-based methods
    ///////////////////////////////////////////////////
    
    // Option 1
    public boolean addStudent(String id, String studentName, String semester, String courseName) {
        boolean isAddedSuccess = false;
        
        try {
            this.add(
                new Student(
                        Integer.parseInt(id),
                        studentName,
                        Integer.parseInt(semester),
                        courseName
                )
            );
            
            isAddedSuccess = true;
            
            this.writeToFile();
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing student data");
        }
        
        return (isAddedSuccess);
    }
    
    // Option 2
    public ArrayList<Student> findStudent(String studentName) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        
        for (Student st : this) {
            if (st.getStudentName().contains(studentName)) {
                foundStudents.add(st);
            }
        }
        
        return (foundStudents);
    }
    
    // Option 3
    
    // Update a student from database
    public void updateStudentDb(String studentId, String studentName) {
        try {
            int stuId = Integer.parseInt(studentId);
            
            for (Student st : this) {
                if (st.getId() == stuId) {
                    st.setStudentName(studentName);
                }
            }
            
            this.writeToFile();
        } catch (NumberFormatException ex) {
            
        }
    }
    
    // Delete a student from database
    public void updateStudentDb(String studentId) {
        try {
            int stuId = Integer.parseInt(studentId.trim());
            
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getId() == stuId) {
                    this.remove(i);
                }
            }
            
            this.writeToFile();
        } catch (NumberFormatException ex) {
            
        }
    }
    
    // Option 4
    public HashMap<String, Integer> reportData() {
        HashMap<String, Integer> reportResult = new HashMap<>();
        
        // Sort the array
        this.sortStudent();
        
        for (Student st : this) {
            String dataName = st.getId() + "," + st.getStudentName() + "," + st.getCourseName();
            reportResult.put(dataName, reportResult.getOrDefault(dataName, 0) + 1);
        }
        
        return (reportResult);
    }
}
