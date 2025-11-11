package GUI;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sqli.ModuleSQLiPanel;
import bruteforce.ModuleBruteForcePanel;
import twofa.Module2FAPanel;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 820, 520);

        cardLayout = new CardLayout(0, 0);
        cardPanel = new JPanel(cardLayout);
        setContentPane(cardPanel);

        // Register screens. Keys are case sensitive.
        cardPanel.add(new Menu(this), "Menu");
        cardPanel.add(new ModuleSQLiPanel(), "Module_SQLi");
        cardPanel.add(new ModuleBruteForcePanel(this), "Module_BruteForce");
        cardPanel.add(new Module2FAPanel(this), "Module_2FA");

        showCard("Menu");
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
}
