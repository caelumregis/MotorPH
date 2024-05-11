import java.util.Date;

public class Attendance {
    private String attendanceID;
    private Employee employee;
    private Date startTime;
    private Date endTime;

    // Constructor
    public Attendance(String attendanceID, Employee employee, Date startTime, Date endTime) {
        this.attendanceID = attendanceID;
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(String attendanceID) {
        this.attendanceID = attendanceID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
