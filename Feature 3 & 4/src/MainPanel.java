import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    public MainPanel(EmployeeManagementApp app) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> app.showCard("AddEmployee"));

        add(addButton, gbc);
    }
}
