/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package short02;

import java.util.Scanner;

/**
 * Short 02 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class UserInterface {
    
    /**
    * Global variables
    * sc - SCanner (General I/O for Program)
    * nameList - NameList
    */
    
    private NameList nameList;
    private final Scanner sc;
    
    /**
     * Constructor of the "entry" class.
     * Initialize the user input (Scanner)
     */
    public UserInterface() {
        sc = new Scanner(System.in);
    }
    
    /**
     * Display User Interface
     */
    public void userInterface() {
        while (true) {
            try {
                System.out.println("Enter the value of n");
                int arr_length = Integer.parseInt(sc.nextLine().trim());
                
                // Check if the user enter wrong value
                if (arr_length > 0 && arr_length <= 50) {
                    nameList = new NameList(arr_length);
                    
                    // Ask user to enter values.
                    // Everytime user press enter, it accepts the value and move next.
                    System.out.println(String.format("Enter %d names", arr_length));
                    for (int i = 0; i < arr_length; i++) {
                        String value = "";
                        
                        // Loop for user re-input
                        while (value.isEmpty()) {
                            String userInput = sc.nextLine();
                            
                            // Validate if user input is really a name
                            if (userInput.matches("([A-Z]{1})([a-z ]){0,}")) {
                                value = userInput;
                                nameList.addElement(i, value);
                                break;
                            } else {
                                System.out.println("Please validate your input (Only letters and spaces)");
                            }
                        }
                    }
                    
                    // Display for pre-sorting array
                    System.out.println("List input name:");
                    nameList.displayNameArray();
                    
                    // Sort the array 
                    nameList.sortNameArray();
                    
                    // Display for post-sorting array
                    System.out.println("List sort name:");
                    nameList.displayNameArray();
                } else {
                    System.out.println("Invalid input. Length of an array should be a positive number and less than 50.");
                }
            } catch (NumberFormatException number_exception) {
                System.out.println(number_exception.getLocalizedMessage());
            }
        }
    }
}
