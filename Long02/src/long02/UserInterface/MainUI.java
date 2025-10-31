package long02.UserInterface;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import long02.BackEnd.DbHandler;
import long02.DataType.Candidate;
import long02.DataType.Experience;
import long02.DataType.Fresher;
import long02.DataType.Intern;

/**
 * Long 02 - Main User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-06
 */
public class MainUI {
    
    /**
     * Default params
     * sc as user input
     * dbHandler as the database
     * validator as the validators
     * currentYear as the current set year
     */
    private final Scanner sc;
    
    private final DbHandler dbHandler;

    private final String nameValidator = "[A-Za-z ]{1,}";
    private final String dateValidator = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})"; 
    private final String emailValidator = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    private final String phoneValidator = "[0-9]{10,}";
    private final String rankofGradValidation = "Excellence|Good|Fair|Poor"; // case sensitive
    private final String addressValidation = "[0-9A-Za-z ]{1,}";
    private final String proSkillValidation = "Java|C#|C|Python|Javascript|Ruby|Kotlin";
    private final String majorValidation = "CS|CE|AI|Other"; // case sensitive
    private final String semesterValidation = "[0-9]{1,2}";
    private final String yesnoValidation = "Y|y|N|n";
    private final String candidateValidation = "0|1|2";
    private final String expYearValidation = "[0-9]{1,2}";
    
    private int currentYear = 0;
    
    /**
     * Constructor of the user interface
     */
    public MainUI() {
        // initialize the user input, database, and get the current year
        sc = new Scanner(System.in);
        dbHandler = new DbHandler();
        currentYear = Year.now().getValue();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String error, String validation, boolean isTrim) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
        if (error.length() == 0) {
            error = "Invalid Input.";
        }
        
        // Assume the value is empty string
        String value = "";
        // Loop for correct format
        while (!value.matches(validation)) {
            System.out.print(message);
            String valueUI = (isTrim) ? (sc.nextLine().trim()) : (sc.nextLine());
            
            // Validate user input, if match with the criteria, assign with the 
            // return value, if not, asking user again
            if (!valueUI.matches(validation)) {
                // Notify user
                System.out.println(error);
            } else {
                // assign value
                value = valueUI;
            }
        }
        
        // return value
        return (value);
    }
    
    /**
     * Enter a date
     * @param message As message for user input
     * @return a String as valid format of date
     */
    private String enterDate(String message) {
        // Call the raw string input with date validation
        String date = this.enterAValue(message, "Invalid input. Should be in DD/MM/YYYY format", dateValidator, true);
        
        // Split for date, month, and year
        String[] dateString = date.split("/");
        
        // Check for invalid date, month, and year
        boolean isDateInvalid = (Integer.parseInt(dateString[0]) < 0 || Integer.parseInt(dateString[0]) > 31);
        boolean isMonthInvalid = (Integer.parseInt(dateString[1]) < 0 || Integer.parseInt(dateString[1]) > 12);
        boolean isYearInvalid = (Integer.parseInt(dateString[2]) < 1900 || Integer.parseInt(dateString[2]) > currentYear);
        
        // If any of the param is valid, loop asking
        while (isDateInvalid || isMonthInvalid || isYearInvalid) {
            if (isDateInvalid) {
                System.out.println("Invalid date. It should not smaller than 0 or larger than 31.");
            }
            
            if (isMonthInvalid) {
                System.out.println("Invalid month. It should not smaller than 0 or larger than 12.");
            }
            
            if (isYearInvalid) {
                System.out.println("Invalid year. It should not smaller than 1900 or larger than current year.");
            }
            
            // Re-ask user to enter date
            date = this.enterAValue(message, "Invalid DOB input. Should be in DD/MM/YYYY format", dateValidator, true);
            
            // Split for date, month, and year
            dateString = date.split("/");
            
            // Check for invalid date, month, and year
            isDateInvalid = (Integer.parseInt(dateString[0]) < 0 || Integer.parseInt(dateString[0]) > 31);
            isMonthInvalid = (Integer.parseInt(dateString[1]) < 0 || Integer.parseInt(dateString[1]) > 12);
            isYearInvalid = (Integer.parseInt(dateString[2]) < 1900 || Integer.parseInt(dateString[2]) > currentYear);
        }
        
        // Return value;
        return (date);
    }
    
    /**
     * Ask if user want to order
     */
    private void isOrder() {
        // Get user input
        String getUserAnswer = this.enterAValue("Do you want to order now (Y/N): ", "Either Y or N (case insensitive)", yesnoValidation, true);
        
        // Perform action if valid
        if (getUserAnswer.equals("Y") || getUserAnswer.equals("y")) {
            dbHandler.sort();
        }
    }
    
    /**
     * Option Four - get and search the candidate list
     */
    private void optionFour() {
        // Get the database to front end
        ArrayList<Candidate> candidates = dbHandler.getCandidates();
        
        // The str of candidates, by each type
        String experienceStr = "", fresherStr = "", internStr = "";
        
        // Loop for each candidate, and try add the name into value
        for (Candidate c : candidates) {            
            if (c instanceof Experience || c.getCandidateID() == 0) {
                experienceStr += c.getFirstName() + " " + c.getLastName() + "\n";
            } else if (c instanceof Fresher || c.getCandidateID() == 1) {
                fresherStr += c.getFirstName() + " " + c.getLastName() + "\n";
            } else if (c instanceof Intern || c.getCandidateID() == 2) {
                internStr += c.getFirstName() + " " + c.getLastName() + "\n";
            }
        }
        
        // Display the name of candidates
        System.out.println(
                "List of candidate: \n"
                + "===========EXPERIENCE CANDIDATE============\n"
                + experienceStr + "\n"
                + "==========FRESHER CANDIDATE==============\n"
                + fresherStr + "\n"
                + "===========INTERN CANDIDATE==============\n" 
                + internStr
        );
        
        // Get the user input for name and type
        String name = this.enterAValue("Input Candidate name (First name or Last name): ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        int candidateType = Integer.parseInt(this.enterAValue("Input type of candidate: ", "Wrong input. Only 0, or 1, or 2.", candidateValidation, true));
        
        // Count and display
        int count = 0;
        System.out.println(
                "+---+----------------+-----------+-------------+----------+-----------------+----+\n" +
                "|No.|Fullname        | Birthdate |Address      |Phone     |Email            |Type|\n" +
                "+---+----------------+-----------+-------------+----------+-----------------+----+"
        );
        // For each candidate, try display if matching (case insensitive)
        for (Candidate c : candidates) {            
            String fullName = c.getFirstName() + " " + c.getLastName(); // Get full anem of the candidate
            if (c.getCandidateID() == candidateType && fullName.toLowerCase().contains(name.toLowerCase())) {
                System.out.println(String.format(
                "|%3d|%16s|%10s|%13s|%8s|%17s|%4d",
                count,
                fullName,
                c.getDoB(),
                c.getAddress(),
                c.getPhone(),
                c.getEmail(),
                candidateType
                ));
                count += 1;
            }   
        }
        System.out.println(
                "+---+----------------+-----------+-------------+----------+-----------------+----+"
        );
    }
    
    /**
     * Option Three - Enter for internship
     */
    private void optionThree() {
        System.out.println("=== Enter a property for Internship ===");
        
        // Enter default values
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String dateOfBirth = this.enterDate("Enter Date of Birth");        
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive values
        String major = this.enterAValue("Enter major (Only CS, CE, AI, Other are accepted in case sensitive): ", "Only CS, CE, AI, Other are accepted in case sensitive", majorValidation, true);
        String semester = this.enterAValue("Enter semester: ", "Only valid from 0 - 9 (single digit)", semesterValidation, true);
        String schoolName = this.enterAValue("Enter School Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        
        // Call the backend service
        try {
            dbHandler.addCandidate(2, new String[] {firstName, lastName, dateOfBirth, address, phone, email}, new String[] {major, semester, schoolName});
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        // Ask user if they want to order
        this.isOrder();
    }
    
    /**
     * Option Two - Fresher
     */
    private void optionTwo() {
        System.out.println("=== Enter a property for Fresher ===");
        
        // Enter default values
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String dateOfBirth = this.enterDate("Enter Date of Birth: ");
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive values
        String graduationDate = this.enterDate("Enter Graduation Date: ");
        String rank = this.enterAValue("Enter rank: ", "Invalid ranking. Only Excellence, Good, Fair, Poor (case sensitive)", rankofGradValidation, true);
        String schoolName = this.enterAValue("Enter school name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        
        // Call the backend service
        try {
            dbHandler.addCandidate(1, new String[] {firstName, lastName, dateOfBirth, address, phone, email}, new String[] {graduationDate, rank, schoolName});
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        // Ask user if they want to order
        this.isOrder();
    }
    
    /**
     * Option One - Experience Candidate
     */
    private void optionOne() {
        System.out.println("=== Enter a property for Experience ===");
        
        // Enter default values
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String dateOfBirth = this.enterDate("Enter Date of Birth: ");
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive values
        String expInYear = this.enterAValue("Enter Year of Experience: ", "Wrong format. Year of Experience must be digits only and in range of 0 to 100.", expYearValidation, true);
        String proSkill = this.enterAValue("Enter Professional Skill: ", "Wrong opion. Only \"Java\", or \"C#\", or \"C\", or \"Python\", or \"Javascript\", or \"Ruby\", or \"Kotlin\"", proSkillValidation, true);
        
        // Call the backend service
        try {
            dbHandler.addCandidate(0, new String[] {firstName, lastName, dateOfBirth, address, phone, email}, new String[] {expInYear, proSkill});
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        // Ask user if they want to order
        this.isOrder();
    }
    
    /**
     * Main User interface
     */
    public void displayUI() {
        while (true) {
           try {
                System.out.print(
                        "CANDIDATE MANAGEMENT SYSTEM \n" +
                        "1. Experience \n" +
                        "2. Fresher \n" +
                        "3. Internship \n" +
                        "4. Searching \n" +
                        "5. Exit \n" +
                        "Please choose:"
                );
                
                // Try get the user input choice
                int choice = Integer.parseInt(sc.nextLine());

                // in each case, try call the option function
                switch (choice) {
                    case 1:
                        this.optionOne();
                        break;
                    case 2:
                        this.optionTwo();
                        break;
                    case 3:
                        this.optionThree();
                        break;
                    case 4:
                        this.optionFour();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        // Invalid Choice
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (NumberFormatException numex) {
                // In case unable to parse
                System.out.println(numex.getLocalizedMessage());
            }
        }
    }
}
