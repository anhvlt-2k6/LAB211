package short04.UserDb;

public class Users {
    private String userName;
    private String password;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String dateOfBirth;
    
    public Users(
            String userName, 
            String password, 
            String name, 
            String phoneNumber, 
            String emailAddress,
            String address,
            String dateofBirth) {
        this.userName = userName;
        this.password = password; // Input password must be the MD5
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.dateOfBirth = dateofBirth;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
