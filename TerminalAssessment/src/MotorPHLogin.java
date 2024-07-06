import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotorPHLogin {

    public MotorPHLogin() {
        // Create the frame
        JFrame frame = new JFrame("MotorPH Login");
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

        // Login box panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(710, 390, 500, 300); // Centered position
        loginPanel.setBackground(new Color(255, 255, 255, 200));
        loginPanel.setLayout(null);
        backgroundPanel.add(loginPanel);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        usernameLabel.setBounds(50, 50, 150, 40); // Increased size
        loginPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        usernameField.setBounds(200, 50, 250, 40); // Increased size
        loginPanel.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        passwordLabel.setBounds(50, 120, 150, 40); // Increased size
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        passwordField.setBounds(200, 120, 250, 40); // Increased size
        loginPanel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Increased font size
        loginButton.setBounds(200, 200, 100, 40);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                int employeeId = validateLogin(username, password);
                if (employeeId > 0) {
                    frame.dispose();
                    if (employeeId == 6) { // Assuming employee ID 6 is the admin
                        new AdminPage();
                    } else {
                        new EmployeePortal(employeeId);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again.");
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private int validateLogin(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT employee_id FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("employee_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        // Use Event Dispatch Thread for GUI operations
        SwingUtilities.invokeLater(MotorPHLogin::new);
    }
}
