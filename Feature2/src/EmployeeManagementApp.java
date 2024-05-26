import javax.swing.*;
import java.awt.*;

public class EmployeeManagementApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private SearchEmployeePanel searchEmployeePanel;

    public EmployeeManagementApp() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2000, 1000);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        searchEmployeePanel = new SearchEmployeePanel(this);
        cardPanel.add(new MainPanel(this), "Main");
        cardPanel.add(new AddEmployeePanel(this), "AddEmployee");
        cardPanel.add(searchEmployeePanel, "SearchEmployee");

        add(cardPanel);

        cardLayout.show(cardPanel, "SearchEmployee");
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    public SearchEmployeePanel getSearchEmployeePanel() {
        return searchEmployeePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeManagementApp app = new EmployeeManagementApp();
            app.setVisible(true);
        });
    }
}
