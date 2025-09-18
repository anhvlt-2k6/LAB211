package short01.StudentManagementPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Short 01 - Student Db Handler
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-14 (1:00 AM)
 */
public class StudentList extends ArrayList<Student> {
    
    /**
     * Note:
     * Global variables
        * fileName - the basic "database" for storing list of students
        * studentDbReader - File Reader, see in readFromFile()
        * studentDbWriter - File Writer, see in writeToFile()
     */
    
    private final String fileName = "Student.txt";
    
    private Scanner studentDbReader;
    private FileWriter studentDbWriter;
    
    ///////////////////////////////////////////////////////////////////////////
    ///// Non-option methods
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * This method reads data from file "Student.txt".
     * 
     * Note that it only reads once, so there is any changes that apply after, it will not read that change
     */
    public void readFromFile() {
        try {
            // Since fw only be created once in this case, no need to put 
            // in the global
            File fw = new File(fileName);
            
            // Check if the file can be read/write. If unable, report to user.
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            // Now initialize the studentDbReader.
            studentDbReader = new Scanner(fw);
            
            // Loop for lines in file.
            while (studentDbReader.hasNext()) {
                String student = studentDbReader.nextLine();
                
                // If the line has a "#" at the start, ignore (Comment in Py3)
                if (!student.startsWith("#")) {
                    
                    /**
                     * The structure of the data (per line) consists of (ind.)
                     * 0 - Student Code (String)
                     * 1 - Student Name (String)
                     * 2 - Date of Birth (String)
                     * 3 - Learning Point (String or Double)
                     * 
                     * Each are separated by a "," (Standard CSV)
                     */
                    String[] studentData = student.split(",");

                    this.addStudent(
                        studentData[0],
                        studentData[1],
                        studentData[2],
                        studentData[3]
                    );
                }
            }
        }
        catch (FileNotFoundException file_ex) {
            // Exception on file not found
            System.out.println("Unable to read file. No data.");
        }
        catch (ArrayIndexOutOfBoundsException arr_ex) {
            // Exception on out of bound of the array.
        }
    }
    
    /**
     * This method writes data from file "Student.txt".
     */
    public void writeToFile() {
        // First line is the header (also the pseudo line)
        String outStr = "#Code,Name,Date-of-birth,Learning-point";
        
        try {
            // Now initialize the studentDbWriter.
            studentDbWriter = new FileWriter(fileName);
            
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
                        "\n%s,%s,%s,%.1f",
                        st.getStudentCode(),
                        st.getStudentName(),
                        st.getDateOfBirth(),
                        st.getLearningPoint());
            }
            
            studentDbWriter.write(outStr);
            studentDbWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            //
        }
        catch (IOException io_e) {
            //
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    ///// Option-based methods
    /////////////////////////////////////////////////////////////////////////////////
    
    // Option 1

    /**
     * Add student direct (or so-called non-casting and non-parsing)
     * @param studentCode as String
     * @param studentName as String
     * @param dateOfBirth as String
     * @param learningPoint as Double
     */
    public void addStudent(String studentCode, String studentName, String dateOfBirth, double learningPoint) {
        // direct add
        this.add(new Student(
                studentCode, 
                studentName, 
                dateOfBirth, 
                learningPoint
        ));
        
        writeToFile();
    }
    
    /**
     * Add student indirect (or so-called casting and/or parsing)
     * @param studentCode as String
     * @param studentName as String
     * @param dateOfBirth as String
     * @param learningPoint as String (will be parsed to Double)
     */
    public void addStudent(String studentCode, String studentName, String dateOfBirth, String learningPoint) {
        // indirect add
        try {
            this.add(new Student(
                    studentCode,
                    studentName,
                    dateOfBirth,
                    Double.parseDouble(learningPoint)
            ));
        }
        catch (NumberFormatException num_ex) {
            // happen when unable to parse (?) a string to the number
            // Locale may not apply. Switch to en-US or en-GB. ja-JP can cause errors
        }
        finally {
            // Write to file after adding new variable
            writeToFile();
        }
    }
    
    // Option 2

    /**
     * Find a student by a part-of-name. If matches, print it out and stop
     * @param stuName - Name of student
     */
    public void lookForStudent(String stuName) {
        
        // Just a loop through the ArrayList
        for (Student st : this) {
            if (st.getStudentName().contains(stuName)) {
                
                // Print out the information
                System.out.println(
                    String.format("Student code: %s\n", st.getStudentCode()) +
                    String.format("Student name: %s\n", st.getStudentName()) +
                    String.format("Date of birth: %s\n", st.getDateOfBirth()) +
                    String.format("Learning point: %.1f", st.getLearningPoint())
                );
                
                break; // Break once found, prevent dead trying
            }
        }
    }
    
    // Option 3

    /**
     * Display for all students in the database
     */
    public void displayStudents() {
        System.out.println(
                "Student list:\n" +
                "---------------------------"
        );
        
        // Just a loop through the ArrayList
        this.forEach((st) -> {
            System.out.println(
                    String.format("Student code: %s\n", st.getStudentCode()) +
                            String.format("Student name: %s\n", st.getStudentName()) +
                            String.format("Date of birth: %s\n", st.getDateOfBirth()) +
                            String.format("Learning point: %.1f\n", st.getLearningPoint()) +
                            "------------------"
            );
        });
    }
}
