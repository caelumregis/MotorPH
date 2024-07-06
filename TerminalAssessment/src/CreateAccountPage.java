import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAccountPage {

    private JFrame frame;

    public CreateAccountPage() {
        // Create the frame
        frame = new JFrame("Create Account");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        Dimension fieldDimension = new Dimension(300, 40);

        JLabel employeeLabel = new JLabel("Select Employee:");
        employeeLabel.setFont(labelFont);
        employeeLabel.setBounds(100, 100, 200, 30);
        frame.add(employeeLabel);

        JComboBox<String> employeeComboBox = new JComboBox<>();
        employeeComboBox.setFont(fieldFont);
        employeeComboBox.setBounds(300, 100, 300, 40);
        frame.add(employeeComboBox);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setBounds(100, 160, 200, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(fieldFont);
        usernameField.setBounds(300, 160, 300, 40);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(100, 220, 200, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setBounds(300, 220, 300, 40);
        frame.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setBounds(100, 280, 200, 30);
        frame.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setBounds(300, 280, 300, 40);
        frame.add(confirmPasswordField);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 18));
        createAccountButton.setBounds(300, 340, 200, 50);
        frame.add(createAccountButton);

        // Load employee data into combo box
        loadEmployeeData(employeeComboBox);

        createAccountButton.addActionListener(e -> {
            String selectedEmployee = (String) employeeComboBox.getSelectedItem();
            if (selectedEmployee == null) {
                JOptionPane.showMessageDialog(frame, "Please select an employee.");
                return;
            }
            int employeeId = Integer.parseInt(selectedEmployee.split(" - ")[0]);
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.");
                return;
            }

            createAccount(employeeId, username, password);
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void loadEmployeeData(JComboBox<String> employeeComboBox) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id, lastname, firstname FROM employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String lastName = rs.getString("lastname");
                String firstName = rs.getString("firstname");
                employeeComboBox.addItem(id + " - " + lastName + ", " + firstName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAccount(int employeeId, String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (username, password, employee_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, employeeId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Account created successfully.");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to create account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while creating the account.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreateAccountPage::new);
    }
}
