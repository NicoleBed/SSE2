package twofa;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/*
 2FA demo — Stolen-code scenario. 
 This demo sends a code and gives 30 seconds for you to enter the code.
 If you enter the code in time you win, if you run out of time before entering 
 the right code the code will expire.
 You can also simulate stolen code.
*/
public class Module2FAPanel extends JPanel {
    
    private final MainFrame mainFrame;
    private String currentCode = null;
    private int secondsLeft = 0;
    private javax.swing.Timer codeTimer;
    private final JLabel lblTimer;
    private final JTextField tfCodeInput;
    private final JTextArea taOutput;
    private final JButton btnSend;
    private final JButton btnVerify;
    private final JButton btnSimulate;
    private static final int EXPIRE_SECONDS = 30;

    
    //This is for the UI handlers
    public Module2FAPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("2FA Demo — Stolen-Code Scenario");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 400, 24);
        add(lblTitle);

        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(560, 10, 120, 26);
        btnBack.addActionListener(e -> {
            if (this.mainFrame != null) this.mainFrame.showCard("Menu");
        });
        add(btnBack);

        btnSend = new JButton("Send Code (simulate)");
        btnSend.setBounds(20, 60, 160, 28);
        btnSend.addActionListener(this::sendCode);
        add(btnSend);

        lblTimer = new JLabel("No code active");
        lblTimer.setBounds(200, 66, 180, 20);
        add(lblTimer);

        tfCodeInput = new JTextField();
        tfCodeInput.setBounds(20, 100, 140, 22);
        add(tfCodeInput);

        btnVerify = new JButton("Verify Code");
        btnVerify.setBounds(180, 98, 120, 26);
        btnVerify.addActionListener(this::verifyCode);
        add(btnVerify);

        btnSimulate = new JButton("Simulate Stolen Code");
        btnSimulate.setBounds(320, 98, 180, 26);
        btnSimulate.addActionListener(this::simulateStolenCode);
        add(btnSimulate);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(taOutput);
        sp.setBounds(20, 140, 660, 260);
        add(sp);

        taOutput.setText("2FA Demo loaded.\n\nClick 'Send Code (simulate)' to generate a 6-digit code that expires in 30 seconds.\n"
                + "Use 'Simulate Stolen Code' to see what happens if the secondary factor is compromised.");
    }

    // generate and send a 6-digit code
    private void sendCode(ActionEvent e) {
        // generate a simple 6-digit code
        currentCode = generateSixDigitCode();
        secondsLeft = EXPIRE_SECONDS;
        lblTimer.setText("Code valid: " + secondsLeft + "s");

        taOutput.setText("Code sent (simulated). The code is: " + currentCode + "\n\n"
                + "In a real system the code would be delivered via SMS or an authenticator app.\n"
                + "This demo keeps everything local and safe.");

        // this stops any existing timer
        if (codeTimer != null && codeTimer.isRunning()) codeTimer.stop();

        // this starts countdown timer
        codeTimer = new javax.swing.Timer(1000, ev -> {
            secondsLeft--;
            if (secondsLeft <= 0) {
                codeTimer.stop();
                currentCode = null;
                lblTimer.setText("Code expired");
                taOutput.setText("Code expired. Click 'Send Code (simulate)' to generate a new one.\n\n"
                        + "Teaching: short expiry limits the window an attacker can use a stolen code.");
            } else {
                lblTimer.setText("Code valid: " + secondsLeft + "s");
            }
        });
        codeTimer.start();
    }

    // This verifies the entered code.
    private void verifyCode(ActionEvent e) {
        String entered = tfCodeInput.getText().trim();
        if (currentCode == null) {
            taOutput.setText("No valid code. Click 'Send Code (simulate)' to generate one.");
            return;
        }
        if (entered.isEmpty()) {
            taOutput.setText("Please enter the 6-digit code.");
            return;
        }
        if (entered.equals(currentCode)) {
            taOutput.setText("Verification success! 2FA code accepted.\n\n"
                    + "Teaching: 2FA adds a second factor and reduces risk if the password is compromised.\n"
                    + "Note: If an attacker also has the code (stolen), 2FA may be bypassed — see 'Simulate Stolen Code'.");
           
            if (codeTimer != null) codeTimer.stop();
            currentCode = null;
            lblTimer.setText("No code active");
        } else {
            taOutput.setText("Verification failed. The code you entered is incorrect.\n\n"
                    + "Tip: Codes expire quickly; make sure you enter the current code.");
        }
    }

    // The option to simulate attacker having the current code.
    private void simulateStolenCode(ActionEvent e) {
        if (currentCode == null) {
            taOutput.setText("No active code to steal. Click 'Send Code (simulate)' first.");
            return;
        }
        
        taOutput.setText("Simulated stolen-code scenario:\n\n"
                + "An attacker obtained the current 2FA code. If the attacker also knows the password,\n"
                + "they can access the account. This shows why protecting secondary channels and\n"
                + "phishing-resistant methods (like security keys) are important.\n\n"
                + "Teaching: 2FA helps but is not invulnerable if the second factor is compromised.");
        // This clears the code to simulate it being consumed/used by attacker
        if (codeTimer != null) codeTimer.stop();
        currentCode = null;
        lblTimer.setText("Code compromised/cleared");
    }

    // This generate a simple random 6-digit code as a String (100000-999999).
    private String generateSixDigitCode() {
        Random r = new Random();
        int code = 100000 + r.nextInt(900000);
        return String.valueOf(code);
    }
}
