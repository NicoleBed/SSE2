package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import GUI.MainFrame;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public Menu(MainFrame mainFrame) {
		setBackground(new Color(0, 128, 0));
		this.mainFrame = mainFrame;
		setLayout(null);

		JButton btnModuleTemplate = new JButton("Module Template");
		btnModuleTemplate.setBackground(new Color(0, 255, 255));
		btnModuleTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("Module_Template");
			}
		});
		btnModuleTemplate.setBounds(171, 326, 124, 30);
		add(btnModuleTemplate);

		JLabel lblNewLabel = new JLabel("Welcome to Secure Play");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(219, 10, 338, 30);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/GUI/Img/Grad7.png")));
		lblNewLabel_1.setBounds(671, 0, 79, 103);
		add(lblNewLabel_1);

		JButton bSoP = new JButton("Start");
		bSoP.setFont(new Font("Georgia", Font.PLAIN, 12));
		bSoP.setBorderPainted(false);
		bSoP.setForeground(new Color(255, 255, 255));
		bSoP.setBackground(new Color(0, 128, 255));
		bSoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bSoP.setBounds(30, 150, 84, 20);
		add(bSoP);
		
		JButton bCM = new JButton("Start");
		bCM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("Complete_Mediation");
			}
		});
		bCM.setFont(new Font("Georgia", Font.PLAIN, 12));
		bCM.setForeground(new Color(255, 255, 255));
		bCM.setBorderPainted(false);
		bCM.setBackground(new Color(0, 128, 255));
		bCM.setBounds(30, 280, 84, 20);
		add(bCM);
		
		JButton bFSD = new JButton("Start");
		bFSD.setFont(new Font("Georgia", Font.PLAIN, 12));
		bFSD.setForeground(new Color(255, 255, 255));
		bFSD.setBorderPainted(false);
		bFSD.setBackground(new Color(0, 128, 255));
		bFSD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bFSD.setBounds(230, 150, 84, 20);
		add(bFSD);

		JLabel lFSD = new JLabel("Fail-Safe Defaults");
		lFSD.setFont(new Font("Georgia", Font.PLAIN, 16));
		lFSD.setForeground(new Color(255, 255, 255));
		lFSD.setBackground(new Color(0, 255, 0));
		lFSD.setBounds(230, 50, 200, 40);
		add(lFSD);
		
		JLabel lCM = new JLabel("Complete Mediation");
		lCM.setForeground(new Color(255, 255, 255));
		lCM.setFont(new Font("Georgia", Font.PLAIN, 16));
		lCM.setBounds(30, 180, 200, 40);
		add(lCM);
		
		JLabel lSoP = new JLabel("Seperation of Privilege");
		lSoP.setFont(new Font("Georgia", Font.PLAIN, 16));
		lSoP.setForeground(new Color(255, 255, 255));
		lSoP.setBounds(30, 50, 200, 40);
		add(lSoP);
	}

}

