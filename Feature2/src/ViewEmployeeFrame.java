import javax.swing.*;
import java.awt.*;

class ViewEmployeeFrame extends JFrame {
    private final String[] employee;
    private final SearchEmployeePanel searchEmployeePanel;

    public ViewEmployeeFrame(String[] employee, SearchEmployeePanel searchEmployeePanel) {
        this.employee = employee;
        this.searchEmployeePanel = searchEmployeePanel;
        setTitle("View Employee");
        setSize(1000, 1000);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add fields for viewing
        add(new JLabel("Employee #:"), gbc);
        gbc.gridx++;
        JTextField employeeIdField = new JTextField(employee[0], 20);
        employeeIdField.setEditable(false);
        add(employeeIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Last Name:"), gbc);
        gbc.gridx++;
        JTextField lastNameField = new JTextField(employee[1], 20);
        lastNameField.setEditable(false);
        add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx++;
        JTextField firstNameField = new JTextField(employee[2], 20);
        firstNameField.setEditable(false);
        add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Birthday:"), gbc);
        gbc.gridx++;
        JTextField birthdayField = new JTextField(employee[3], 20);
        birthdayField.setEditable(false);
        add(birthdayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        JTextField addressField = new JTextField(employee[4], 20);
        addressField.setEditable(false);
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx++;
        JTextField phoneNumberField = new JTextField(employee[5], 20);
        phoneNumberField.setEditable(false);
        add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("SSS #:"), gbc);
        gbc.gridx++;
        JTextField sssField = new JTextField(employee[6], 20);
        sssField.setEditable(false);
        add(sssField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Philhealth #:"), gbc);
        gbc.gridx++;
        JTextField philhealthField = new JTextField(employee[7], 20);
        philhealthField.setEditable(false);
        add(philhealthField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("TIN #:"), gbc);
        gbc.gridx++;
        JTextField tinField = new JTextField(employee[8], 20);
        tinField.setEditable(false);
        add(tinField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pag-ibig #:"), gbc);
        gbc.gridx++;
        JTextField pagibigField = new JTextField(employee[9], 20);
        pagibigField.setEditable(false);
        add(pagibigField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Status:"), gbc);
        gbc.gridx++;
        JTextField statusField = new JTextField(employee[10], 20);
        statusField.setEditable(false);
        add(statusField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Position:"), gbc);
        gbc.gridx++;
        JTextField positionField = new JTextField(employee[11], 20);
        positionField.setEditable(false);
        add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Immediate Supervisor:"), gbc);
        gbc.gridx++;
        JTextField supervisorField = new JTextField(employee[12], 20);
        supervisorField.setEditable(false);
        add(supervisorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Basic Salary:"), gbc);
        gbc.gridx++;
        JTextField basicSalaryField = new JTextField(employee[13], 20);
        basicSalaryField.setEditable(false);
        add(basicSalaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Rice Subsidy:"), gbc);
        gbc.gridx++;
        JTextField riceSubsidyField = new JTextField(employee[14], 20);
        riceSubsidyField.setEditable(false);
        add(riceSubsidyField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Allowance:"), gbc);
        gbc.gridx++;
        JTextField phoneAllowanceField = new JTextField(employee[15], 20);
        phoneAllowanceField.setEditable(false);
        add(phoneAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Clothing Allowance:"), gbc);
        gbc.gridx++;
        JTextField clothingAllowanceField = new JTextField(employee[16], 20);
        clothingAllowanceField.setEditable(false);
        add(clothingAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Gross Semi-monthly Rate:"), gbc);
        gbc.gridx++;
        JTextField grossRateField = new JTextField(employee[17], 20);
        grossRateField.setEditable(false);
        add(grossRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx++;
        JTextField hourlyRateField = new JTextField(employee[18], 20);
        hourlyRateField.setEditable(false);
        add(hourlyRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton computeButton = new JButton("Compute Salary");
        computeButton.addActionListener(e -> computeSalary());
        add(computeButton, gbc);
    }

    private void computeSalary() {
        String month = JOptionPane.showInputDialog(this, "Enter the month to compute salary:", "Compute Salary", JOptionPane.PLAIN_MESSAGE);
        if (month != null && !month.trim().isEmpty()) {
            // Logic to compute salary for the selected month
            // This could involve calculations based on attendance, hourly rate, etc.
            JOptionPane.showMessageDialog(this, "Salary computed for month: " + month, "Salary Computed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

