package GUI;
//I am aware VS hates this
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(198, 119, 74));
		frame.setBounds(100, 100, 1103, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to <PLACEHOLDER NAME>!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(282, 10, 413, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<FUN IMAGE>");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(0, 255, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(34, 21, 180, 114);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Help");
		btnNewButton.setBounds(994, 456, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("<MODULE DESCRIPTION>");
		lblNewLabel_3.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(34, 207, 490, 55);
		frame.getContentPane().add(lblNewLabel_3);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 232, 337, 21);
		frame.getContentPane().add(progressBar);
		
		JButton btnNewButton_1 = new JButton("Go To Module");
		btnNewButton_1.setBounds(34, 263, 106, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Module 1");
		lblNewLabel_2.setBackground(new Color(255, 128, 128));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setBounds(27, 179, 490, 127);
		frame.getContentPane().add(lblNewLabel_2);
	}
}