package M3_Seperation_Privilege;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import GUI.MainFrame;

/**
 * SeparationofPrivilege.java
 *
 * Demonstrates the concept of Separation of Privilege with a bank deposit box requiring 2 keys to open,
 * along with a real-world analogy of house keys.
 */

public class SeperationofPrivilege extends JPanel {

	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;
	
	private JCheckBox key1Checkbox;
	private JCheckBox key2Checkbox;
	private JButton openDepositBoxButton;
	private JTextArea infoArea;
	private JButton houseKeyExampleButton;

	/**
	 * Create the panel.
	 */
	public SeperationofPrivilege(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bank Deposit Box with Separation", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
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
        add(scrollPane, BorderLayout.SOUTH);

        JPanel sidePanel = new JPanel(new FlowLayout());
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
                            "This prevents unauthorized access if only one key holder is compromised.");
                } else if (hasKey1 || hasKey2) {
                    infoArea.setText("Access Denied!\n\n" +
                            "Only one key holder is present. One key alone is not enough to open the deposit box, " +
                            "demonstrating how no single error or attack can cause a security breach.");
                } else {
                    infoArea.setText("Access Denied!\n\n" +
                            "No key holders are present. The deposit box remains locked.");
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
}
