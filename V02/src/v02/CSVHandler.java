package v02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class CSVHandler extends ArrayList<CSVData> {

    private static final long serialVersionUID = 1L;
    
    // Database reader and writer
    private Scanner csvReader;
    private FileWriter csvWriter;
    
    ///////////////////////////////////////////////////
    /////// Selection-based methods
    ///////////////////////////////////////////////////
    
    public void importCSV(String path) throws Exception {
        try {
            
            File fw = new File(path);
            
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException("Cannot read " + path); // Stop right here
            }
            
            csvReader = new Scanner(fw);
            
            while (csvReader.hasNext()) {
                String csvData = csvReader.nextLine();
                
                if (!csvData.equals("ID,Tên,Email,Điện thoại,Địa chỉ")) {
                    
                    /**
                     * Split into segments
                     * [0] - id
                     * [1] - name
                     * [2] - email
                     * [3] - phoneNumber
                     * [4] - address
                     */
                    
                    String[] data = csvData.split(",");
                    
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
    
    public void exportCSV(String path) throws Exception {
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
            
        }
        catch (IOException io_e) {
            
        }
    }
    
    public void formatAddress() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("No data is available.");
        }
        
        for (CSVData dat : this) {
            String[] addressSegment = dat.getAddress().split(" ");
            
            String properAddress = "";

            for (String s : addressSegment) {
                if (!s.isEmpty()) {
                    properAddress += s + " ";
                }
            }

            dat.setAddress(properAddress.trim());
        }
    }
    
    public void formatName() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("No data is available.");
        }
        
        for (CSVData dat : this) {
            String[] nameSegment = dat.getName().split(" ");

            String properName = "";

            for (String s : nameSegment) {
                if (!s.isEmpty()) {
                    String firstLetter = s.substring(0, 1).toUpperCase();
                    String remainingLetter = s.substring(1);

                    properName += firstLetter + remainingLetter + " ";
                }
            }

            dat.setName(properName.trim());
        }
    }
}
