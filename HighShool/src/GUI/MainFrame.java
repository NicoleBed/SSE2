package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import Templ.tempMod;
import M2_Complete_Mediation.m2_Complete_Mediation_GUI;
import bruteforce.ModuleBruteForcePanel;
import sqli.ModuleSQLiPanel;
import twofa.Module2FAPanel;
import M3_Seperation_Privilege.SeperationofPrivilege;

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
		setBounds(100, 100, 550, 500);
		cardLayout = new CardLayout(0,0);
		cardPanel = new JPanel(cardLayout);
		setContentPane(cardPanel);
		
		Menu menu = new Menu(this);
		cardPanel.add(menu, "Main_Menu");
		
		tempMod modul = new tempMod(this);
		cardPanel.add(modul, "Module_Template");	
		
		m2_Complete_Mediation_GUI m2CM_GUI = new m2_Complete_Mediation_GUI(this);
		cardPanel.add(m2CM_GUI, "Complete_Mediation");
		
		ModuleBruteForcePanel mBFP = new ModuleBruteForcePanel(this);
		cardPanel.add(mBFP, "Brute_Force");
		
		ModuleSQLiPanel mSQL = new ModuleSQLiPanel(this);
		cardPanel.add(mSQL, "SQLi");
		
		Module2FAPanel m2FA = new Module2FAPanel(this);
		cardPanel.add(m2FA, "2FA");
		
		SeperationofPrivilege mSOP = new SeperationofPrivilege(this);
		cardPanel.add(mSOP, "Seperation_Privilege");
	}
	
	public void showCard(String cardName) {
		cardLayout.show(cardPanel, cardName);
	}

}
