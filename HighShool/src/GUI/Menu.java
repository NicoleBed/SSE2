package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import GUI.MainFrame;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public Menu(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(null);
		
		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("Module_Template");
			}
		});
		button.setBounds(129, 145, 85, 21);
		add(button);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setBounds(200, 10, 25, 13);
		add(lblNewLabel);

	}

}
