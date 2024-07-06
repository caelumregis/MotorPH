import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminPage {

    private JFrame frame;

    public AdminPage() {
        // Create the frame
        frame = new JFrame("MotorPH Admin");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/resources/Logo.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        frame.add(backgroundPanel);
        backgroundPanel.setLayout(null);

        // Buttons
        JButton employeeDataButton = new JButton("Employee Data");
        employeeDataButton.setBounds(710, 200, 500, 100);
        backgroundPanel.add(employeeDataButton);

        JButton leaveRequestButton = new JButton("Leave Request");
        leaveRequestButton.setBounds(710, 320, 500, 100);
        backgroundPanel.add(leaveRequestButton);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(710, 440, 500, 100);
        backgroundPanel.add(createAccountButton);

        JButton attendanceTrackerButton = new JButton("Attendance Tracker");
        attendanceTrackerButton.setBounds(710, 560, 500, 100);
        backgroundPanel.add(attendanceTrackerButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(1740, 20, 150, 40);
        backgroundPanel.add(logoutButton);

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new MotorPHLogin();
        });

        employeeDataButton.addActionListener(e -> {
            frame.dispose();
            new EmployeeDataPage();
        });

        createAccountButton.addActionListener(e -> {
            new CreateAccountPage();
        });

        leaveRequestButton.addActionListener(e -> {
            viewLeaveRequests();
        });

        attendanceTrackerButton.addActionListener(e -> {
            viewAttendanceTracker();
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void viewLeaveRequests() {
        JFrame leaveRequestFrame = new JFrame("Leave Requests");
        leaveRequestFrame.setSize(800, 400);
        leaveRequestFrame.setLayout(new BorderLayout());

        // Table to display leave requests
        String[] columns = {"ID", "Employee ID", "Start Date", "End Date", "Reason", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        leaveRequestFrame.add(scrollPane, BorderLayout.CENTER);

        // Load leave requests from the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM leave_requests";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int employeeId = rs.getInt("employee_id");
                Date startDate = rs.getDate("leave_start_date");
                Date endDate = rs.getDate("leave_end_date");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                tableModel.addRow(new Object[]{id, employeeId, startDate, endDate, reason, status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // View button
        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View");
        buttonPanel.add(viewButton);
        leaveRequestFrame.add(buttonPanel, BorderLayout.SOUTH);

        viewButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int leaveRequestId = (int) tableModel.getValueAt(selectedRow, 0);
                viewLeaveRequestDetails(leaveRequestId, tableModel, selectedRow);
            } else {
                JOptionPane.showMessageDialog(leaveRequestFrame, "Please select a leave request to view.");
            }
        });

        leaveRequestFrame.setVisible(true);
    }

    private void viewLeaveRequestDetails(int leaveRequestId, DefaultTableModel tableModel, int selectedRow) {
        JFrame leaveRequestDetailsFrame = new JFrame("Leave Request Details");
        leaveRequestDetailsFrame.setSize(800, 600);
        leaveRequestDetailsFrame.setResizable(false);
        leaveRequestDetailsFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(20, 20, 100, 30);
        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(130, 20, 200, 30);
        employeeIdField.setEditable(false);
        leaveRequestDetailsFrame.add(employeeIdLabel);
        leaveRequestDetailsFrame.add(employeeIdField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 60, 100, 30);
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(130, 60, 200, 30);
        lastNameField.setEditable(false);
        leaveRequestDetailsFrame.add(lastNameLabel);
        leaveRequestDetailsFrame.add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 100, 100, 30);
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(130, 100, 200, 30);
        firstNameField.setEditable(false);
        leaveRequestDetailsFrame.add(firstNameLabel);
        leaveRequestDetailsFrame.add(firstNameField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(20, 140, 100, 30);
        JTextField positionField = new JTextField();
        positionField.setBounds(130, 140, 200, 30);
        positionField.setEditable(false);
        leaveRequestDetailsFrame.add(positionLabel);
        leaveRequestDetailsFrame.add(positionField);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(20, 180, 100, 30);
        JTextField startDateField = new JTextField();
        startDateField.setBounds(130, 180, 200, 30);
        startDateField.setEditable(false);
        leaveRequestDetailsFrame.add(startDateLabel);
        leaveRequestDetailsFrame.add(startDateField);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(20, 220, 100, 30);
        JTextField endDateField = new JTextField();
        endDateField.setBounds(130, 220, 200, 30);
        endDateField.setEditable(false);
        leaveRequestDetailsFrame.add(endDateLabel);
        leaveRequestDetailsFrame.add(endDateField);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(20, 260, 100, 30);
        JTextArea reasonArea = new JTextArea();
        reasonArea.setBounds(130, 260, 600, 80);
        reasonArea.setEditable(false);
        leaveRequestDetailsFrame.add(reasonLabel);
        leaveRequestDetailsFrame.add(reasonArea);

        // Load leave request details
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT lr.*, e.lastname, e.firstname, e.position FROM leave_requests lr " +
                    "JOIN employees e ON lr.employee_id = e.id WHERE lr.id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, leaveRequestId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employeeIdField.setText(String.valueOf(rs.getInt("employee_id")));
                lastNameField.setText(rs.getString("lastname"));
                firstNameField.setText(rs.getString("firstname"));
                positionField.setText(rs.getString("position"));
                startDateField.setText(rs.getDate("leave_start_date").toString());
                endDateField.setText(rs.getDate("leave_end_date").toString());
                reasonArea.setText(rs.getString("reason"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Approve and Reject buttons
        JButton approveButton = new JButton("Approve");
        approveButton.setBounds(270, 500, 100, 30);
        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(430, 500, 100, 30);
        leaveRequestDetailsFrame.add(approveButton);
        leaveRequestDetailsFrame.add(rejectButton);

        approveButton.addActionListener(e -> updateLeaveRequestStatus(leaveRequestId, "Approved", leaveRequestDetailsFrame, tableModel, selectedRow));
        rejectButton.addActionListener(e -> updateLeaveRequestStatus(leaveRequestId, "Rejected", leaveRequestDetailsFrame, tableModel, selectedRow));

        leaveRequestDetailsFrame.setVisible(true);
    }

    private void updateLeaveRequestStatus(int leaveRequestId, String status, JFrame leaveRequestDetailsFrame, DefaultTableModel tableModel, int selectedRow) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE leave_requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, status);
            stmt.setInt(2, leaveRequestId);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(leaveRequestDetailsFrame, "Leave request " + status.toLowerCase() + " successfully.");
            tableModel.setValueAt(status, selectedRow, 5); // Update the status in the table
            leaveRequestDetailsFrame.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(leaveRequestDetailsFrame, "An error occurred while updating the leave request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAttendanceTracker() {
        JFrame attendanceTrackerFrame = new JFrame("Attendance Tracker");
        attendanceTrackerFrame.setSize(1920, 1080);
        attendanceTrackerFrame.setLayout(new BorderLayout());

        // Table to display attendance tracker
        String[] columns = {"Employee ID", "Last Name", "First Name", "Position"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        attendanceTrackerFrame.add(scrollPane, BorderLayout.CENTER);

        // Load employee data
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id, lastname, firstname, position FROM employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("position")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // View button
        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View");
        buttonPanel.add(viewButton);
        attendanceTrackerFrame.add(buttonPanel, BorderLayout.SOUTH);

        viewButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int employeeId = (int) tableModel.getValueAt(selectedRow, 0);
                viewAttendanceDetails(employeeId);
            } else {
                JOptionPane.showMessageDialog(attendanceTrackerFrame, "Please select an employee to view attendance.");
            }
        });

        attendanceTrackerFrame.setVisible(true);
    }

    private void viewAttendanceDetails(int employeeId) {
        JFrame attendanceDetailsFrame = new JFrame("Attendance Details");
        attendanceDetailsFrame.setSize(800, 600);
        attendanceDetailsFrame.setResizable(false);
        attendanceDetailsFrame.setLayout(null);

        JTextArea attendanceDetailsArea = new JTextArea();
        attendanceDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        attendanceDetailsArea.setEditable(false);
        attendanceDetailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(attendanceDetailsArea);
        scrollPane.setBounds(20, 20, 760, 540);
        attendanceDetailsFrame.add(scrollPane);

        // Load attendance details
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT time_in, time_out FROM attendance WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder details = new StringBuilder();
            while (rs.next()) {
                details.append("Time In: ").append(rs.getTimestamp("time_in")).append("\n");
                details.append("Time Out: ").append(rs.getTimestamp("time_out")).append("\n\n");
            }

            attendanceDetailsArea.setText(details.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        attendanceDetailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminPage::new);
    }
}
