package GUI;

import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel {
    private static final long serialVersionUID = 1L;
    private final MainFrame mainFrame;

    //Create the panel
    public Menu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(12, 12));
        setBackground(new Color(0, 102, 51)); // dark green background

        
        JLabel title = new JLabel("SecurePlay — Main Menu", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 1, 8, 8));
        center.setOpaque(false); // let background show through

        JButton btnSQLi = new JButton("SQLi Demo");
        btnSQLi.addActionListener(e -> mainFrame.showCard("Module_SQLi"));
        center.add(btnSQLi);

        JButton btnBrute = new JButton("Brute Force Demo");
        btnBrute.addActionListener(e -> mainFrame.showCard("Module_BruteForce"));
        center.add(btnBrute);

        JButton btn2FA = new JButton("2FA Demo");
        btn2FA.addActionListener(e -> mainFrame.showCard("Module_2FA"));
        center.add(btn2FA);

        add(center, BorderLayout.CENTER);

        // Bottom: notes and exit
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);

        JTextArea notes = new JTextArea(
            "Notes:\n" +
            "- All modules are local demos and safe.\n" +
            "- Use Back buttons in modules to return here."
        );
        notes.setEditable(false);
        notes.setOpaque(false);
        notes.setForeground(Color.WHITE);
        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);
        bottom.add(notes, BorderLayout.CENTER);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        bottom.add(btnExit, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);
    }
}
