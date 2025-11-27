import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SeparationofPrivilege.java
 *
 * Demonstrates the concept of Separation of Privilege with a bank deposit box requiring 2 keys to open,
 * along with a real-world analogy of house keys.
 */
public class SeperationofPrivilege extends JFrame {

    private JCheckBox key1Checkbox;
    private JCheckBox key2Checkbox;
    private JButton openDepositBoxButton;
    private JTextArea infoArea;
    private JButton houseKeyExampleButton;

    public SeperationofPrivilege() {
        setTitle("Separation of Privilege");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Set the JFrame background to blue
        getContentPane().setBackground(Color.BLUE);

        JLabel titleLabel = new JLabel("Bank Deposit Box with Separation", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.setBackground(Color.BLUE);  // match background
        key1Checkbox = new JCheckBox("Key Holder 1 Present (Key 1)");
        key2Checkbox = new JCheckBox("Key Holder 2 Present (Key 2)");
        centerPanel.add(key1Checkbox);
        centerPanel.add(key2Checkbox);

        openDepositBoxButton = new JButton("Try to Open Deposit Box");
        centerPanel.add(openDepositBoxButton);
        add(centerPanel, BorderLayout.CENTER);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setText("Description:\n" +
                "- This bank deposit box requires BOTH keys to open.\n" +
                "- This ensures that not one person can have access.\n" +
                "- Important note!!! : '2 keys are better than 1.'\n\n" +
                "Try selecting one or both key holders and press the button.");
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(420, 120));
        // Set scroll pane viewport background to blue
        scrollPane.getViewport().setBackground(Color.BLUE);
        add(scrollPane, BorderLayout.SOUTH);

        JPanel sidePanel = new JPanel(new FlowLayout());
        sidePanel.setBackground(Color.BLUE); // match background
        houseKeyExampleButton = new JButton("Real-World Example: House Keys");
        sidePanel.add(houseKeyExampleButton);
        add(sidePanel, BorderLayout.EAST);

        openDepositBoxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean hasKey1 = key1Checkbox.isSelected();
                boolean hasKey2 = key2Checkbox.isSelected();

                if (hasKey1 && hasKey2) {
                    infoArea.setText("Access Granted!\n\n" +
                            "Both key holders are present, so the deposit box opens securely. " +
                            "This prevents unauthorized access if only one key holder is compromised."
                            + "Congrats");
                } else if (hasKey1 || hasKey2) {
                    infoArea.setText("Access Denied!\n\n" +
                            "Only one key holder is present. One key alone is not enough to open the deposit box, " +
                            "demonstrating how no single error or attack can cause a security breach." +
                            "Try again");
                } else {
                    infoArea.setText("Access Denied!\n\n" +
                            "No key holders are present. The deposit box remains locked."
                    + "Try again with selection");
                }
            }
        });

        houseKeyExampleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "Real-World Example: House Keys\n\n" +
                        "- Usually, only one key is needed to enter your house.\n" +
                        "- Separation of Privilege (two keys needed) is rare in homes due to convenience.\n" +
                        "- In secure places like bank deposit boxes or office vaults, two keys " +
                        "ensure better security, preventing a single person from causing a breach.\n\n" +
                        "Use Separation of Privilege when the risk justifies the extra effort!";
                JOptionPane.showMessageDialog(SeperationofPrivilege.this, message, "House Key Example", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SeperationofPrivilege demo = new SeperationofPrivilege();
                demo.setVisible(true);
            }
        });
    }
}
