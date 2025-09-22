package v02;

import java.util.Scanner;

public class V02 {

    private final Scanner sc;
    
    private final CSVHandler csvHandler;
    
    public V02() {
       sc = new Scanner(System.in);
       csvHandler = new CSVHandler();
    }
    
    private void optionOne() {
        System.out.println("--------- Import CSV ---------");
        
        String csvName = "";
        
        while (csvName.isEmpty()) {
            System.out.print("Enter Path: ");
            String userEnterCSVPath = sc.nextLine();
            
            if (userEnterCSVPath.length() >= 5) {
                String csvPathLast = userEnterCSVPath.subSequence(userEnterCSVPath.length() - 4, userEnterCSVPath.length()).toString();
                
                if (!csvPathLast.toLowerCase().equals(".csv")) {
                    System.out.println("File is not CSV");
                } else {
                    csvName = userEnterCSVPath;
                    break;
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        
        try {
            csvHandler.importCSV(csvName);
            System.out.println("Import: Done");
        } catch (Exception e) {
            System.out.println("Import: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void optionTwo() {
        
        System.out.println("--------- Format Address");
        
        try {
            csvHandler.formatAddress();
            
            System.out.println("-------Format: Done");
        } catch (Exception e) {
            System.out.println("-------Format: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void optionThree() {
        System.out.println("--------- Format Name");
        
        try {
            csvHandler.formatName();
            
            System.out.println("-------Format: Done");
        } catch (Exception e) {
            System.out.println("-------Format: Error");
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void optionFour() {
        System.out.print(
                "--------- Export CSV ---------\n" +
                "Enter Path: "
        );
        
        String csvName = "";
        
        while (csvName.isEmpty()) {
            String userEnterCSVPath = sc.nextLine();
            String csvPathLast = userEnterCSVPath.subSequence(userEnterCSVPath.length() - 4, userEnterCSVPath.length()).toString();
        
            if (!(csvPathLast.equals(".csv") || csvPathLast.equals(".CSV"))) {
                System.out.println("File is not CSV");
            } else {
                csvName = userEnterCSVPath;
                break;
            }
        }
        
        try {
            csvHandler.exportCSV(csvName);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void displayUserInterface() {
        while (true) {
            
            System.out.println(
                            "======= Format CSV Program =======\n"
                          + "1. Import CSV\n"
                          + "2. Format Address\n"
                          + "3. Format Name\n"
                          + "4. Export CSV\n"
                          + "5. Exit"
            );
            
            try {
                System.out.print("Please choice one option: ");
                
                int choice = Integer.parseInt(sc.nextLine());
                
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
                        System.out.println("Invalid choice!");
                        break;
                }
                
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice!");
            }
        }
    }
    
    public static void main(String[] args) {
        V02 v2 = new V02();
        v2.displayUserInterface();
    }
}
