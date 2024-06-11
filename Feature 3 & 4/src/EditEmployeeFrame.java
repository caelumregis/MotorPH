import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class EditEmployeeFrame extends JFrame {
    private final String[] employee;
    private final SearchEmployeePanel searchEmployeePanel;

    public EditEmployeeFrame(String[] employee, SearchEmployeePanel searchEmployeePanel) {
        this.employee = employee;
        this.searchEmployeePanel = searchEmployeePanel;
        setTitle("Edit Employee");
        setSize(800, 1000);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add fields for editing
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
        add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx++;
        JTextField firstNameField = new JTextField(employee[2], 20);
        add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Birthday:"), gbc);
        gbc.gridx++;
        JTextField birthdayField = new JTextField(employee[3], 20);
        add(birthdayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        JTextField addressField = new JTextField(employee[4], 20);
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx++;
        JTextField phoneNumberField = new JTextField(employee[5], 20);
        add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("SSS #:"), gbc);
        gbc.gridx++;
        JTextField sssField = new JTextField(employee[6], 20);
        add(sssField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Philhealth #:"), gbc);
        gbc.gridx++;
        JTextField philhealthField = new JTextField(employee[7], 20);
        add(philhealthField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("TIN #:"), gbc);
        gbc.gridx++;
        JTextField tinField = new JTextField(employee[8], 20);
        add(tinField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pag-ibig #:"), gbc);
        gbc.gridx++;
        JTextField pagibigField = new JTextField(employee[9], 20);
        add(pagibigField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Status:"), gbc);
        gbc.gridx++;
        JTextField statusField = new JTextField(employee[10], 20);
        add(statusField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Position:"), gbc);
        gbc.gridx++;
        JTextField positionField = new JTextField(employee[11], 20);
        add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Immediate Supervisor:"), gbc);
        gbc.gridx++;
        JTextField supervisorField = new JTextField(employee[12], 20);
        add(supervisorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Basic Salary:"), gbc);
        gbc.gridx++;
        JTextField basicSalaryField = new JTextField(employee[13], 20);
        add(basicSalaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Rice Subsidy:"), gbc);
        gbc.gridx++;
        JTextField riceSubsidyField = new JTextField(employee[14], 20);
        add(riceSubsidyField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Allowance:"), gbc);
        gbc.gridx++;
        JTextField phoneAllowanceField = new JTextField(employee[15], 20);
        add(phoneAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Clothing Allowance:"), gbc);
        gbc.gridx++;
        JTextField clothingAllowanceField = new JTextField(employee[16], 20);
        add(clothingAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Gross Semi-monthly Rate:"), gbc);
        gbc.gridx++;
        JTextField grossRateField = new JTextField(employee[17], 20);
        add(grossRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx++;
        JTextField hourlyRateField = new JTextField(employee[18], 20);
        add(hourlyRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Save the edited employee details
            employee[1] = lastNameField.getText();
            employee[2] = firstNameField.getText();
            employee[3] = birthdayField.getText();
            employee[4] = addressField.getText();
            employee[5] = phoneNumberField.getText();
            employee[6] = sssField.getText();
            employee[7] = philhealthField.getText();
            employee[8] = tinField.getText();
            employee[9] = pagibigField.getText();
            employee[10] = statusField.getText();
            employee[11] = positionField.getText();
            employee[12] = supervisorField.getText();
            employee[13] = basicSalaryField.getText();
            employee[14] = riceSubsidyField.getText();
            employee[15] = phoneAllowanceField.getText();
            employee[16] = clothingAllowanceField.getText();
            employee[17] = grossRateField.getText();
            employee[18] = hourlyRateField.getText();

            saveEmployeeToCSV(searchEmployeePanel.getEmployeeData());
            searchEmployeePanel.updateTableData(searchEmployeePanel.getEmployeeData());
            dispose();
        });
        add(saveButton, gbc);
    }

    private void saveEmployeeToCSV(List<String[]> employeeData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_data.csv"))) {
            for (String[] employee : employeeData) {
                writer.write(String.join(",", employee));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

