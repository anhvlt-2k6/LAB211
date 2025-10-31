package v02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * V02 - CSV Handler (act as a backend to handle CSV files)
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-24
 */
public final class CSVHandler extends ArrayList<CSVData> {

    // serial version uid
    private static final long serialVersionUID = 1L;
    
    // CSV reader and writer
    private Scanner csvReader;
    private FileWriter csvWriter;
    
    ///////////////////////////////////////////////////
    /////// Selection-based methods
    ///////////////////////////////////////////////////

    /**
     * Import CSV into the program
     * @param path of the CSV file
     * @throws Exception if cannot read the file, unable to parse number, or out of bound
     */
    public void importCSV(String path) throws Exception {
        try {
            // File initialization as input
            File fw = new File(path);
            
            // Check if the file is accessible and readable
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException("Cannot read " + path); // Stop right here
            }
            
            // Initialize the reader
            csvReader = new Scanner(fw);
            
            // If the csv will has lines, continue reading
            while (csvReader.hasNext()) {
                // csvData single line as a line of csv
                String csvData = csvReader.nextLine();
                
                // File header
                if (!csvData.equals("ID,Tên,Email,Điện thoại,Địa chỉ")) {
                    
                    /**
                     * Split into segments
                     * [0] - id
                     * [1] - name
                     * [2] - email
                     * [3] - phoneNumber
                     * [4] - address
                     */
                    
                    // Split into segments
                    String[] data = csvData.split(",");
                    
                    // Only add if data has enough param
                    if (data.length == 5) {
                        this.add(new CSVData(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4]
                        ));
                    }
                }
            }
        } 
        catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new Exception("Error: " + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Export CSV into files
     * @param path of the CSV file
     * @throws Exception if no data in the array list or IO error
     */
    public void exportCSV(String path) throws Exception {
        // exception on empty data (not able to write, stop right there)
        if (this.isEmpty()) {
            throw new Exception("No data is available.");
        }
        
        // First line is the header (also the pseudo line)
        String outStr = "ID,Tên,Email,Điện thoại,Địa chỉ";
        
        try {
            // Now initialize the studentDbWriter.
            csvWriter = new FileWriter(path);
            
            // Just a loop through the ArrayList
            for (CSVData dat : this) {
                outStr += String.format(
                        "\n%s,%s,%s,%s,%s",
                        dat.getId(),
                        dat.getName().trim(),
                        dat.getEmail().trim(),
                        dat.getPhoneNumber().trim(),
                        dat.getAddress().trim()
                );
            }
            
            // Write to file and close
            csvWriter.write(outStr);
            csvWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            // Ignore that (file is not found)
        }
        catch (IOException io_e) {
            // Unable to write file
            throw new Exception(io_e);
        }
    }
    
    /**
     * Format Address
     * @throws Exception if no data in the array list 
     */
    public void formatAddress() throws Exception {
        // exception on empty data (not able to write, stop right there)
        if (this.isEmpty()) {
            throw new Exception("No data is available.");
        }
        
        // Loop through the array
        for (CSVData dat : this) {
            // Get the data, then split it into array of string by spaces
            String[] addressSegment = dat.getAddress().split(" ");
            
            // Proper address
            String properAddress = "";
            
            // for each string of the, since split by spaces, has multiple segments
            for (String s : addressSegment) {
                // If the string is not empty, add it into properAddress
                if (!s.isEmpty()) {
                    properAddress += s + " ";
                }
            }

            // Set back the value. properAddress may contain spaces and both
            // start and end, so trim it.
            dat.setAddress(properAddress.trim());
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void formatName() throws Exception {
        // exception on empty data (not able to write, stop right there)
        if (this.isEmpty()) {
            throw new Exception("No data is available.");
        }
        
        // Loop through the array
        for (CSVData dat : this) {
            // Get the data, then split it into array of string by spaces
            String[] nameSegment = dat.getName().split(" ");

            String properName = "";
            
            // for each string of the, since split by spaces, has multiple segments
            for (String s : nameSegment) {
                // If the string is not empty
                // Upper case the first letter first, and add it into properName
                if (!s.isEmpty()) {
                    String firstLetter = s.substring(0, 1).toUpperCase();
                    String remainingLetter = s.substring(1);

                    properName += firstLetter + remainingLetter + " ";
                }
            }
            
            // Set back the value. properAddress may contain spaces and both
            // start and end, so trim it.
            dat.setName(properName.trim());
        }
    }
}
