package template;

import javax.swing.JPanel;

import GUI.MainFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * tempMod - instructional template panel.
 *
 * Purpose:
 *  - shows a short guide for teammates on how to add a new module.
 *  - contains a "Main Menu" button to return to the Menu card.
 *
 * Keep this file up-to-date with the project's module creation steps.
 */
public class TemplatePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final MainFrame mainFrame;

    /**
     * Constructor.
     *
     * @param mainFrame used to return to Main_Menu
     */
    public TemplatePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Module Template");
        lblNewLabel.setBounds(185, 5, 148, 23);
        add(lblNewLabel);

        // Back to main menu button
        JButton btnNewButton = new JButton("Main Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showCard("Main_Menu");
            }
        });
        btnNewButton.setBounds(10, 349, 114, 31);
        add(btnNewButton);

        // Instruction header
        JLabel lblNewLabel_1 = new JLabel("To create a new module:");
        lblNewLabel_1.setBounds(10, 22, 194, 20);
        add(lblNewLabel_1);

        // Detailed steps for adding a new module (kept in the panel for quick reference)
        JTextArea txtrRightclickAnd = new JTextArea();
        txtrRightclickAnd.setFont(new Font("Monospaced", Font.PLAIN, 9));
        txtrRightclickAnd.setText(
            "1. Right-click <src> and select add new package\r\n" +
            "\ta. Package naming convention <M#_Name>\r\n" +
            "2. Right-click the package and select add, other, and scroll and select JPanel\r\n" +
            "\ta. GUI naming convention <m#_Name_GUI>\r\n" +
            "3. Below SerialVersionUID, add <private final MainFrame mainframe;>\r\n" +
            "4. In the constructor for the panel, add parameters <MainFrame mainframe>\r\n" +
            "5. On the next line, add <this.mainFrame = mainframe;>\r\n" +
            "6. In MainFrame.java, import your gui class\r\n" +
            "7. At the bottom of the MainFrame constructor, make an instance of your class and put <this> as the parameter\r\n" +
            "8. On the next line, add <cardPanel.add(<object>, \"<Module_#_Name>\");>\r\n" +
            "9. On your GUI, select absolute layout and add it to your panel\r\n" +
            "10. Add a button and put it in the bottom left corner\r\n" +
            "11. Select the button and click 'show events' (Next to Properties)\r\n" +
            "12. Click the '+' on action and double-click the drop-down\r\n" +
            "13. Inside the new ActionListener, add the line <mainframe.showCard(\"Main_Menu\");"
        );
        txtrRightclickAnd.setBounds(20, 38, 627, 221);
        add(txtrRightclickAnd);
    }
}
