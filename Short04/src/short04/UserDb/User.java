package short04.UserDb;

/**
 * Short 04 - User class as blueprint
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class User {
    private final int id;
    private final String userName;
    private String password;
    private final String name;
    private final String phoneNumber;
    private final String emailAddress;
    private final String address;
    private final String dateOfBirth;
    
    /**
     * Constructor of Users class
     * @param id as unique id
     * @param userName userName (or Account)
     * @param password Password of the userName (not name). Always in MD5
     * @param name Actual name of the user (Real name)
     * @param phoneNumber Phone number of the user
     * @param emailAddress Email address of the user
     * @param address Home address of the user
     * @param dateofBirth Date of Birth, follows as DD/MM/YYYY format
     */
    public User(
            int id,
            String userName, 
            String password, 
            String name, 
            String phoneNumber, 
            String emailAddress,
            String address,
            String dateofBirth) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.dateOfBirth = dateofBirth;
    }

    /**
     * Return user name (or Account)
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Return password as MD5
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Return actual name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Return phone number
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Return email address
     * @return String
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Return physical address (or home address)
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Return date of birth
     * @return String
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Return id of the user
     * @return id in integer
     */
    public int getId() {
        return id;
    }

    /**
     * Set password (in MD5 format)
     * @param password as String in MD5 format
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
