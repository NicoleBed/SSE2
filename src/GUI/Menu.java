package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Main menu panel for SecurePlay.
 *
 * This Menu shows buttons that navigate to different modules via MainFrame.showCard(String).
 * Buttons added:
 *  - SQLi Demo ("Module_SQLi")
 *  - Brute Force Demo ("Module_BruteForce")
 *  - 2FA Demo ("Module_2FA")
 */
public class Menu extends JPanel {
    private static final long serialVersionUID = 1L;
    private final MainFrame mainframe;

    public Menu(MainFrame mainframe) {
        this.mainframe = mainframe;
        initialize();
    }

    private void initialize() {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("SecurePlay — Main Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setBounds(20, 10, 400, 30);
        add(lblTitle);

        JLabel lblSubtitle = new JLabel("Choose a lesson to explore attacker and defender perspectives:");
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitle.setBounds(20, 40, 600, 20);
        add(lblSubtitle);

        // Example existing template button (keeps original functionality if tempMod exists)
        JButton btnTemplate = new JButton("Module Template");
        btnTemplate.addActionListener(e -> mainframe.showCard("Module_Template"));
        btnTemplate.setBounds(129, 150, 140, 28);
        add(btnTemplate);

        // SQLi demo button
        JButton btnSQLi = new JButton("SQLi Demo");
        btnSQLi.addActionListener(e -> mainframe.showCard("Module_SQLi"));
        btnSQLi.setBounds(129, 190, 140, 28);
        add(btnSQLi);

        // Brute-force demo button
        JButton btnBrute = new JButton("Brute Force Demo");
        btnBrute.addActionListener(e -> mainframe.showCard("Module_BruteForce"));
        btnBrute.setBounds(129, 230, 160, 28);
        add(btnBrute);

        // 2FA demo button
        JButton btn2FA = new JButton("2FA Demo");
        btn2FA.addActionListener(e -> mainframe.showCard("Module_2FA"));
        btn2FA.setBounds(129, 270, 140, 28);
        add(btn2FA);

        JTextArea taNotes = new JTextArea();
        taNotes.setEditable(false);
        taNotes.setLineWrap(true);
        taNotes.setWrapStyleWord(true);
        taNotes.setText("Notes:\n- All modules are safe, local, and for demonstration only.\n"
                + "- Use the Back button inside modules to return here.\n"
                + "- Teachers: modules are sandboxed (no DB/network).");
        JScrollPane spNotes = new JScrollPane(taNotes);
        spNotes.setBounds(20, 320, 520, 130);
        add(spNotes);

        // Exit button
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(560, 380, 120, 30);
        btnExit.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        add(btnExit);
    }
}
