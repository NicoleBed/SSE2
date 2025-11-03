package bruteforce;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Brute-force learning module (safe demo).
 *
 * - No network or DB.
 * - Insecure mode: unlimited guesses (simulates vulnerable system).
 * - Secure mode: rate-limited / account lockout after N attempts with cooldown timer.
 *
 * Back button uses mainFrame.showCard("Menu") and falls back to closing the window
 * if mainFrame is null to avoid freezing the UI.
 */
public class ModuleBruteForcePanel extends JPanel {
    private final MainFrame mainFrame;

    // demo state
    private final String correctPassword = "apple123"; // demo password students can guess
    private int insecureAttempts = 0;

    // secure mode state
    private int secureAttempts = 0;
    private final int SECURE_MAX_ATTEMPTS = 5;
    private boolean secureLocked = false;
    private javax.swing.Timer secureCooldownTimer;
    private int secureCooldownSecondsLeft = 0;

    // UI components
    private final JTextField tfUser;
    private final JPasswordField tfPass;
    private final JTextArea taOutput;
    private final JLabel lblSecureStatus;

    public ModuleBruteForcePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Brute-Force Demo (Try to guess the password)");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 500, 25);
        add(lblTitle);

        JLabel lblInstr = new JLabel("<html><i>Insecure mode lets attackers try forever.<br>"
                + "Secure mode locks the account after multiple failures and forces a cooldown.</i></html>");
        lblInstr.setBounds(20, 40, 600, 40);
        add(lblInstr);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(20, 95, 80, 20);
        add(lblUser);

        tfUser = new JTextField("student");
        tfUser.setBounds(100, 95, 160, 22);
        add(tfUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(20, 125, 80, 20);
        add(lblPass);

        tfPass = new JPasswordField();
        tfPass.setBounds(100, 125, 160, 22);
        add(tfPass);

        JButton btnTryInsecure = new JButton("Insecure Try");
        btnTryInsecure.setBounds(280, 95, 140, 25);
        btnTryInsecure.addActionListener(e -> insecureTry());
        btnTryInsecure.setToolTipText("Shows how unlimited attempts can be abused.");
        add(btnTryInsecure);

        JButton btnTrySecure = new JButton("Secure Try");
        btnTrySecure.setBounds(280, 125, 140, 25);
        btnTrySecure.addActionListener(e -> secureTry());
        btnTrySecure.setToolTipText("Shows lockout after repeated failures.");
        add(btnTrySecure);

        lblSecureStatus = new JLabel("Secure status: OK");
        lblSecureStatus.setBounds(440, 95, 250, 20);
        add(lblSecureStatus);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(taOutput);
        sp.setBounds(20, 165, 670, 220);
        add(sp);

        JButton btnHint = new JButton("Hint");
        btnHint.setBounds(20, 400, 90, 26);
        btnHint.addActionListener(e -> showHint());
        add(btnHint);

        JButton btnReset = new JButton("Reset Secure");
        btnReset.setBounds(130, 400, 120, 26);
        btnReset.addActionListener(e -> resetSecureState());
        add(btnReset);

        // Robust Back button: uses mainFrame if available; otherwise falls back to closing window.
        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(560, 400, 130, 26);
        btnBack.addActionListener(ev -> {
            try {
                if (mainFrame != null) {
                    // NOTE: ensure MainFrame registers the menu with key "Menu"
                    mainFrame.showCard("Menu");
                    System.out.println("[BruteForce] Navigated back to Menu");
                } else {
                    System.err.println("[BruteForce] mainFrame is null; closing window as fallback.");
                    Window w = SwingUtilities.getWindowAncestor((Component) ev.getSource());
                    if (w != null) w.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Window w = SwingUtilities.getWindowAncestor((Component) ev.getSource());
                if (w != null) w.dispose();
            }
        });
        add(btnBack);

        taOutput.setText("Welcome to Brute-Force demo.\n\n"
                + "Try to guess the password using Insecure Try (no limits) and Secure Try (lockout).\n\n"
                + "Tip: password is a word + number (try simple guesses or the common payloads).");
    }

    private void insecureTry() {
        insecureAttempts++;
        String user = tfUser.getText();
        String pass = new String(tfPass.getPassword());

        StringBuilder out = new StringBuilder();
        out.append("=== Insecure Attempt #").append(insecureAttempts).append(" ===\n");
        out.append("Checking username='").append(user).append("' password='").append(pass).append("'\n");

        if (pass.equals(correctPassword)) {
            out.append("RESULT: LOGIN SUCCESS (insecure system accepts correct password)\n\n");
            out.append("Teaching point: Insecure systems with no rate limiting allow repeated automated attempts.");
        } else {
            out.append("RESULT: LOGIN FAILED\n\n");
            out.append("No protections applied — attacker could keep trying.");
        }

        taOutput.setText(out.toString());
    }

    private void secureTry() {
        if (secureLocked) {
            taOutput.setText("Account is locked. Please wait " + secureCooldownSecondsLeft + " seconds.\n\n"
                    + "Teaching point: lockout prevents fast repeated guessing.");
            return;
        }

        secureAttempts++;
        String user = tfUser.getText();
        String pass = new String(tfPass.getPassword());

        StringBuilder out = new StringBuilder();
        out.append("=== Secure Attempt #").append(secureAttempts).append(" ===\n");
        out.append("Checking username='").append(user).append("' password='").append(pass).append("'\n");

        if (pass.equals(correctPassword)) {
            out.append("RESULT: LOGIN SUCCESS (secure system)\n\n");
            out.append("Teaching point: correct password succeeds; failed attempts count resets on success.");
            secureAttempts = 0;
            lblSecureStatus.setText("Secure status: OK");
        } else {
            out.append("RESULT: LOGIN FAILED\n\n");
            if (secureAttempts >= SECURE_MAX_ATTEMPTS) {
                lockAccountForCooldown(30); // 30 second cooldown
                out.append("Account locked due to too many failures. Cooldown started (30s).\n");
                out.append("Teaching point: account lockout slows attackers and blocks fast brute-force.");
            } else {
                out.append("Failed attempt " + secureAttempts + " of " + SECURE_MAX_ATTEMPTS + ".\n");
                out.append("Teaching point: rate limiting or lockout should be enforced.");
            }
        }

        taOutput.setText(out.toString());
    }

    private void lockAccountForCooldown(int seconds) {
        secureLocked = true;
        secureCooldownSecondsLeft = seconds;
        lblSecureStatus.setText("Secure status: LOCKED (" + secureCooldownSecondsLeft + "s)");
        if (secureCooldownTimer != null && secureCooldownTimer.isRunning()) {
            secureCooldownTimer.stop();
        }
        secureCooldownTimer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secureCooldownSecondsLeft--;
                if (secureCooldownSecondsLeft <= 0) {
                    secureCooldownTimer.stop();
                    secureLocked = false;
                    secureAttempts = 0;
                    lblSecureStatus.setText("Secure status: OK");
                    taOutput.setText("Cooldown finished. You can try secure login again.\n\n"
                            + "Teaching point: choose a lockout policy that balances security and usability.");
                } else {
                    lblSecureStatus.setText("Secure status: LOCKED (" + secureCooldownSecondsLeft + "s)");
                }
            }
        });
        secureCooldownTimer.start();
    }

    private void resetSecureState() {
        if (secureCooldownTimer != null) secureCooldownTimer.stop();
        secureLocked = false;
        secureAttempts = 0;
        lblSecureStatus.setText("Secure status: OK");
        taOutput.setText("Secure state reset. Lockout cleared.");
    }

    private void showHint() {
        taOutput.setText("Hint: Good passwords are long and not simple words + numbers.\n\n"
                + "This demo uses 'apple123' as the correct password so you can see the difference between "
                + "unlimited attempts and a locked account.");
    }
}
