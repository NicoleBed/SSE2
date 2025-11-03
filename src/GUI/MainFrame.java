package GUI;

import javax.swing.*;
import java.awt.*;
import sqli.ModuleSQLiPanel;
import bruteforce.ModuleBruteForcePanel;
import twofa.Module2FAPanel;


/*
 * Main application frame that holds the card panel.
 *
 * This MainFrame uses a single CardLayout-backed JPanel called cardPanel.
 * All modules must be registered with cardPanel.add(component, "CardKey");
 * Use mainFrame.showCard("CardKey") to switch.
 *
 * NOTE: If you get compile errors about missing classes in Templ, comment out the corresponding
 * cardPanel.add(...) lines until those classes exist.
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // the panel that holds all cards
    private final JPanel cardPanel;

    // convenience reference to CardLayout so showCard is simple
    private final CardLayout cardLayout;

    public MainFrame() {
        super("SecurePlay");

        // configure frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 520);
        setLocationRelativeTo(null);

        // create card panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        // Register cards (keep keys exact and case-sensitive)
        // 1) Menu (must exist)
        Menu menu = new Menu(this);
        cardPanel.add(menu, "Menu");

        // 2) Template module (if you have it)
        try {
            // if tempMod exists in package Templ, this will work
            cardPanel.add(new template.TemplatePanel(this), "Module_Template");
        } catch (NoClassDefFoundError | Exception e) {
            // swallow: template not present — OK during incremental development
            System.err.println("[MainFrame] tempMod not found or failed to load: " + e.getMessage());
        }

        // 3) SQLi demo (if present)
        try {
            cardPanel.add(new sqli.ModuleSQLiPanel(this), "Module_SQLi");
        } catch (NoClassDefFoundError | Exception e) {
            System.err.println("[MainFrame] ModuleSQLiPanel not found or failed to load: " + e.getMessage());
        }

        // 4) Brute Force demo (should exist)
        try {
            cardPanel.add(new bruteforce.ModuleBruteForcePanel(this), "Module_BruteForce");
        } catch (NoClassDefFoundError | Exception e) {
            System.err.println("[MainFrame] ModuleBruteForcePanel not found or failed to load: " + e.getMessage());
        }

        // 5) 2FA demo (should exist)
        try {
            cardPanel.add(new twofa.Module2FAPanel(this), "Module_2FA");
        } catch (NoClassDefFoundError | Exception e) {
            System.err.println("[MainFrame] Module2FAPanel not found or failed to load: " + e.getMessage());
        }

        // Show default card
        showCard("Menu");
    }

    /**
     * Public method modules call to switch cards.
     * @param cardName exact card key registered with cardPanel.add(component, cardName)
     */
    public void showCard(String cardName) {
        if (cardName == null) return;
        // print for debugging
        System.out.println("[MainFrame] showCard called with: " + cardName);
        cardLayout.show(cardPanel, cardName);
        // revalidate/repaint to ensure UI update
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // convenience main() to run the app for testing
    public static void main(String[] args) {
        // ensure UI uses platform look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            MainFrame f = new MainFrame();
            f.setVisible(true);
        });
    }
}
