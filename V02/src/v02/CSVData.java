package v02;

/**
 * V02 - CSV Data (act as a blueprint for the data structure inside CSV handler)
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-24
 */
public class CSVData {
    
    // Property
    private final String id;
    private String name;
    private final String email;
    private final String phoneNumber;
    private String address;
    
    /**
     * Constructor
     * @param id as id of object
     * @param name as name of object
     * @param email as email of object
     * @param phoneNumber as phone of object
     * @param address as address of object
     */
    public CSVData(String id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Get the name of object
     * @return name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of object
     * @param name as the name wanted to set for object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get address of object
     * @return address as a string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address of object
     * @param address as the address wanted to set for object
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get id of object
     * @return id as a string
     */
    public String getId() {
        return id;
    }

    /**
     * Get email of object
     * @return email as a string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get phone number of object 
     * @return phone number as a string
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
