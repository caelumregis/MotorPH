import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class SearchEmployeePanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable employeeTable;
    private JTextField searchField;
    private List<String[]> employeeData;
    private EmployeeManagementApp app;

    public SearchEmployeePanel(EmployeeManagementApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Employee ID:"));
        searchField = new JTextField(20);
        topPanel.add(searchField);

        JButton viewButton = new JButton("View Details");
        viewButton.addActionListener(e -> openViewEmployeeFrame());
        topPanel.add(viewButton);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> openEditEmployeeFrame());
        topPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedEmployee());
        topPanel.add(deleteButton);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> app.showCard("AddEmployee"));
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        loadEmployeeData();

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterEmployeeData(searchField.getText());
            }
        });
    }

    private void loadEmployeeData() {
        employeeData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("MotorPH Employee Data - Employee Details.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employeeData.add(parseCsvLine(line));
            }
            updateTableData(employeeData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean insideQuote = false;
        for (char ch : line.toCharArray()) {
            if (ch == ',' && !insideQuote) {
                values.add(sb.toString().trim());
                sb.setLength(0);
            } else if (ch == '"') {
                insideQuote = !insideQuote;
            } else {
                sb.append(ch);
            }
        }
        values.add(sb.toString().trim());
        return values.toArray(new String[0]);
    }

    public void updateTableData(List<String[]> data) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Employee #");
        columnNames.add("Last Name");
        columnNames.add("First Name");
        columnNames.add("Birthday");
        columnNames.add("Address");
        columnNames.add("Phone Number");
        columnNames.add("SSS #");
        columnNames.add("Philhealth #");
        columnNames.add("TIN #");
        columnNames.add("Pag-ibig #");
        columnNames.add("Status");
        columnNames.add("Position");
        columnNames.add("Immediate Supervisor");
        columnNames.add("Basic Salary");
        columnNames.add("Rice Subsidy");
        columnNames.add("Phone Allowance");
        columnNames.add("Clothing Allowance");
        columnNames.add("Gross Semi-monthly Rate");
        columnNames.add("Hourly Rate");

        Vector<Vector<Object>> tableData = new Vector<>();
        for (String[] row : data) {
            Vector<Object> rowData = new Vector<>();
            for (String cell : row) {
                rowData.add(cell);
            }
            tableData.add(rowData);
        }

        tableModel.setDataVector(tableData, columnNames);
    }

    private void filterEmployeeData(String query) {
        if (query.isEmpty()) {
            updateTableData(employeeData);
            return;
        }

        Vector<Vector<Object>> filteredData = new Vector<>();
        for (String[] row : employeeData) {
            if (row[0].contains(query)) {
                Vector<Object> rowData = new Vector<>();
                for (String cell : row) {
                    rowData.add(cell);
                }
                filteredData.add(rowData);
            }
        }

        tableModel.setDataVector(filteredData, tableModel.getDataVector());
    }

    private void openViewEmployeeFrame() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String[] employee = new String[employeeTable.getColumnCount()];
            for (int i = 0; i < employeeTable.getColumnCount(); i++) {
                employee[i] = (String) employeeTable.getValueAt(selectedRow, i);
            }
            new ViewEmployeeFrame(employee, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openEditEmployeeFrame() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String[] employee = new String[employeeTable.getColumnCount()];
            for (int i = 0; i < employeeTable.getColumnCount(); i++) {
                employee[i] = (String) employeeTable.getValueAt(selectedRow, i);
            }
            new EditEmployeeFrame(employee, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
                employeeData.removeIf(row -> row[0].equals(employeeId));
                saveAllEmployeesToCSV();
                updateTableData(employeeData);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAllEmployeesToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_data.csv"))) {
            for (String[] employee : employeeData) {
                writer.write(String.join(",", employee));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getEmployeeData() {
        return employeeData;
    }
}
