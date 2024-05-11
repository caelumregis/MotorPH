public class Department {
    private String departmentID;
    private String name;
    private String location;

    // Constructor
    public Department(String departmentID, String name, String location) {
        this.departmentID = departmentID;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
