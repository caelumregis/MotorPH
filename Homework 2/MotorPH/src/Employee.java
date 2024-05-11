import java.util.Date;

public class Employee {

    private String employeeID;
    private String name;
    private String position;
    private String email;
    private String phone;
    private String address;
    private Date hireDate;

    // Constructor
    public Employee(String employeeID, String name, String position, String email, String phone, String address, Date hireDate) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.hireDate = hireDate;
    }

    // Getters and Setters
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
