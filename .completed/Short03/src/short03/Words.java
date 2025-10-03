package short03;

import java.io.File;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Short 03 - Word Database Handler
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Words {
    
    private FileWriter fwEdit;
    private Scanner fwRead;
    
    /////////////////////////////////////////////////////////////////////
    ///////////// Non-selection methods
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Get the file name, based on the word
     * @param word as input word
     * @return String as the file name
     */
    public String getFileName(String word) {
        return String.format("%c", word.charAt(0)).toLowerCase() + "_index.dat";
    }
    
    /////////////////////////////////////////////////////////////////////
    ///////////// Selection-based methods
    /////////////////////////////////////////////////////////////////////
    
    public boolean isWordDuplicated(String word) {
        // Find duplication
        boolean isDuplicated = false;
        
        // Read from file, to find duplicate
        try {
            // Get the reading
            fwRead = new Scanner(new File(getFileName(word)));
        
            // loop to find the word if duplicated
            while (fwRead.hasNext()) {
                // Seperate by CSV
                String[] str = fwRead.nextLine().split(",");
                
                // Get the first segment as word
                if (str[0].equals(word)) {
                    isDuplicated = true;
                }
            }
        }
        catch (FileNotFoundException file_io_excep) {
            // Handle if the database hasn't been created yet.
        }
        
        return (isDuplicated);
    }
    
    /**
     * Create a new word, and add it into the index file (follows the char_index.txt
     * @param word The word
     * @param meaning The meaning with the word
     * @return If the word is created AND write to the database
     */
    public boolean createWord(String word, String meaning) {
        boolean isSuccess = false; // if writing new file is success
        
        // Find duplication
        boolean isDuplicated = false;
        
        // Read from file, to find duplicate
        try {
            // Get the reading
            fwRead = new Scanner(new File(getFileName(word)));
        
            // loop to find the word if duplicated
            while (fwRead.hasNext()) {
                // Seperate by CSV
                String[] str = fwRead.nextLine().split(",");
                
                // Get the first segment as word
                if (str[0].equals(word)) {
                    isDuplicated = true;
                }
            }
        }
        catch (FileNotFoundException file_io_excep) {
            // Handle if the database hasn't been created yet.
        }
        
        if (!isDuplicated) {
            try {
                // Append the word database
                fwEdit = new FileWriter(getFileName(word), true);

                // Write the new word
                fwEdit.write(String.format("%s,%s", word, meaning));

                // Close the file
                fwEdit.close();

                isSuccess = true;
            } catch (IOException io_e) {
                isSuccess = false;
            }
        } else {
            System.out.println("A duplicated word is found");
        }
        
        return (isSuccess); // return if success
    }
    
    /**
     * Edit the meaning of a word in the index file
     * @param word As input word
     * @param meaning As input meaning of the word
     * @return If editing the meaning of a word is success
     */
    public boolean editWord(String word, String meaning) {
        // Return param
        boolean isSuccess = false; 
        
        // Conditional variable. To check if the word exists.
        boolean isFoundWord = false;
        
        ArrayList<String> str_arr = new ArrayList<>();
        
        // Read from file
        try {
            fwRead = new Scanner(new File(getFileName(word)));
        
            while (fwRead.hasNext()) {
                str_arr.add(fwRead.nextLine());
            }
        }
        catch (FileNotFoundException file_io_excep) {
            // Handle if the database hasn't been created yet.
        }
        
        // Try modify the meaning
        for (int i = 0; i < str_arr.size(); i++) {
            // If matching, with the word (you know)
            if (str_arr.get(i).startsWith(word)) {
                
                // Split the string, where 0 - word, 1 - meaning
                String[] word_data = str_arr.get(i).split(",");
                
                word_data[1] = meaning;
                
                str_arr.set(i, word_data[0] + "," + word_data[1]);
                
                isFoundWord = true; // Only if the word is found
                
                break; // Once find, stop right there
            }
        }
        
        // Write back to the file
        String writeBack = "";
        
        if (isFoundWord) {
            
            for (String str : str_arr) {
                writeBack += str + "\n";
            }

            try {
                // Initialize the file writer
                fwEdit = new FileWriter(getFileName(word));

                // write the content
                fwEdit.write(writeBack);

                // close the pointer
                fwEdit.close();

                isSuccess = true; // Only set once the file is written

            } catch (IOException io_e) {
                // Handle the IO Exception
            }
        } else {
            isSuccess = false;
        }
        
        return (isSuccess);
    }
    
    /**
     * Look for a word in the indexed file
     * @param word as input word for searching
     */
    public void lookUpMeaning(String word) {
        // Read from file
        try {
            fwRead = new Scanner(new File(getFileName(word)));
            
            // Loop through the file
            while (fwRead.hasNext()) {
                
                // Split the file using a comma
                String[] fwReadStr = fwRead.nextLine().split(",");
                
                // If the word is found, print it out.
                if (fwReadStr[0].equals(word)) {
                    System.out.println("Meaning: " + fwReadStr[1]);
                    break; // Once found, exit the loop.
                }
            }
        }
        catch (FileNotFoundException file_io_excep) {
            // File Exception Io (R/W issue)
        }
    }
}
