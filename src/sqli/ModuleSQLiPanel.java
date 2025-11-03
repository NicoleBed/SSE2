package sqli;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Safe SQL Injection demo stub (no DB, local only).
 *
 * Back button tries 3 strategies:
 *  1) call mainFrame.showCard("Menu")
 *  2) find CardLayout in ancestor window and switch to "Menu"
 *  3) close the top-level window as a last-resort fallback
 *
 * This prevents the situation where the visible card is not switching even though
 * showCard(...) was called on a different MainFrame instance.
 */
public class ModuleSQLiPanel extends JPanel {
    private final MainFrame mainFrame;
    private final JTextField tfUser;
    private final JPasswordField tfPass;
    private final JTextArea taOutput;

    public ModuleSQLiPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel title = new JLabel("SQL Injection (Demo Stub)");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBounds(260, 10, 400, 30);
        add(title);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(20, 70, 80, 25);
        add(lblUser);

        tfUser = new JTextField();
        tfUser.setBounds(120, 70, 560, 25);
        add(tfUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(20, 110, 80, 25);
        add(lblPass);

        tfPass = new JPasswordField();
        tfPass.setBounds(120, 110, 560, 25);
        add(tfPass);

        JButton btnTry = new JButton("Try (Insecure)");
        btnTry.setBounds(20, 160, 140, 30);
        btnTry.addActionListener(e -> doInsecureTry());
        add(btnTry);

        JButton btnMain = new JButton("Main Menu");
        btnMain.setBounds(180, 160, 120, 30);
        btnMain.addActionListener(e -> navigateBackToMenu(e));
        add(btnMain);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(taOutput);
        sp.setBounds(0, 210, 800, 230);
        add(sp);

        // initial text
        taOutput.setText("INSECURE QUERY (demo):\n\n");
    }

    private void doInsecureTry() {
        String user = tfUser.getText();
        String pass = new String(tfPass.getPassword());

        // construct an insecure concatenated query (demo only)
        String query = "SELECT * FROM users WHERE username = '" + user + "' AND password = '" + pass + "'";
        StringBuilder out = new StringBuilder();
        out.append("INSECURE QUERY (demo):\n");
        out.append(query).append("\n\n");

        // very simple heuristic to "detect" injection-like payloads for demo
        boolean injectionLike = looksLikeInjection(user) || looksLikeInjection(pass);

        if (injectionLike) {
            out.append("Demo result: injection-like input detected (would bypass insecure code).\n\n");
            out.append("Teaching point: concatenating user input into SQL can allow attackers to change the query logic.");
        } else {
            out.append("Demo result: no obvious injection detected (demo).\n\n");
            out.append("Teaching point: safe code uses parameterized queries / prepared statements instead of string concatenation.");
        }

        taOutput.setText(out.toString());
    }

    private boolean looksLikeInjection(String s) {
        if (s == null) return false;
        String low = s.toLowerCase();
        // simple checks for common patterns used in demos
        if (low.contains("' or ") || low.contains("\" or ") || low.contains("'or'") || low.contains(" or 1=1") ) return true;
        if (low.contains(";") || low.contains("--") || low.contains("/*") || low.contains("*/")) return true;
        // injection-like sequences with quotes and equals
        if (low.matches(".*['\"].*=.*")) return true;
        return false;
    }

    /**
     * Robust navigation to menu. Tries multiple strategies so the UI actually switches
     * even if there are multiple frames or the panel was constructed with a different MainFrame instance.
     */
    private void navigateBackToMenu(ActionEvent ev) {
        // 1) Preferred: call method on the MainFrame instance if available
        try {
            if (mainFrame != null) {
                System.out.println("[SQLi] Attempting mainFrame.showCard(\"Menu\")");
                mainFrame.showCard("Menu");
                // small delay not required; ensure UI repaint
                repaint();
                return;
            }
        } catch (Exception ex) {
            System.err.println("[SQLi] mainFrame.showCard threw: " + ex.getMessage());
            // fall through to other strategies
        }

        // 2) Fallback: find a CardLayout in the current top-level window and show "Menu"
        try {
            Window top = SwingUtilities.getWindowAncestor(this);
            if (top != null) {
                boolean switched = switchCardLayoutInContainer(top, "Menu");
                if (switched) {
                    System.out.println("[SQLi] Switched card via ancestor CardLayout");
                    return;
                }
            }
        } catch (Exception ex) {
            System.err.println("[SQLi] fallback CardLayout switch failed: " + ex.getMessage());
        }

        // 3) Last-resort: simply close the top-level window (so the user can re-open app)
        try {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) {
                System.err.println("[SQLi] Closing top-level window as last-resort fallback.");
                w.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Recursively search for a JPanel with CardLayout and call show(panel, cardName).
     * Returns true if a switch was performed.
     */
    private boolean switchCardLayoutInContainer(Container root, String cardName) {
        if (root == null) return false;
        // check this container
        LayoutManager lm = root.getLayout();
        if (lm instanceof CardLayout && root instanceof JPanel) {
            try {
                CardLayout cl = (CardLayout) lm;
                cl.show((JPanel) root, cardName);
                root.revalidate();
                root.repaint();
                return true;
            } catch (Exception ignored) {}
        }
        // otherwise search children
        for (Component c : root.getComponents()) {
            if (c instanceof Container) {
                boolean done = switchCardLayoutInContainer((Container) c, cardName);
                if (done) return true;
            }
        }
        return false;
    }
}
