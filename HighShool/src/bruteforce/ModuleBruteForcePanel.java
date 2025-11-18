package bruteforce;

import javax.swing.*;

import GUI.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Combination lock demo.
 *Students will guess a pin and there will be two modes and Insecure mode and a Secure mode.
 *Insecure will be infinite tries, and secure will be limited tries.
 * 
 */

public class ModuleBruteForcePanel extends JPanel {
    private final JTextField tf;               // this is for where student types the PIN guess
    private final JTextArea explanation;       // this shows feedback and lesson text
    private final JLabel statusLabel;          // this shows attempts or locked status
    private final JButton modeBtn;             // this toggles insecure / secure
    private final String REAL = "123";         // this is the actual PIN for the demo (changeable)
    private int attempts = 0;                  // this is the current attempt counter
    private int hintCount = 0;                 // the cycles of hints (1..3)
    private boolean secureMode = false;        // false = Insecure, true = Secure
    private boolean locked = false;            // for whether account is locked
    private int cooldownSecondsLeft = 0;       // remaining lockout time
    private javax.swing.Timer cooldownTimer;   // swing timer for cooldown

    
    private static final int SECURE_MAX_ATTEMPTS = 5; //the number of failed attempts
    private static final int SECURE_COOLDOWN_SECONDS = 30; //The time locked after hitting the secure limit

    public ModuleBruteForcePanel(MainFrame ignored) {
       
        setLayout(new BorderLayout(8,8)); 
        setBackground(Color.WHITE);

       
        JLabel title = new JLabel("Combination Lock (PIN) Demo", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 12));
        row.add(new JLabel("Enter PIN:"));
        tf = new JTextField(8);
        row.add(tf);

        JButton tryBtn = new JButton("Try");
        tryBtn.addActionListener(this::onTry);
        row.add(tryBtn);

        JButton hintBtn = new JButton("Hint");
        hintBtn.addActionListener(this::onHint);
        row.add(hintBtn);

        JButton whatsBtn = new JButton("What's this");
        whatsBtn.addActionListener(this::onWhatsThis);
        row.add(whatsBtn);

        
        modeBtn = new JButton("Mode: Insecure");
        modeBtn.addActionListener(e -> toggleMode());
        row.add(modeBtn);

        add(row, BorderLayout.CENTER);

        
        JPanel bottom = new JPanel(new BorderLayout(8,8));
        statusLabel = new JLabel("Attempts: 0");
        bottom.add(statusLabel, BorderLayout.NORTH);

        explanation = new JTextArea();
        explanation.setEditable(false);
        explanation.setLineWrap(true);
        explanation.setWrapStyleWord(true);
        explanation.setFont(new Font("SansSerif", Font.PLAIN, 12));
        explanation.setText("Play: guess the 3-digit PIN. Use Hint if stuck.");
        JScrollPane sp = new JScrollPane(explanation);
        sp.setPreferredSize(new Dimension(600, 160));
        bottom.add(sp, BorderLayout.CENTER);

        add(bottom, BorderLayout.SOUTH);
    }

    // The try button functions
    private void onTry(ActionEvent ev) {
        // read and normalize input
        String raw = tf.getText();
        if (raw == null) raw = "";
        raw = raw.trim();

        if (raw.isEmpty()) {
            explanation.setText("Type a PIN first.");
            return;
        }

        // If it is currently locked, this shows remaining time
        if (locked) {
            explanation.setText("Locked. Wait " + cooldownSecondsLeft + "s before trying again.");
            return;
        }

        // The amount of attempts
        attempts++;
        updateStatus();

        // If the guess is correct
        if (raw.equals(REAL)) {
            explanation.setText("Unlocked! Correct PIN.\n\n"
                    + "Lesson: short PINs are low-entropy and easy to guess if attackers try many values.\n"
                    + "Insecure mode lets attackers try forever. Secure mode locks after repeated failures.");
            // This will reset the guesses after correct guess
            attempts = 0;
            updateStatus();
            return;
        }

        // this is for wrong guess handling
        explanation.setText("Wrong PIN. Try again or press Hint.");

        // If your in secure mode and reach limit the cooldown starts
        if (secureMode && attempts >= SECURE_MAX_ATTEMPTS) {
            startCooldown(SECURE_COOLDOWN_SECONDS);
            explanation.setText("Too many wrong tries. Locked for " + SECURE_COOLDOWN_SECONDS + " seconds.\n\n"
                    + "Teaching: lockouts slow automated guessing and raise attack cost.");
        }
    }

    // Click between insecure and secure modes
    private void toggleMode() {
        secureMode = !secureMode;
        modeBtn.setText(secureMode ? "Mode: Secure" : "Mode: Insecure");

        // reset attempts and any lock when switching modes
        attempts = 0;
        stopCooldownIfRunning();
        locked = false;
        cooldownSecondsLeft = 0;
        updateStatus();

        explanation.setText(secureMode
                ? "Secure mode: lock after " + SECURE_MAX_ATTEMPTS + " failed attempts. Cooldown " + SECURE_COOLDOWN_SECONDS + "s."
                : "Insecure mode: unlimited tries allowed. This is vulnerable to brute-force.");
    }

    // Start a cooldown lock for the given seconds
    private void startCooldown(int seconds) {
        locked = true;
        cooldownSecondsLeft = seconds;
        updateStatus();

        if (cooldownTimer != null && cooldownTimer.isRunning()) {
            cooldownTimer.stop();
        }

        // Timer ticks every 1s to update remaining seconds
        cooldownTimer = new javax.swing.Timer(1000, e -> {
            cooldownSecondsLeft--;
            if (cooldownSecondsLeft <= 0) {
                cooldownTimer.stop();
                locked = false;
                attempts = 0;
                cooldownSecondsLeft = 0;
                explanation.setText("Lockout ended. You may try again.");
                updateStatus();
            } else {
                updateStatus();
            }
        });
        cooldownTimer.start();
    }

    // Stop cooldown timer if it exists
    private void stopCooldownIfRunning() {
        if (cooldownTimer != null && cooldownTimer.isRunning()) {
            cooldownTimer.stop();
            cooldownTimer = null;
        }
    }

    // Updates the status of it either being locked or amount of attempts
    private void updateStatus() {
        if (locked) {
            statusLabel.setText("LOCKED (" + cooldownSecondsLeft + "s)");
        } else {
            statusLabel.setText("Attempts: " + attempts + (secureMode ? "  [Secure mode]" : "  [Insecure mode]"));
        }
    }

    // Goes through three hints
    private void onHint(ActionEvent ev) {
        hintCount = Math.min(hintCount + 1, 3);
        if (hintCount == 1) {
            explanation.setText("Hint 1: The PIN is three digits.");
        } else if (hintCount == 2) {
            explanation.setText("Hint 2: Try simple sequences first (common PINs are easy).");
        } else {
            explanation.setText("Hint 3 (explicit): try 123\n\n"
                    + "Teaching note: this demo uses a weak PIN to show how easy it is to break without limits.");
        }
    }

    // A full explanation in the what's this option
    private void onWhatsThis(ActionEvent ev) {
        String text =
                "What this demo does\n\n" +
                "You guess a short numeric PIN. The demo shows what happens when guesses are allowed.\n\n" +
                "Goal\n\n" +
                "See how easy short PINs are to break and how lockouts slow attackers.\n\n" +
                "Modes\n\n" +
                "- Insecure: unlimited tries. Attackers can try many values.\n" +
                "- Secure: locks after a fixed number of failures and forces a cooldown.\n\n" +
                "Why this matters\n\n" +
                "Real attackers use automation. Unlimited tries let them try thousands per second.\n" +
                "Lockouts and rate-limits increase the time and cost to break an account.\n\n" +
                "How to use\n\n" +
                "- Type a 3-digit PIN and press Try.\n" +
                "- Toggle Mode to see the difference between Insecure and Secure.\n" +
                "- Press Hint for step-by-step help.\n\n" +
                "That is it.";

        JTextArea ta = new JTextArea(text);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(520, 340));
        JOptionPane.showMessageDialog(this, sp, "About this demo", JOptionPane.INFORMATION_MESSAGE);
    }
}
