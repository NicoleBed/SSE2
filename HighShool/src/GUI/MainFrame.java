package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import Templ.tempMod;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private CardLayout cardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//cardPanel = new JPanel();
		//cardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(cardPanel);
		//contentPane.setLayout(new CardLayout(0, 0));
		//CardLayout cl = new CardLayout();
		//cardPanel.setLayout(cl);
		cardLayout = new CardLayout(0,0);
		cardPanel = new JPanel(cardLayout);
		setContentPane(cardPanel);
		
		Menu menu = new Menu(this);
		cardPanel.add(menu, "Main_Menu");
		
		tempMod modul = new tempMod(this);
		cardPanel.add(modul, "Module_Template");	
	}
	
	public void showCard(String cardName) {
		cardLayout.show(cardPanel, cardName);
	}

}