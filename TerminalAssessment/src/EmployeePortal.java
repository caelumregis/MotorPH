import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeePortal {

    private JFrame frame;
    private int employeeId;

    public EmployeePortal(int employeeId) {
        this.employeeId = employeeId;

        // Create the frame
        frame = new JFrame("Employee Portal");
        frame.setSize(960, 600); // Half of 1920 width and reduced height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Employee details area
        JTextArea employeeDetailsArea = new JTextArea();
        employeeDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Increased font size
        employeeDetailsArea.setEditable(false);
        employeeDetailsArea.setBorder(new LineBorder(Color.BLACK));
        employeeDetailsArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(employeeDetailsArea);
        scrollPane.setBounds(30, 30, 600, 500);
        frame.add(scrollPane);

        // Buttons
        JButton timeInButton = new JButton("Time-In");
        timeInButton.setBounds(660, 30, 250, 50);
        frame.add(timeInButton);
        timeInButton.addActionListener(e -> timeIn());

        JButton timeOutButton = new JButton("Time-Out");
        timeOutButton.setBounds(660, 100, 250, 50);
        frame.add(timeOutButton);
        timeOutButton.addActionListener(e -> timeOut());

        JButton attendanceTrackerButton = new JButton("Attendance Tracker");
        attendanceTrackerButton.setBounds(660, 170, 250, 50);
        frame.add(attendanceTrackerButton);
        attendanceTrackerButton.addActionListener(e -> viewAttendance());

        JButton leaveRequestButton = new JButton("Submit Leave Request");
        leaveRequestButton.setBounds(660, 250, 250, 50);
        frame.add(leaveRequestButton);
        leaveRequestButton.addActionListener(e -> submitLeaveRequest());

        JButton viewSalaryButton = new JButton("View Salary");
        viewSalaryButton.setBounds(660, 320, 250, 50);
        frame.add(viewSalaryButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(660, 490, 250, 50);
        frame.add(logoutButton);
        logoutButton.addActionListener(e -> {
            frame.dispose();
            new MotorPHLogin();
        });

        // Load employee details
        loadEmployeeDetails(employeeDetailsArea);

        // Display the frame
        frame.setVisible(true);
    }

    private void loadEmployeeDetails(JTextArea employeeDetailsArea) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                StringBuilder details = new StringBuilder();
                details.append("ID: ").append(rs.getInt("id")).append("\n");
                details.append("Last Name: ").append(rs.getString("lastname")).append("\n");
                details.append("First Name: ").append(rs.getString("firstname")).append("\n");
                details.append("Birthday: ").append(rs.getDate("birthday")).append("\n");
                details.append("Address: ").append(rs.getString("address")).append("\n");
                details.append("Phone Number: ").append(rs.getString("phonenumber")).append("\n");
                details.append("SSS Number: ").append(rs.getString("sssnumber")).append("\n");
                details.append("PhilHealth Number: ").append(rs.getString("philhealthnumber")).append("\n");
                details.append("TIN Number: ").append(rs.getString("tinnumber")).append("\n");
                details.append("Pag-IBIG Number: ").append(rs.getString("pagibignumber")).append("\n");
                details.append("Status: ").append(rs.getString("status")).append("\n");
                details.append("Position: ").append(rs.getString("position")).append("\n");
                details.append("Immediate Supervisor: ").append(rs.getString("immediatesupervisor")).append("\n");
                details.append("Basic Salary: ").append(rs.getDouble("basicsalary")).append("\n");
                details.append("Rice Subsidy: ").append(rs.getDouble("ricesubsidy")).append("\n");
                details.append("Phone Allowance: ").append(rs.getDouble("phoneallowance")).append("\n");
                details.append("Clothing Allowance: ").append(rs.getDouble("clothingallowance")).append("\n");
                details.append("Gross Semimonthly Rate: ").append(rs.getDouble("grosssemimonthlyrate")).append("\n");
                details.append("Hourly Rate: ").append(rs.getDouble("hourlyrate")).append("\n");
                employeeDetailsArea.setText(details.toString());
            } else {
                employeeDetailsArea.setText("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void submitLeaveRequest() {
        JDialog dialog = new JDialog(frame, "Submit Leave Request", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        JTextField startDateField = new JTextField();
        dialog.add(startDateLabel);
        dialog.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        JTextField endDateField = new JTextField();
        dialog.add(endDateLabel);
        dialog.add(endDateField);

        JLabel reasonLabel = new JLabel("Reason:");
        JTextArea reasonArea = new JTextArea();
        dialog.add(reasonLabel);
        dialog.add(reasonArea);

        JButton submitButton = new JButton("Submit");
        dialog.add(submitButton);
        submitButton.addActionListener(e -> {
            String startDateText = startDateField.getText();
            String endDateText = endDateField.getText();
            String reason = reasonArea.getText();

            // Validate dates
            try {
                java.sql.Date startDate = java.sql.Date.valueOf(startDateText);
                java.sql.Date endDate = java.sql.Date.valueOf(endDateText);

                // Insert leave request into database
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String query = "INSERT INTO leave_requests (employee_id, leave_start_date, leave_end_date, reason) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, employeeId);
                    stmt.setDate(2, startDate);
                    stmt.setDate(3, endDate);
                    stmt.setString(4, reason);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(dialog, "Leave request submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, "An error occurred while submitting the leave request.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        dialog.add(cancelButton);
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void timeIn() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO attendance (employee_id, time_in) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
            stmt.executeUpdate();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeInMessage = "Logged in at " + sdf.format(new Date());
            JOptionPane.showMessageDialog(frame, timeInMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while logging in.");
        }
    }

    private void timeOut() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE attendance SET time_out = ? WHERE employee_id = ? AND time_out IS NULL";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            stmt.setInt(2, employeeId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeOutMessage = "Logged out at " + sdf.format(new Date());
                JOptionPane.showMessageDialog(frame, timeOutMessage);
            } else {
                JOptionPane.showMessageDialog(frame, "No active session found. Please time-in first.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while logging out.");
        }
    }

    private void viewAttendance() {
        JTextArea attendanceArea = new JTextArea();
        attendanceArea.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(attendanceArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT time_in, time_out FROM attendance WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder attendanceDetails = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                Timestamp timeIn = rs.getTimestamp("time_in");
                Timestamp timeOut = rs.getTimestamp("time_out");
                attendanceDetails.append("Time In: ").append(sdf.format(timeIn));
                if (timeOut != null) {
                    attendanceDetails.append(" | Time Out: ").append(sdf.format(timeOut)).append("\n");
                } else {
                    attendanceDetails.append(" | Time Out: Not yet logged out\n");
                }
            }

            attendanceArea.setText(attendanceDetails.toString());

            JOptionPane.showMessageDialog(frame, scrollPane, "Attendance Tracker", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while retrieving attendance.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeePortal(1)); // Example with employee ID 1
    }
}
