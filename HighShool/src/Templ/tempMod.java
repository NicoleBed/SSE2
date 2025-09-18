package Templ;

import javax.swing.JPanel;

import GUI.MainFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class tempMod extends JPanel {

	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public tempMod(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Module Template");
		lblNewLabel.setBounds(185, 5, 148, 23);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("Main_Menu");
			}
		});
		btnNewButton.setBounds(10, 349, 114, 31);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("To create a new module:");
		lblNewLabel_1.setBounds(10, 22, 194, 20);
		add(lblNewLabel_1);
		
		JTextArea txtrRightclickAnd = new JTextArea();
		txtrRightclickAnd.setFont(new Font("Monospaced", Font.PLAIN, 9));
		txtrRightclickAnd.setText("1. Right-click <src> and select add new package\r\n\ta. Package naming convention <M#_Name>\r\n2. Right-click the package and select add, other, and scroll and select JPanel\r\n\ta. GUI naming convention <m#_Name_GUI>\r\n3. Below SerialVersionUID, add <private final MainFrame mainframe;>\r\n4. In the constructor for the panel, add parameters <MainFrame mainframe>\r\n5. On the next line, add <this.mainFrame = mainframe;>\r\n6. In MainFrame.java, import your gui class\r\n7. At the bottom of the MainFrame constructor, make an instance of your class and put <this> as the parameter\r\n8. On the next line, add <cardPanel.add(<object>, \"<Module_#_Name>\");>\r\n9. On your GUI, select absolute layout and add it to your panel\r\n10. Add a button and put it in the bottom left corner\r\n11. Select the button and click 'show events' (Next to Properties)\r\n12. Click the '+' on action and double-click the drop-down\r\n13. Inside the new ActionListener, add the line <mainframe.showCard(\"Main_Menu\");");
		txtrRightclickAnd.setBounds(20, 38, 627, 221);
		add(txtrRightclickAnd);

	}
}