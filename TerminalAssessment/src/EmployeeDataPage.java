import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeDataPage {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public EmployeeDataPage() {
        // Create the frame
        frame = new JFrame("Employee Data");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Create the table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel.addColumn("ID");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Birthday");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("SSN Number");
        tableModel.addColumn("PhilHealth Number");
        tableModel.addColumn("TIN Number");
        tableModel.addColumn("Pag-IBIG Number");
        tableModel.addColumn("Status");
        tableModel.addColumn("Position");
        tableModel.addColumn("Immediate Supervisor");
        tableModel.addColumn("Basic Salary");
        tableModel.addColumn("Rice Subsidy");
        tableModel.addColumn("Phone Allowance");
        tableModel.addColumn("Clothing Allowance");
        tableModel.addColumn("Gross Semimonthly Rate");
        tableModel.addColumn("Hourly Rate");

        // Load employee data
        loadEmployeeData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 90, 1880, 660);
        frame.add(scrollPane);

        // Create search field
        searchField = new JTextField("Search Employee ID");
        searchField.setBounds(20, 20, 200, 30);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search Employee ID")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search Employee ID");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterTable(searchField.getText());
            }
        });
        frame.add(searchField);

        // Create buttons
        JButton viewButton = new JButton("View");
        viewButton.setBounds(230, 20, 100, 30);
        frame.add(viewButton);
        viewButton.addActionListener(e -> viewEmployee());

        JButton editButton = new JButton("Edit");
        editButton.setBounds(340, 20, 100, 30);
        frame.add(editButton);
        editButton.addActionListener(e -> editEmployee());

        JButton addButton = new JButton("Add");
        addButton.setBounds(450, 20, 100, 30);
        frame.add(addButton);
        addButton.addActionListener(e -> addEmployee());

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(560, 20, 100, 30);
        frame.add(deleteButton);
        deleteButton.addActionListener(e -> deleteEmployee());

        JButton backButton = new JButton("Back");
        backButton.setBounds(1770, 20, 100, 30);
        frame.add(backButton);
        backButton.addActionListener(e -> {
            frame.dispose();
            new AdminPage(); // Assuming AdminPage class exists and is the page to go back to
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void loadEmployeeData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getDate("birthday"),
                        rs.getString("address"),
                        rs.getString("phonenumber"),
                        rs.getString("sssnumber"),
                        rs.getString("philhealthnumber"),
                        rs.getString("tinnumber"),
                        rs.getString("pagibignumber"),
                        rs.getString("status"),
                        rs.getString("position"),
                        rs.getString("immediatesupervisor"),
                        rs.getDouble("basicsalary"),
                        rs.getDouble("ricesubsidy"),
                        rs.getDouble("phoneallowance"),
                        rs.getDouble("clothingallowance"),
                        rs.getDouble("grosssemimonthlyrate"),
                        rs.getDouble("hourlyrate")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterTable(String query) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        if (query.trim().length() == 0 || query.equals("Search Employee ID")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    private void viewEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an employee to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder details = new StringBuilder();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            details.append(tableModel.getColumnName(i)).append(": ").append(tableModel.getValueAt(selectedRow, i)).append("\n");
        }

        JOptionPane.showMessageDialog(frame, details.toString(), "View Employee", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an employee to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the edit employee dialog
        JDialog dialog = new JDialog(frame, "Edit Employee", true);
        dialog.setSize(400, 600);
        dialog.setLayout(new GridLayout(20, 2));

        JTextField[] fields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            JLabel label = new JLabel(tableModel.getColumnName(i) + ":");
            JTextField textField = new JTextField(tableModel.getValueAt(selectedRow, i).toString());
            if (i == 0) {
                textField.setEditable(false); // ID field should not be editable
            }
            fields[i] = textField;
            dialog.add(label);
            dialog.add(textField);
        }

        JButton okButton = new JButton("OK");
        dialog.add(okButton);
        okButton.addActionListener(e -> {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "UPDATE employees SET lastname=?, firstname=?, birthday=?, address=?, phonenumber=?, sssnumber=?, philhealthnumber=?, tinnumber=?, pagibignumber=?, status=?, position=?, immediatesupervisor=?, basicsalary=?, ricesubsidy=?, phoneallowance=?, clothingallowance=?, grosssemimonthlyrate=?, hourlyrate=? WHERE id=?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, fields[1].getText());
                stmt.setString(2, fields[2].getText());

                // Parse and validate the birthday field
                String birthdayText = fields[3].getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date parsedDate = dateFormat.parse(birthdayText);
                    stmt.setDate(3, new Date(parsedDate.getTime()));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                stmt.setString(4, fields[4].getText());
                stmt.setString(5, fields[5].getText());
                stmt.setString(6, fields[6].getText());
                stmt.setString(7, fields[7].getText());
                stmt.setString(8, fields[8].getText());
                stmt.setString(9, fields[9].getText());
                stmt.setString(10, fields[10].getText());
                stmt.setString(11, fields[11].getText());
                stmt.setString(12, fields[12].getText());
                stmt.setString(13, fields[13].getText());
                stmt.setDouble(14, Double.parseDouble(fields[14].getText()));
                stmt.setDouble(15, Double.parseDouble(fields[15].getText()));
                stmt.setDouble(16, Double.parseDouble(fields[16].getText()));
                stmt.setDouble(17, Double.parseDouble(fields[17].getText()));
                stmt.setDouble(18, Double.parseDouble(fields[18].getText()));
                stmt.setInt(19, Integer.parseInt(fields[0].getText()));

                stmt.executeUpdate();
                dialog.dispose();
                tableModel.setValueAt(fields[1].getText(), selectedRow, 1);
                tableModel.setValueAt(fields[2].getText(), selectedRow, 2);
                tableModel.setValueAt(Date.valueOf(birthdayText), selectedRow, 3);
                tableModel.setValueAt(fields[4].getText(), selectedRow, 4);
                tableModel.setValueAt(fields[5].getText(), selectedRow, 5);
                tableModel.setValueAt(fields[6].getText(), selectedRow, 6);
                tableModel.setValueAt(fields[7].getText(), selectedRow, 7);
                tableModel.setValueAt(fields[8].getText(), selectedRow, 8);
                tableModel.setValueAt(fields[9].getText(), selectedRow, 9);
                tableModel.setValueAt(fields[10].getText(), selectedRow, 10);
                tableModel.setValueAt(fields[11].getText(), selectedRow, 11);
                tableModel.setValueAt(fields[12].getText(), selectedRow, 12);
                tableModel.setValueAt(Double.parseDouble(fields[13].getText()), selectedRow, 13);
                tableModel.setValueAt(Double.parseDouble(fields[14].getText()), selectedRow, 14);
                tableModel.setValueAt(Double.parseDouble(fields[15].getText()), selectedRow, 15);
                tableModel.setValueAt(Double.parseDouble(fields[16].getText()), selectedRow, 16);
                tableModel.setValueAt(Double.parseDouble(fields[17].getText()), selectedRow, 17);
                tableModel.setValueAt(Double.parseDouble(fields[18].getText()), selectedRow, 18);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        dialog.add(cancelButton);
        cancelButton.addActionListener(e1 -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an employee to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the selected employee?", "Delete Employee", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int employeeId = (int) tableModel.getValueAt(selectedRow, 0);
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM employees WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            tableModel.removeRow(selectedRow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addEmployee() {
        // Get the last employee ID
        int nextId = getLastEmployeeId() + 1;

        // Create the add employee dialog
        JDialog dialog = new JDialog(frame, "Add Employee", true);
        dialog.setSize(400, 600);
        dialog.setLayout(new GridLayout(20, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(String.valueOf(nextId));
        idField.setEditable(false);
        dialog.add(idLabel);
        dialog.add(idField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        dialog.add(lastNameLabel);
        dialog.add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        dialog.add(firstNameLabel);
        dialog.add(firstNameField);

        JLabel birthdayLabel = new JLabel("Birthday (YYYY-MM-DD):");
        JTextField birthdayField = new JTextField();
        dialog.add(birthdayLabel);
        dialog.add(birthdayField);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        dialog.add(addressLabel);
        dialog.add(addressField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField();
        dialog.add(phoneNumberLabel);
        dialog.add(phoneNumberField);

        JLabel ssnNumberLabel = new JLabel("SSN Number:");
        JTextField ssnNumberField = new JTextField();
        dialog.add(ssnNumberLabel);
        dialog.add(ssnNumberField);

        JLabel philHealthNumberLabel = new JLabel("PhilHealth Number:");
        JTextField philHealthNumberField = new JTextField();
        dialog.add(philHealthNumberLabel);
        dialog.add(philHealthNumberField);

        JLabel tinNumberLabel = new JLabel("TIN Number:");
        JTextField tinNumberField = new JTextField();
        dialog.add(tinNumberLabel);
        dialog.add(tinNumberField);

        JLabel pagIbigNumberLabel = new JLabel("Pag-IBIG Number:");
        JTextField pagIbigNumberField = new JTextField();
        dialog.add(pagIbigNumberLabel);
        dialog.add(pagIbigNumberField);

        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField();
        dialog.add(statusLabel);
        dialog.add(statusField);

        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField();
        dialog.add(positionLabel);
        dialog.add(positionField);

        JLabel immediateSupervisorLabel = new JLabel("Immediate Supervisor:");
        JTextField immediateSupervisorField = new JTextField();
        dialog.add(immediateSupervisorLabel);
        dialog.add(immediateSupervisorField);

        JLabel basicSalaryLabel = new JLabel("Basic Salary:");
        JTextField basicSalaryField = new JTextField();
        dialog.add(basicSalaryLabel);
        dialog.add(basicSalaryField);

        JLabel riceSubsidyLabel = new JLabel("Rice Subsidy:");
        JTextField riceSubsidyField = new JTextField();
        dialog.add(riceSubsidyLabel);
        dialog.add(riceSubsidyField);

        JLabel phoneAllowanceLabel = new JLabel("Phone Allowance:");
        JTextField phoneAllowanceField = new JTextField();
        dialog.add(phoneAllowanceLabel);
        dialog.add(phoneAllowanceField);

        JLabel clothingAllowanceLabel = new JLabel("Clothing Allowance:");
        JTextField clothingAllowanceField = new JTextField();
        dialog.add(clothingAllowanceLabel);
        dialog.add(clothingAllowanceField);

        JLabel grossSemimonthlyRateLabel = new JLabel("Gross Semimonthly Rate:");
        JTextField grossSemimonthlyRateField = new JTextField();
        dialog.add(grossSemimonthlyRateLabel);
        dialog.add(grossSemimonthlyRateField);

        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
        JTextField hourlyRateField = new JTextField();
        dialog.add(hourlyRateLabel);
        dialog.add(hourlyRateField);

        JButton okButton = new JButton("OK");
        dialog.add(okButton);
        okButton.addActionListener(e -> {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO employees (id, lastname, firstname, birthday, address, phonenumber, sssnumber, philhealthnumber, tinnumber, pagibignumber, status, position, immediatesupervisor, basicsalary, ricesubsidy, phoneallowance, clothingallowance, grosssemimonthlyrate, hourlyrate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, nextId);
                stmt.setString(2, lastNameField.getText());
                stmt.setString(3, firstNameField.getText());

                // Parse and validate the birthday field
                String birthdayText = birthdayField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date parsedDate = dateFormat.parse(birthdayText);
                    stmt.setDate(4, new Date(parsedDate.getTime()));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                stmt.setString(5, addressField.getText());
                stmt.setString(6, phoneNumberField.getText());
                stmt.setString(7, ssnNumberField.getText());
                stmt.setString(8, philHealthNumberField.getText());
                stmt.setString(9, tinNumberField.getText());
                stmt.setString(10, pagIbigNumberField.getText());
                stmt.setString(11, statusField.getText());
                stmt.setString(12, positionField.getText());
                stmt.setString(13, immediateSupervisorField.getText());
                stmt.setDouble(14, Double.parseDouble(basicSalaryField.getText()));
                stmt.setDouble(15, Double.parseDouble(riceSubsidyField.getText()));
                stmt.setDouble(16, Double.parseDouble(phoneAllowanceField.getText()));
                stmt.setDouble(17, Double.parseDouble(clothingAllowanceField.getText()));
                stmt.setDouble(18, Double.parseDouble(grossSemimonthlyRateField.getText()));
                stmt.setDouble(19, Double.parseDouble(hourlyRateField.getText()));
                stmt.executeUpdate();
                dialog.dispose();
                tableModel.addRow(new Object[]{
                        nextId,
                        lastNameField.getText(),
                        firstNameField.getText(),
                        Date.valueOf(birthdayText),
                        addressField.getText(),
                        phoneNumberField.getText(),
                        ssnNumberField.getText(),
                        philHealthNumberField.getText(),
                        tinNumberField.getText(),
                        pagIbigNumberField.getText(),
                        statusField.getText(),
                        positionField.getText(),
                        immediateSupervisorField.getText(),
                        Double.parseDouble(basicSalaryField.getText()),
                        Double.parseDouble(riceSubsidyField.getText()),
                        Double.parseDouble(phoneAllowanceField.getText()),
                        Double.parseDouble(clothingAllowanceField.getText()),
                        Double.parseDouble(grossSemimonthlyRateField.getText()),
                        Double.parseDouble(hourlyRateField.getText())
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        dialog.add(cancelButton);
        cancelButton.addActionListener(e1 -> dialog.dispose());

        dialog.setVisible(true);
    }

    private int getLastEmployeeId() {
        int lastId = 0;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT MAX(id) AS last_id FROM employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastId = rs.getInt("last_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeDataPage::new);
    }
}
