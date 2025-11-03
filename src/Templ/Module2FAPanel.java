package Templ;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * 2FA learning module (TOTP-like one-time code stub).
 *
 * - No network / SMS / email. Codes are generated locally and expire after 30s.
 * - Demonstrates "something you have" factor (simulated) and code expiry.
 *
 * Back button uses mainFrame.showCard("Menu") and falls back to closing the window
 * if mainFrame is null to avoid freezing the UI.
 */
public class Module2FAPanel extends JPanel {
    private final MainFrame mainFrame;

    // code state
    private String currentCode = null;
    private int secondsLeft = 0;
    private javax.swing.Timer codeTimer;

    // UI
    private final JLabel lblCodeTimer;
    private final JTextField tfCodeInput;
    private final JTextArea taOutput;

    public Module2FAPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("2FA Demo (One-Time Code Simulator)");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 500, 25);
        add(lblTitle);

        JLabel lblIntro = new JLabel("<html><i>This simulates how a one-time 2FA code works. "
                + "Press Send Code, then enter the code before it expires (30s).</i></html>");
        lblIntro.setBounds(20, 40, 600, 40);
        add(lblIntro);

        JButton btnSend = new JButton("Send Code (simulate)");
        btnSend.setBounds(20, 95, 180, 28);
        btnSend.addActionListener(e -> sendCode());
        add(btnSend);

        lblCodeTimer = new JLabel("No code sent");
        lblCodeTimer.setBounds(220, 100, 200, 20);
        add(lblCodeTimer);

        tfCodeInput = new JTextField();
        tfCodeInput.setBounds(20, 140, 160, 22);
        add(tfCodeInput);

        JButton btnVerify = new JButton("Verify Code");
        btnVerify.setBounds(200, 138, 120, 26);
        btnVerify.addActionListener(e -> verifyCode());
        add(btnVerify);

        // Optional: simulate stolen code scenario
        JButton btnSimulateStolen = new JButton("Simulate Stolen Code");
        btnSimulateStolen.setBounds(340, 138, 170, 26);
        btnSimulateStolen.addActionListener(e -> simulateStolenCode());
        add(btnSimulateStolen);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(taOutput);
        sp.setBounds(20, 180, 670, 220);
        add(sp);

        JButton btnExplain = new JButton("What is this?");
        btnExplain.setBounds(20, 410, 140, 26);
        btnExplain.addActionListener(e -> explain2FA());
        add(btnExplain);

        // Robust Back button (uses mainFrame or falls back)
        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(560, 410, 130, 26);
        btnBack.addActionListener(ev -> {
            try {
                if (mainFrame != null) {
                    // NOTE: ensure MainFrame registers the menu with key "Menu"
                    mainFrame.showCard("Menu");
                    System.out.println("[2FA] Navigated back to Menu");
                } else {
                    System.err.println("[2FA] mainFrame is null; closing window as fallback.");
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

        taOutput.setText("2FA Demo loaded.\n\nClick 'Send Code (simulate)' to generate a six-digit code that expires in 30 seconds.\n"
                + "This simulates a TOTP or SMS code but runs fully locally. Try entering the code to verify.");
    }

    private void sendCode() {
        currentCode = generateSixDigitCode();
        secondsLeft = 30;
        lblCodeTimer.setText("Code valid for: " + secondsLeft + "s");
        taOutput.setText("Code sent (simulated). The code is: " + currentCode + "\n\n"
                + "In a real system the code would be delivered via an authenticator app or SMS.\n"
                + "This demo shows expiration and verification only (no external delivery).");

        if (codeTimer != null && codeTimer.isRunning()) {
            codeTimer.stop();
        }
        codeTimer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--;
                if (secondsLeft <= 0) {
                    codeTimer.stop();
                    currentCode = null;
                    lblCodeTimer.setText("Code expired");
                    taOutput.setText("Code expired. Click 'Send Code (simulate)' to generate a new one.");
                } else {
                    lblCodeTimer.setText("Code valid for: " + secondsLeft + "s");
                }
            }
        });
        codeTimer.start();
    }

    private void verifyCode() {
        String entered = tfCodeInput.getText().trim();
        if (currentCode == null) {
            taOutput.setText("No valid code. Click 'Send Code (simulate)' to generate a new code.");
            return;
        }
        if (entered.isEmpty()) {
            taOutput.setText("Please enter the 6-digit code.");
            return;
        }
        if (entered.equals(currentCode)) {
            taOutput.setText("Verification success! 2FA code accepted.\n\n"
                    + "Teaching point: 2FA adds 'something you have' to the password factor, stopping attackers even if they have the password.");
            // reset code after success
            if (codeTimer != null) codeTimer.stop();
            currentCode = null;
            lblCodeTimer.setText("No code sent");
        } else {
            taOutput.setText("Verification failed. The code you entered is incorrect.\n\n"
                    + "Tip: Codes expire quickly; make sure you enter the current code.");
        }
    }

    private void simulateStolenCode() {
        if (currentCode == null) {
            taOutput.setText("No active code to steal. Click 'Send Code (simulate)' first, then use 'Simulate Stolen Code'.");
            return;
        }
        // Simulate attacker having the code; but since this is a simulation, show a teaching message.
        taOutput.setText("Simulated stolen-code scenario:\n\n"
                + "An attacker got the secondary code. If the attacker also knows the password, they can access the account.\n\n"
                + "Teaching point: 2FA reduces risk but is not a silver bullet; protect secondary channels and consider phishing-resistant methods (e.g., security keys).");
        // Optionally clear the code to simulate that the attacker used it
        currentCode = null;
        if (codeTimer != null) codeTimer.stop();
        lblCodeTimer.setText("Code compromised/cleared");
    }

    private String generateSixDigitCode() {
        Random r = new Random();
        int code = 100000 + r.nextInt(900000);
        return String.valueOf(code);
    }

    private void explain2FA() {
        taOutput.setText("What this demo shows:\n\n"
                + "1) A one-time code is generated and only valid for a short time (30s here).\n"
                + "2) Even if an attacker knows the password, they usually cannot get the code (unless they also control the user's phone or app).\n"
                + "3) Real TOTP uses shared secret + time + HMAC; this demo simplifies that to a local code generator for teaching.");
    }
}
