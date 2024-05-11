import java.util.Date;

public class Salary {
    private String salaryID;
    private Employee employee;
    private double amount;
    private Date date;

    // Constructor
    public Salary(String salaryID, Employee employee, double amount, Date date) {
        this.salaryID = salaryID;
        this.employee = employee;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public String getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(String salaryID) {
        this.salaryID = salaryID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
