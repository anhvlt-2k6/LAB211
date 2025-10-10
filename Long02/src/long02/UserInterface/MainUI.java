package long02.UserInterface;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import long02.BackEnd.DbHandler;
import long02.DataType.Candidate;
import long02.DataType.Experience;
import long02.DataType.Fresher;
import long02.DataType.Intern;

public class MainUI {
    
    private final Scanner sc;
    
    private final DbHandler dbHandler;

    private final String nameValidator = "[A-Za-z ]{1,}";
    private final String dateValidator = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})"; 
    private final String emailValidator = "([A-Za-z0-9._-]{1,})@(([A-Za-z0-9]{1,}).[A-Za-z0-9]{2,})";
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
    
    public MainUI() {
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
    
    private String enterDate(String message) {
        String date = this.enterAValue(message, "Invalid input. Should be in DD/MM/YYYY format", dateValidator, true);
        
        String[] dob = date.split("/");
        boolean isDateInvalidDob = (Integer.parseInt(dob[0]) < 0 || Integer.parseInt(dob[0]) > 31);
        boolean isMonthInvalidDob = (Integer.parseInt(dob[1]) < 0 || Integer.parseInt(dob[1]) > 12);
        boolean isYearInvalidDob = (Integer.parseInt(dob[2]) < 1900 || Integer.parseInt(dob[2]) > currentYear);
        
        while (isDateInvalidDob || isMonthInvalidDob || isYearInvalidDob) {
            if (isDateInvalidDob) {
                System.out.println("Invalid date. It should not smaller than 0 or larger than 31.");
            }
            
            if (isMonthInvalidDob) {
                System.out.println("Invalid month. It should not smaller than 0 or larger than 12.");
            }
            
            if (isYearInvalidDob) {
                System.out.println("Invalid year. It should not smaller than 1900 or larger than current year.");
            }
            
            date = this.enterAValue("DOB: ", "Invalid DOB input. Should be in DD/MM/YYYY format", dateValidator, true);
            
            dob = date.split("/");
            isDateInvalidDob = (Integer.parseInt(dob[0]) < 0 || Integer.parseInt(dob[0]) > 31);
            isMonthInvalidDob = (Integer.parseInt(dob[1]) < 0 || Integer.parseInt(dob[1]) > 12);
            isYearInvalidDob = (Integer.parseInt(dob[2]) < 1900 || Integer.parseInt(dob[2]) > currentYear);
        }
        
        return (date);
    }
    
    private boolean isOrder() {
        boolean isOrder = false;
        
        String getUserAnswer = this.enterAValue("Do you want to order now (Y/N): ", "Either Y or N (case insensitive)", yesnoValidation, true);
        
        if (getUserAnswer.equals("Y") || getUserAnswer.equals("y")) {
            isOrder = true;
        }
        
        return (isOrder);
    }
    
    private void optionFour() {
        ArrayList<Candidate> candidates = dbHandler.getCandidates();
        String experienceStr = "", fresherStr = "", internStr = "";
        
        for (Candidate c : candidates) {            
            if (c instanceof Experience || c.getCandidateID() == 0) {
                experienceStr += c.getFirstName() + " " + c.getLastName() + "\n";
            } else if (c instanceof Fresher || c.getCandidateID() == 1) {
                fresherStr += c.getFirstName() + " " + c.getLastName() + "\n";
            } else if (c instanceof Intern || c.getCandidateID() == 2) {
                internStr += c.getFirstName() + " " + c.getLastName() + "\n";
            }
        }
        
        System.out.println(
                "List of candidate: \n"
                + "===========EXPERIENCE CANDIDATE============\n"
                + experienceStr + "\n"
                + "==========FRESHER CANDIDATE==============\n"
                + fresherStr + "\n"
                + "===========INTERN CANDIDATE==============\n" 
                + internStr
        );
        
        //
        String name = this.enterAValue("Input Candidate name (First name or Last name): ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        int candidateType = Integer.parseInt(this.enterAValue("Input type of candidate: ", "Wrong input. Only 0, or 1, or 2.", candidateValidation, true));
        
        int count = 0;
        System.out.println(
                "+---+----------------+---------+-------------+----------+-----------------+----+\n" +
                "|No.|Fullname        |Birthdate|Address      |Phone     |Email            |Type|\n" +
                "+---+----------------+---------+-------------+----------+-----------------+----+"
        );
        for (Candidate c : candidates) {            
            String fullName = c.getFirstName() + " " + c.getLastName();
            if (c.getCandidateID() == candidateType && fullName.toLowerCase().contains(name.toLowerCase())) {
                System.out.println(String.format(
                "%3d|%16s|%9s|%13s|%10s|%17s|%4d",
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
                "+---+----------------+---------+-------------+----------+-----------------+----+"
        );
    }
    
    private void optionThree() {
        System.out.println("=== Enter a property for Internship ===");
        
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        
        // ask for date of birth
        String dateOfBirth = this.enterDate("Enter Date of Birth");
        
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive
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
        if (this.isOrder()) {
            dbHandler.sort();
        }
    }
    
    private void optionTwo() {
        System.out.println("=== Enter a property for Fresher ===");
        
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        
        // ask for date of birth
        String dateOfBirth = this.enterDate("Enter Date of Birth: ");
        
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive
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
        if (this.isOrder()) {
            dbHandler.sort();
        }
    }
    
    private void optionOne() {
        System.out.println("=== Enter a property for Experience ===");
        
        String firstName = this.enterAValue("Enter First Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        String lastName = this.enterAValue("Enter Last Name: ", "Wrong name input. Must be letters and spaces.", nameValidator, true);
        
        // ask for date of birth
        String dateOfBirth = this.enterDate("Enter Date of Birth: ");
        
        String address = this.enterAValue("Enter Address: ", "Wrong address format. Address must only contain numbers, letters, and spaces.", addressValidation, true);
        String phone = this.enterAValue("Enter Phone Number: ", "Wrong phone number. Must be digits (min 10).", phoneValidator, true);
        String email = this.enterAValue("Enter Email: ", "Wrong email input. Must be in format \"khanhvh@fe.edu.vn\".", emailValidator, true);
        
        // Exclusive
        String expInYear = this.enterAValue("Enter Year of Experience: ", "Wrong format. Year of Experience must be digits only and in range of 0 to 100.", expYearValidation, true);
        String proSkill = this.enterAValue("Enter Professional Skill: ", "Wrong opion. Only \"Java\", or \"C#\", or \"C\", or \"Python\", or \"Javascript\", or \"Ruby\", or \"Kotlin\"", proSkillValidation, true);
        
        // Call the backend service
        try {
            dbHandler.addCandidate(0, new String[] {firstName, lastName, dateOfBirth, address, phone, email}, new String[] {expInYear, proSkill});
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        // Ask user if they want to order
        if (this.isOrder()) {
            dbHandler.sort();
        }
    }
    
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

                int choice = Integer.parseInt(sc.nextLine());

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
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (NumberFormatException numex) {
                System.out.println(numex.getLocalizedMessage());
            }
        }
    }
}
