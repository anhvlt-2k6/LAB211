package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Long 01 - Student database backend
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-21
 */
public final class StudentDb extends ArrayList<Student> {

    // Serial UID
    private static final long serialVersionUID = 1L;
    
    // Database name
    private final String databaseName = "studentDb.csv";
    
    // Database reader and writer
    private Scanner studentDbReader;
    private FileWriter studentDbWriter;
    
    /**
     * Constructor of the database.
     * It will read the database
     */
    public StudentDb() {
        this.readFromFile();
    }
    
    ///////////////////////////////////////////////////
    /////// Non-selection-based methods
    ///////////////////////////////////////////////////
    
    /**
     *
     */
    
    public void sortStudent() {
        Collections.sort(this, (o1, o2) -> (o1.getStudentName().compareTo(o2.getStudentName())));
    }
    
    /**
     * Read from the database
     */
    private void readFromFile() {
        try {
            // Initialize the database file object
            File fw = new File(databaseName);
            
            // If the file does not exist and/or cannot read, skip the operation
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            // Initialize the database file reader
            studentDbReader = new Scanner(fw);
            
            // Loop through the database
            while (studentDbReader.hasNext()) {
                String student = studentDbReader.nextLine();
                
                // If the line has a "#" at the start, ignore (Comment in Py3)
                if (!student.startsWith("#")) {
                    
                    /**
                     * Split into segments
                     * [0] - student id
                     * [1] - student nae
                     * [2] - semester
                     * [3] - course name
                     */
                    
                    String[] studentData = student.split(",");

                    // add this student into the "memory" of the database
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
            // Exception on file not found, out of bound, or unable to parse number
        }
    }
    
    /**
     * Write from local database to file
     */
    private void writeToFile() {
        // First line is the header (also the pseudo line)
        String outStr = "#Id,Name,Semester,CourseName";
        
        try {
            // Now initialize the studentDbWriter.
            studentDbWriter = new FileWriter(databaseName);
            
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
            
            // Write to file and close
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

    /**
     * Add student into database
     * @param id as the id of student
     * @param studentName as the student name
     * @param semester as semester of student
     * @param courseName as the course name
     * @return if the add is success (exception handle)
     */
    public boolean addStudent(String id, String studentName, String semester, String courseName) {
        // Return value, assume if the add is failed
        boolean isAddedSuccess = false;
        
        // Try-catch for integer parsing
        try {
            // Try adding to the "memory" database
            this.add(
                new Student(
                        Integer.parseInt(id),
                        studentName,
                        Integer.parseInt(semester),
                        courseName
                )
            );
            
            // Write to the database
            this.writeToFile();
            
            // Notify that the change is applied successfully
            isAddedSuccess = true;
        } catch (NumberFormatException e) {
            // Throw when integer parsing causes exceptional data
            System.out.println("Error while parsing student data");
        }
        
        // Return if the add is completed in success
        return (isAddedSuccess);
    }
    
    // Option 2

    /**
     * Find a student by its name
     * @param studentName as name or a part of a name
     * @return students that matches the list
     */
    public ArrayList<Student> findStudent(String studentName) {
        // Return list
        ArrayList<Student> foundStudents = new ArrayList<>();
        
        // Iterate for the current database, and find which one match the condition
        for (Student st : this) {
            if (st.getStudentName().contains(studentName)) {
                foundStudents.add(st);
            }
        }
        
        // Return the list of matched students
        return (foundStudents);
    }
    
    // Option 3

    /**
     * Update a student from database
     * @param studentId as student id
     * @param studentName as student name
     */
    public void updateStudentDb(String studentId, String studentName) {
        
        // Try-catch in case failed to parse the student Id
        try {
            // Parse from string into integer
            int stuId = Integer.parseInt(studentId);
            
            // Iterate for student. If there is any student matched with the params
            //  change its name
            for (Student st : this) {
                if (st.getId() == stuId) {
                    st.setStudentName(studentName);
                }
            }
            
            // Write back to file
            this.writeToFile();
        } catch (NumberFormatException ex) {
            // Exception handle for failed to parse
        }
    }
    
    /**
     * Delete a student from database
     * @param studentId as student Id
     */
    public void updateStudentDb(String studentId) {
        
        // Try-catch in case failed to parse the student Id
        try {
            // Parse from string into integer
            int stuId = Integer.parseInt(studentId.trim());
            
            // Iterate for student. If there is any student matched with the params
            //  delete it
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getId() == stuId) {
                    this.remove(i);
                }
            }
            
            this.writeToFile();
        } catch (NumberFormatException ex) {
            // Exception handle for failed to parse
        }
    }
    
    // Option 4

    /**
     * Generate a report
     * @return a HashMap, where the key as student unique info, Integer as the total course
     */
    public HashMap<String, Integer> reportData() {
        // Return value
        HashMap<String, Integer> reportResult = new HashMap<>();
        
        // Sort the array
        this.sortStudent();
        
        // Loop through the database, promise O(n)
        for (Student st : this) {
            // database as unique, defined by id, student name, and course name
            // if any of them changes, that is not what we count
            String dataName = st.getId() + "," + st.getStudentName() + "," + st.getCourseName();
            
            // put back the data to the resultReport
            // if it does not exist, generate inner value as 0 and +1 as count
            // if it does exist, +1 as count
            reportResult.put(dataName, reportResult.getOrDefault(dataName, 0) + 1);
        }
        
        // return the value
        return (reportResult);
    }
}
