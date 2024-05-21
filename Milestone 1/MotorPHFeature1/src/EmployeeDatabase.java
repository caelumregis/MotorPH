import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDatabase {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private DefaultTableModel employeeTableModel;
    private JTable deleteEmployeeTable;

    public EmployeeDatabase() {
        // Create and set up the window.
        frame = new JFrame("MotorPH");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false); // Disable resizing (and maximizing) the window

        // Initialize CardLayout and JPanel for holding cards
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add main panel to the card layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JButton employeeListButton = new JButton("Employee List");
        JButton addButton = new JButton("Add Employee");
        JButton deleteButton = new JButton("Delete Employee");

        employeeListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        employeeListButton.setBounds(425, 150, 150, 30);
        addButton.setBounds(425, 190, 150, 30);
        deleteButton.setBounds(425, 230, 150, 30);

        mainPanel.add(employeeListButton);
        mainPanel.add(addButton);
        mainPanel.add(deleteButton);

        cardPanel.add(mainPanel, "main");
        frame.getContentPane().add(cardPanel);



        //Create a new panel for employee list functionality
        // Initialize employee table model
        String[] columnNames = {"Employee ID", "Last Name", "First Name", "Birthday", "Address", "Phone Number", "SSS #", "Philhealth #", "TIN #", "Pag-ibig #", "Status", "Position", "Immediate Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Gross Semi-monthly Rate", "Hourly Rate"};
        employeeTableModel = new DefaultTableModel(columnNames, 0);

        // Create a table with the table model
        JTable employeeTable = new JTable(employeeTableModel);

        // Make the table read-only
        employeeTable.setDefaultEditor(Object.class, null);

        // Set preferred column widths for better visibility
        employeeTable.getColumnModel().getColumn(0).setPreferredWidth(250); // Employee ID
        employeeTable.getColumnModel().getColumn(1).setPreferredWidth(500); // Last Name
        employeeTable.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(80); // Birthday
        employeeTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Phone Number
        employeeTable.getColumnModel().getColumn(5).setPreferredWidth(150); // SSS #
        employeeTable.getColumnModel().getColumn(6).setPreferredWidth(150); // Philhealth #
        employeeTable.getColumnModel().getColumn(7).setPreferredWidth(150); // TIN #
        employeeTable.getColumnModel().getColumn(8).setPreferredWidth(150); // Pag-ibig #
        employeeTable.getColumnModel().getColumn(9).setPreferredWidth(150); // Status
        employeeTable.getColumnModel().getColumn(10).setPreferredWidth(150); // Position
        employeeTable.getColumnModel().getColumn(11).setPreferredWidth(150); // Immediate Supervisor
        employeeTable.getColumnModel().getColumn(12).setPreferredWidth(150); // Basic Salary
        employeeTable.getColumnModel().getColumn(13).setPreferredWidth(150); // Rice Subsidy
        employeeTable.getColumnModel().getColumn(14).setPreferredWidth(150); // Phone Allowance
        employeeTable.getColumnModel().getColumn(15).setPreferredWidth(150); // Clothing Allowance
        employeeTable.getColumnModel().getColumn(16).setPreferredWidth(150); // Gross Semi-monthly Rate
        employeeTable.getColumnModel().getColumn(17).setPreferredWidth(150); // Hourly Rate
        // ... set preferred widths for other columns as needed ...

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(40, 70, 900, 350);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add the table to the employee list functionality
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.add(scrollPane);

        JLabel employeelistLabel = new JLabel("Search Employee Number:");
        employeelistLabel.setBounds(50, 20, 150, 30);
        searchPanel.add(employeelistLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(225, 20, 200, 30);
        searchPanel.add(searchField);

        JButton searchButtonOnSearchPanel = new JButton("Search");
        searchButtonOnSearchPanel.setBounds(450, 20, 150, 30);
        searchButtonOnSearchPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        searchPanel.add(searchButtonOnSearchPanel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(871, 430, 70, 20);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }
        });
        searchPanel.add(backButton);

        // Add the employee list panel to the card layout
        cardPanel.add(searchPanel, "search");




        // Create a new panel for add employee functionality
        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);

        // Create labels and text fields for each category
        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(225, 20, 100, 30);
        addPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(335, 20, 200, 30);
        addPanel.add(idField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(225, 60, 100, 30);
        addPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(335, 60, 200, 30);
        addPanel.add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(225, 100, 100, 30);
        addPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(335, 100, 200, 30);
        addPanel.add(firstNameField);

        JLabel birthdayLabel = new JLabel("Birthday:");
        birthdayLabel.setBounds(225, 140, 100, 30);
        addPanel.add(birthdayLabel);

        JTextField birthdayField = new JTextField();
        birthdayField.setBounds(335, 140, 200, 30);
        addPanel.add(birthdayField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(225, 180, 100, 30);
        addPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(335, 180, 200, 30);
        addPanel.add(addressField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(225, 220, 100, 30);
        addPanel.add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(335, 220, 200, 30);
        addPanel.add(phoneNumberField);

        JLabel sssNumberLabel = new JLabel("SSS #:");
        sssNumberLabel.setBounds(225, 260, 100, 30);
        addPanel.add(sssNumberLabel);

        JTextField sssNumberField = new JTextField();
        sssNumberField.setBounds(335, 260, 200, 30);
        addPanel.add(sssNumberField);

        JLabel philhealthNumberLabel = new JLabel("Philhealth #:");
        philhealthNumberLabel.setBounds(225, 300, 100, 30);
        addPanel.add(philhealthNumberLabel);

        JTextField philhealthNumberField = new JTextField();
        philhealthNumberField.setBounds(335, 300, 200, 30);
        addPanel.add(philhealthNumberField);

        JLabel tinNumberLabel = new JLabel("TIN #:");
        tinNumberLabel.setBounds(225, 340, 100, 30);
        addPanel.add(tinNumberLabel);

        JTextField tinNumberField = new JTextField();
        tinNumberField.setBounds(335, 340, 200, 30);
        addPanel.add(tinNumberField);

        JLabel pagibigNumberLabel = new JLabel("Pag-ibig #:");
        pagibigNumberLabel.setBounds(225, 380, 100, 30);
        addPanel.add(pagibigNumberLabel);

        JTextField pagibigNumberField = new JTextField();
        pagibigNumberField.setBounds(335, 380, 200, 30);
        addPanel.add(pagibigNumberField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(225, 420, 100, 30);
        addPanel.add(statusLabel);

        JTextField statusField = new JTextField();
        statusField.setBounds(335, 420, 200, 30);
        addPanel.add(statusField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(225, 460, 100, 30);
        addPanel.add(positionLabel);

        JTextField positionField = new JTextField();
        positionField.setBounds(335, 460, 200, 30);
        addPanel.add(positionField);

        JLabel immediateSupervisorLabel = new JLabel("Immediate Supervisor:");
        immediateSupervisorLabel.setBounds(225, 500, 100, 30);
        addPanel.add(immediateSupervisorLabel);

        JTextField immediateSupervisorField = new JTextField();
        immediateSupervisorField.setBounds(335, 500, 200, 30);
        addPanel.add(immediateSupervisorField);

        JLabel basicSalaryLabel = new JLabel("Basic Salary:");
        basicSalaryLabel.setBounds(225, 540, 100, 30);
        addPanel.add(basicSalaryLabel);

        JTextField basicSalaryField = new JTextField();
        basicSalaryField.setBounds(335, 540, 200, 30);
        addPanel.add(basicSalaryField);

        JLabel riceSubsidyLabel = new JLabel("Rice Subsidy:");
        riceSubsidyLabel.setBounds(225, 580, 100, 30);
        addPanel.add(riceSubsidyLabel);

        JTextField riceSubsidyField = new JTextField();
        riceSubsidyField.setBounds(335, 580, 200, 30);
        addPanel.add(riceSubsidyField);

        JLabel phoneAllowanceLabel = new JLabel("Phone Allowance:");
        phoneAllowanceLabel.setBounds(225, 620, 100, 30);
        addPanel.add(phoneAllowanceLabel);

        JTextField phoneAllowanceField = new JTextField();
        phoneAllowanceField.setBounds(335, 620, 200, 30);
        addPanel.add(phoneAllowanceField);

        JLabel clothingAllowanceLabel = new JLabel("Clothing Allowance:");
        clothingAllowanceLabel.setBounds(225, 660, 100, 30);
        addPanel.add(clothingAllowanceLabel);

        JTextField clothingAllowanceField = new JTextField();
        clothingAllowanceField.setBounds(335, 660, 200, 30);
        addPanel.add(clothingAllowanceField);

        JLabel grossSemiMonthlyRateLabel = new JLabel("Gross Semi-monthly Rate:");
        grossSemiMonthlyRateLabel.setBounds(225, 700, 100, 30);
        addPanel.add(grossSemiMonthlyRateLabel);

        JTextField grossSemiMonthlyRateField = new JTextField();
        grossSemiMonthlyRateField.setBounds(335, 700, 200, 30);
        addPanel.add(grossSemiMonthlyRateField);

        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
        hourlyRateLabel.setBounds(225, 740, 100, 30);
        addPanel.add(hourlyRateLabel);

        JTextField hourlyRateField = new JTextField();
        hourlyRateField.setBounds(335, 740, 200, 30);
        addPanel.add(hourlyRateField);

        JButton addButtonOnAddPanel = new JButton("Add");
        addButtonOnAddPanel.setBounds(600, 175, 150, 30);
        addButtonOnAddPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployeeToTable(idField.getText(), lastNameField.getText(), firstNameField.getText(), birthdayField.getText(), addressField.getText(), phoneNumberField.getText(),
                        sssNumberField.getText(), philhealthNumberField.getText(), tinNumberField.getText(), pagibigNumberField.getText(), statusField.getText(),
                        positionField.getText(), immediateSupervisorField.getText(), basicSalaryField.getText(), riceSubsidyField.getText(), phoneAllowanceField.getText(),
                        clothingAllowanceField.getText(), grossSemiMonthlyRateField.getText(), hourlyRateField.getText());
                idField.setText("");
                lastNameField.setText("");
                firstNameField.setText("");
                birthdayField.setText("");
                addressField.setText("");
                phoneNumberField.setText("");
                sssNumberField.setText("");
                philhealthNumberField.setText("");
                tinNumberField.setText("");
                pagibigNumberField.setText("");
                statusField.setText("");
                positionField.setText("");
                immediateSupervisorField.setText("");
                basicSalaryField.setText("");
                riceSubsidyField.setText("");
                phoneAllowanceField.setText("");
                clothingAllowanceField.setText("");
                grossSemiMonthlyRateField.setText("");
                hourlyRateField.setText("");
                cardLayout.show(cardPanel, "main");
            }
        });
        addPanel.add(addButtonOnAddPanel);

        JButton backButtonOnAddPanel = new JButton("Back");
        backButtonOnAddPanel.setBounds(600, 225, 150, 30);
        backButtonOnAddPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"main");
            }
        });
        addPanel.add(backButtonOnAddPanel);

        // Add the add panel to the card layout
        cardPanel.add(addPanel, "add");




        // Create a new panel for delete employee functionality
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(null);

        JLabel deleteLabel = new JLabel("Enter Employee ID to DELETE:");
        deleteLabel.setBounds(50, 20, 175, 30);
        deletePanel.add(deleteLabel);

        JTextField deleteField = new JTextField();
        deleteField.setBounds(225, 20, 200, 30);
        deletePanel.add(deleteField);

        JButton deleteButtonOnDeletePanel = new JButton("Delete");
        deleteButtonOnDeletePanel.setBounds(450, 20, 150, 30);
        deleteButtonOnDeletePanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowToDelete = -1;
                for (int i = 0; i < employeeTableModel.getRowCount(); i++) {
                    if (employeeTableModel.getValueAt(i, 0).equals(deleteField.getText())) {
                        rowToDelete = i;
                        break;
                    }
                }
                if (rowToDelete != -1) {
                    employeeTableModel.removeRow(rowToDelete);
                    deleteField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee not found.");
                }
            }
        });
        deletePanel.add(deleteButtonOnDeletePanel);

        // Create a table with the table model for deleting employee
        deleteEmployeeTable = new JTable(employeeTableModel);

        // Make the table read-only
        deleteEmployeeTable.setDefaultEditor(Object.class, null);

        // Create a scroll pane and add the table to it
        JScrollPane deleteEmployeeScrollPane = new JScrollPane(deleteEmployeeTable);
        deleteEmployeeScrollPane.setBounds(40, 70, 900, 350);
        deleteEmployeeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        deleteEmployeeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the delete panel
        deletePanel.add(deleteEmployeeScrollPane);

        // Back button
        JButton backButtonOnDeletePanel = new JButton("Back");
        backButtonOnDeletePanel.setBounds(871, 430, 70, 20);
        backButtonOnDeletePanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }
        });
        deletePanel.add(backButtonOnDeletePanel);

        // Add the delete panel to the card layout
        cardPanel.add(deletePanel, "delete");

        // Display the window.
        frame.setVisible(true);
    }



    private void search() {
        cardLayout.show(cardPanel, "search");
    }

    private void addEmployee() {
        cardLayout.show(cardPanel, "add");
    }

    private void deleteEmployee() {
        cardLayout.show(cardPanel, "delete");
    }

    private void addEmployeeToTable(String id, String lastName, String firstName, String birthday, String address, String phoneNumber,
                                    String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber, String status,
                                    String position, String immediateSupervisor, String basicSalary, String riceSubsidy, String phoneAllowance,
                                    String clothingAllowance, String grossSemiMonthlyRate, String hourlyRate) {
        Object[] row = {id, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagibigNumber, status,
                position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate};
        employeeTableModel.addRow(row);
    }


    public static void main(String[] args) {
        new EmployeeDatabase();
    }
}