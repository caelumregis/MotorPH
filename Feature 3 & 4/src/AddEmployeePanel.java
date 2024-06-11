import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class AddEmployeePanel extends JPanel {
    public AddEmployeePanel(EmployeeManagementApp app) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add fields for adding a new employee
        add(new JLabel("Employee #:"), gbc);
        gbc.gridx++;
        JTextField employeeIdField = new JTextField(20);
        add(employeeIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Last Name:"), gbc);
        gbc.gridx++;
        JTextField lastNameField = new JTextField(20);
        add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("First Name:"), gbc);
        gbc.gridx++;
        JTextField firstNameField = new JTextField(20);
        add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Birthday:"), gbc);
        gbc.gridx++;
        JTextField birthdayField = new JTextField(20);
        add(birthdayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Address:"), gbc);
        gbc.gridx++;
        JTextField addressField = new JTextField(20);
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx++;
        JTextField phoneNumberField = new JTextField(20);
        add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("SSS #:"), gbc);
        gbc.gridx++;
        JTextField sssField = new JTextField(20);
        add(sssField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Philhealth #:"), gbc);
        gbc.gridx++;
        JTextField philhealthField = new JTextField(20);
        add(philhealthField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("TIN #:"), gbc);
        gbc.gridx++;
        JTextField tinField = new JTextField(20);
        add(tinField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pag-ibig #:"), gbc);
        gbc.gridx++;
        JTextField pagibigField = new JTextField(20);
        add(pagibigField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Status:"), gbc);
        gbc.gridx++;
        JTextField statusField = new JTextField(20);
        add(statusField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Position:"), gbc);
        gbc.gridx++;
        JTextField positionField = new JTextField(20);
        add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Immediate Supervisor:"), gbc);
        gbc.gridx++;
        JTextField supervisorField = new JTextField(20);
        add(supervisorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Basic Salary:"), gbc);
        gbc.gridx++;
        JTextField basicSalaryField = new JTextField(20);
        add(basicSalaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Rice Subsidy:"), gbc);
        gbc.gridx++;
        JTextField riceSubsidyField = new JTextField(20);
        add(riceSubsidyField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Allowance:"), gbc);
        gbc.gridx++;
        JTextField phoneAllowanceField = new JTextField(20);
        add(phoneAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Clothing Allowance:"), gbc);
        gbc.gridx++;
        JTextField clothingAllowanceField = new JTextField(20);
        add(clothingAllowanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Gross Semi-monthly Rate:"), gbc);
        gbc.gridx++;
        JTextField grossRateField = new JTextField(20);
        add(grossRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx++;
        JTextField hourlyRateField = new JTextField(20);
        add(hourlyRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the new employee details to the CSV file
                String[] employee = new String[]{
                        employeeIdField.getText(),
                        lastNameField.getText(),
                        firstNameField.getText(),
                        birthdayField.getText(),
                        addressField.getText(),
                        phoneNumberField.getText(),
                        sssField.getText(),
                        philhealthField.getText(),
                        tinField.getText(),
                        pagibigField.getText(),
                        statusField.getText(),
                        positionField.getText(),
                        supervisorField.getText(),
                        basicSalaryField.getText(),
                        riceSubsidyField.getText(),
                        phoneAllowanceField.getText(),
                        clothingAllowanceField.getText(),
                        grossRateField.getText(),
                        hourlyRateField.getText()
                };
                saveEmployeeToCSV(employee);
                // Clear fields after saving
                employeeIdField.setText("");
                lastNameField.setText("");
                firstNameField.setText("");
                birthdayField.setText("");
                addressField.setText("");
                phoneNumberField.setText("");
                sssField.setText("");
                philhealthField.setText("");
                tinField.setText("");
                pagibigField.setText("");
                statusField.setText("");
                positionField.setText("");
                supervisorField.setText("");
                basicSalaryField.setText("");
                riceSubsidyField.setText("");
                phoneAllowanceField.setText("");
                clothingAllowanceField.setText("");
                grossRateField.setText("");
                hourlyRateField.setText("");
                // Switch back to main panel
                app.showCard("SearchEmployee");
            }
        });
        add(saveButton, gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> app.showCard("SearchEmployee"));
        add(backButton, gbc);
    }

    private void saveEmployeeToCSV(String[] employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_data.csv", true))) {
            writer.write(String.join(",", employee));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
