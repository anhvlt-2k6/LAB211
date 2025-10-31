package v02;

import java.util.Scanner;

/**
 * V02 - Main entry
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-24
 */
public class V02 {

    private final Scanner sc;
    
    private final CSVHandler csvHandler;
    
    /**
     * Constructor of the Main entry
     */
    public V02() {
       sc = new Scanner(System.in);
       csvHandler = new CSVHandler();
    }
    
    /**
     * Option One - Import CSV
     */
    private void optionOne() {
        
        System.out.println("--------- Import CSV ---------");
        
        // Assume the csvName is invalid as empty
        String csvName = "";
        
        // Loop for the csvName input
        while (csvName.isEmpty()) {
            // Ask user to enter the Path of CSV
            System.out.print("Enter Path: ");
            String userEnterCSVPath = sc.nextLine();
            
            // A valid csv name has a length of 5
            if (userEnterCSVPath.length() >= 5) {
                
                // Get the csv last part.
                String csvPathLast = userEnterCSVPath.subSequence(userEnterCSVPath.length() - 4, userEnterCSVPath.length()).toString();
                
                // If not matching to a .csv (case-insensitive), reject the file
                if (!csvPathLast.toLowerCase().equals(".csv")) {
                    System.out.println("Invalid input. The path should include with a CSV file (often ends in .csv or .CSV)");
                } else {
                    csvName = userEnterCSVPath;
                    break;
                }
            } else {
                // Notify user for invalid input
                System.out.println("Invalid input. The path is too short.");
            }
        }
        
        // Try import the CSV
        try {
            csvHandler.importCSV(csvName);
            System.out.println("Import: Done");
        } catch (Exception e) {
            // If there is any error, display the errors
            System.out.println("Import: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Option Two - Format Address
     */
    private void optionTwo() {
        
        System.out.println("--------- Format Address");
        
        // Try format the CSV
        try {
            csvHandler.formatAddress();
            System.out.println("-------Format: Done");
        } catch (Exception e) {
            // If there is any error, display the errors
            System.out.println("-------Format: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Option Three - Format Name
     */
    private void optionThree() {
        System.out.println("--------- Format Name");
        
        // Try format the CSV
        try {
            csvHandler.formatName();
            System.out.println("-------Format: Done");
        } catch (Exception e) {
            // If there is any error, display the errors
            System.out.println("-------Format: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Option Four - Export CSV
     */
    private void optionFour() {
        System.out.print(
                "--------- Export CSV ---------\n" +
                "Enter Path: "
        );
        
        // Assume the csvName is invalid as empty
        String csvName = "";
        
        // Loop for the csvName input
        while (csvName.isEmpty()) {
            // Ask user to enter the Path of CSV
            System.out.print("Enter Path: ");
            String userEnterCSVPath = sc.nextLine();
            
            // A valid csv name has a length of 5
            if (userEnterCSVPath.length() >= 5) {
                
                // Get the csv last part.
                String csvPathLast = userEnterCSVPath.subSequence(userEnterCSVPath.length() - 4, userEnterCSVPath.length()).toString();
                
                // If not matching to a .csv (case-insensitive), reject the file
                if (!csvPathLast.toLowerCase().equals(".csv")) {
                    System.out.println("Invalid input. The path should include with a CSV file (often ends in .csv or .CSV)");
                } else {
                    csvName = userEnterCSVPath;
                    break;
                }
            } else {
                // Notify user for invalid input
                System.out.println("Invalid input. The path is too short.");
            }
        }
        
        try {
            // Try to export to file
            csvHandler.exportCSV(csvName);
            System.out.println("Export: Done");
        } catch (Exception e) {
            // If there is any error, display the errors
            System.out.println("Export: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Display the user interface
     */
    public void displayUserInterface() {
        while (true) {
            try {
                System.out.print(
                    "======= Format CSV Program =======\n"
                  + "1. Import CSV\n"
                  + "2. Format Address\n"
                  + "3. Format Name\n"
                  + "4. Export CSV\n"
                  + "5. Exit\n"
                  + "Please choice one option: "
                );
                // Try parse user input
                int choice = Integer.parseInt(sc.nextLine());
                
                // Each case, map to a user selecetion.
                // See the selection above
                switch (choice) {
                    case 1:
                        optionOne();
                        break;
                    case 2:
                        optionTwo();
                        break;
                    case 3:
                        optionThree();
                        break;
                    case 4:
                        optionFour();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        // Out of choice.
                        System.out.println("Invalid choice!");
                        break;
                }
                
            } catch (NumberFormatException ex) {
                // Not a number.
                System.out.println("Invalid choice!");
            }
        }
    }
    
    /**
     * Main Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        V02 v2 = new V02();
        v2.displayUserInterface();
    }
}
