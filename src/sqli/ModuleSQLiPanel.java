package sqli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 SQLi demo.
 There is one input with three hints and potential injection queries that bypass the password.
*/

public class ModuleSQLiPanel extends JPanel {
    private final JTextField tf; //input field where the student types a password
    private final JTextArea explanation; //shows the feedback, examples, hints, and lesson text
    private final String REAL = "apple123"; //The real password
    private int hintCount = 0; // The order for when the demo is pressed

    
    public ModuleSQLiPanel() {
        setLayout(new BorderLayout(8,8));
        setBackground(Color.white);

        // creates the title for the game
        JLabel title = new JLabel("Guess the Password", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 12));
        row.add(new JLabel("Password:"));
        tf = new JTextField(18);
        row.add(tf);

        JButton tryBtn = new JButton("Try");
        tryBtn.addActionListener(this::onTry);
        row.add(tryBtn);

        JButton hintBtn = new JButton("Hint");
        hintBtn.addActionListener(this::onHint);
        row.add(hintBtn);

        // The what's this button explaining everything
        JButton whatsBtn = new JButton("What's this");
        whatsBtn.setToolTipText("Full explanation of the demo and why it matters");
        whatsBtn.addActionListener(this::onWhatsThis);
        row.add(whatsBtn);

        add(row, BorderLayout.CENTER);

        // The explanation area for every action
        explanation = new JTextArea();
        explanation.setEditable(false);
        explanation.setLineWrap(true);
        explanation.setWrapStyleWord(true);
        explanation.setFont(new Font("SansSerif", Font.PLAIN, 12));
        explanation.setText("Play: guess the password. Use Hint if stuck.");
        JScrollPane sp = new JScrollPane(explanation);
        sp.setPreferredSize(new Dimension(600, 160));
        add(sp, BorderLayout.SOUTH);
    }

    // Text box that shows your actions
    private void onTry(ActionEvent e) {
        String raw = tf.getText();
        if (raw == null) raw = "";
        raw = raw.trim();
        if (raw.isEmpty()) {
            explanation.setText("Type something first.");
            return;
        }

        // This auto-wraps tautologies to make input simpler
        String wrapped = wrapIfTautology(raw);

        // show the SQL that would be constructed (for teaching)
        String query = "SELECT * FROM users WHERE username = '" + wrapped + "' AND password = '" + wrapped + "'";

        // 1) exact real password check
        if (raw.equals(REAL)) {
            explanation.setText("Correct: exact match to stored password.\n\n"
                    + "Constructed SQL (unsafe demo):\n" + query + "\n\n"
                    + "Lesson: string concatenation like this lets attackers change the query logic.");
            return;
        }

        // 2) injection/tautology detection
        // If wrapIfTautology changed the input, it is treat it as an injection-like tautology.
        if (!wrapped.equals(raw) || raw.equalsIgnoreCase("' or 1=1 --") || raw.equalsIgnoreCase("' or 'a'='a' --")) {
            explanation.setText("Bypassed with SQL injection (tautology).\n\n"
                    + "Constructed SQL (unsafe demo):\n" + query + "\n\n"
                    + "Why this works: the injected text turns the WHERE into a condition that's always true.\n"
                    + "Fix: use prepared statements and parameterized queries instead of concatenation.");
            return;
        }

        // 3) nothing matched
        explanation.setText("Nope. Try again or press Hint.\n\n(If you're testing injection, try typing 1=1 or press Hint.)");
    }

    // this detect very simple tautologies students might type: 1=1, a=a, abc=abc
    private boolean isSimpleTautology(String s) {
        if (s == null) return false;
        String t = s.trim().toLowerCase();
        // numeric tautology: 1=1, 22=22
        if (t.matches("^\\d+\\s*=\\s*\\d+$")) {
            String[] parts = t.split("=");
            return parts[0].trim().equals(parts[1].trim());
        }
        // alphabetic tautology: a=a, abc=abc
        if (t.matches("^[a-z]+\\s*=\\s*[a-z]+$")) {
            String[] parts = t.split("=");
            return parts[0].trim().equals(parts[1].trim());
        }
        return false;
    }

    //If the user types a simple tautology then it gets wrapped
    // Otherwise return the original input unchanged.
    private String wrapIfTautology(String input) {
        if (input == null) return null;
        if (isSimpleTautology(input)) {
            return "' OR " + input.trim() + " --";
        }
        return input;
    }

    // This gives the user three hints
    private void onHint(ActionEvent e) {
        hintCount = Math.min(hintCount + 1, 3);
        if (hintCount == 1) {
            explanation.setText("Hint 1: It's a fruit plus some numbers.");
        } else if (hintCount == 2) {
            explanation.setText("Hint 2: Think how to make the check always true.\n"
                    + "Try typing 1=1 (the demo will auto-wrap it).");
        } else { // third press shows short explicit example and a short safety note
            explanation.setText("Hint 3 (explicit): try exactly ' OR 1=1 --\n\n"
                    + "This shows why we must never concatenate user input into SQL. Use prepared statements.");
        }
    }

    //This covers what the demo does, the goal and what an injections query is
    private void onWhatsThis(ActionEvent e) {
        String text =
                "What this demo does\n\n" +
                "This demo shows why building SQL with string concatenation is unsafe.\n" +
                "You type a value. The demo builds a SQL query using that text directly.\n\n" +
                "Goal\n\n" +
                "Try to guess the real password (apple123). Or try input that makes the WHERE clause always true.\n\n" +
                "What an injection query is\n\n" +
                "An injection input changes the SQL logic. For example:\n" +
                "  ' OR 1=1 --\n" +
                "This closes the quote, adds a condition that is always true, and comments out the rest.\n\n" +
                "Why this is bad\n\n" +
                "If a server used code like this, an attacker could bypass login checks.\n" +
                "They would not need the real password.\n\n" +
                "How to fix (short)\n\n" +
                "Use prepared statements or parameterized queries. Do not concatenate user input.\n" +
                "Validate input, give minimal error messages, and log suspicious attempts.\n\n" +
                "How to use the demo\n\n" +
                "- Type apple123 to see a normal success.\n" +
                "- Type 1=1 or a=a to try a tautology (demo auto-wraps to show injection).\n" +
                "- Press Hint for step-by-step help.\n\n" +
                "That is it. Keep it simple and show the SQL to teach the idea.";

        JTextArea ta = new JTextArea(text);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(520, 380));

        JOptionPane.showMessageDialog(this, sp, "About this demo", JOptionPane.INFORMATION_MESSAGE);
    }
}
