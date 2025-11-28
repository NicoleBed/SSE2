package M1_FailSafeDefaults;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.MainFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class m1_FailSafeDefault_GUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;
	
	//Game Variables
	private int round = 1;
	private int score = 0;
	private boolean useInclusionRules = false;
	private Random rand = new Random();
	private PersonType currentPerson;
	
	// private final List<String> peoplePool = new ArrayList<>(List.of("adult", "minor", "VIP", "Wearing ski-mask", "Fake ID", "Owner"));
	
	
	public enum PersonType{
		ADULT, MINOR, VIP, WEARING_MASK, FAKE_ID, OWNER
	}
	
	
	private final List<PersonType> peoplePool = new ArrayList<>(List.of(PersonType.ADULT, PersonType.MINOR,
			PersonType.VIP, PersonType.WEARING_MASK, PersonType.FAKE_ID, PersonType.OWNER));
	
	private final List<String> inclusionRules = new ArrayList<>(List.of(
			"Allow adults with valis ID's",
			"Allow VIP's",
			"Allow the owner"
			));
	
	
	private final List<String> exclusionRules = new ArrayList <> (List.of(
			"No minors",
			"No people waring masks",
			"No Fake IDs"			
			));
	
	//GUI stuff
	private JTextArea gameLog;
	private JLabel lblRules, lblPerson, lblScore, lblRound;
	private JButton btnAllow, btnDeny, btnUnsure, btnNext, btnMenu;
	
	public m1_FailSafeDefault_GUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(null);
		setBorder(new EmptyBorder(12, 12, 12, 12));
		setBackground(new Color(240, 240, 240));
		
		//Status labels 
		lblRound = new JLabel("Round: 1");
		lblScore = new JLabel("Score: 0");
		lblRules = new JLabel ("Rules: Don't let in minors, people with masks, or fake ID's!");
		lblRound.setBounds(15, 5, 80, 18);
		lblScore.setBounds(80, 5, 80, 18);
		lblRules.setBounds(155, 5, 400, 18);
		add(lblRound);
		add(lblScore);
		add(lblRules);
		
		//Game log area
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 28, 370, 120);
		gameLog = new JTextArea();
		gameLog.setEditable(false);
		gameLog.setWrapStyleWord(true);
		scrollPane.setViewportView(gameLog);
		add(scrollPane);
		
		//person display
		
		lblPerson = new JLabel();
		lblPerson.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPerson.setBounds(15, 156, 300, 24);
		add(lblPerson);
		
		//buttons
		btnAllow = new JButton("Allow");
		btnAllow.setBounds(15, 190, 90, 28);
		add(btnAllow);
		
		btnDeny = new JButton("Deny"); //Not sure why but this button is not appearing. (resolved)
		btnDeny.setBounds(115, 190, 90, 28);
		btnUnsure = new JButton("Unsure"); //Since unsure and Deny have the same purpose I will utilize this button for Deny (resolved)
		btnUnsure.setBounds(225, 190, 90, 28);
		add(btnUnsure);
		add(btnDeny);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(315, 227, 70, 25);
		btnNext.setEnabled(false);
		add(btnNext);
		
		btnMenu = new JButton("Main menu");
		btnMenu.setBounds(15, 227, 110, 25);
		add(btnMenu);
		
		//Button actions
		
		btnAllow.addActionListener(e -> handleDecision(true));
		btnDeny.addActionListener(e -> handleDecision(false));
		btnUnsure.addActionListener(e -> handleDecision(false));
		btnNext.addActionListener(e -> nextRound());
		btnMenu.addActionListener(e -> mainFrame.showCard("Main_Menu"));
		
		startGame();
	}
	
	private void startGame() {
		gameLog.setText("Fail-Safe module\n");
		round = 1;
		score = 0;
		useInclusionRules = false;
		lblRules.setText("Rules: Don't let in minors, fake ID's or people with masks!");
		updateLabels();
		generatePerson();
		enableDecisionButtons(true);
		btnNext.setEnabled(false);
	
		
	}
	
	private String getDisplayName(PersonType person) {
		switch(person) {
			case ADULT:
				return "Adult";
			case MINOR:
				return "Minor";
			case VIP:
				return "VIP";
			case WEARING_MASK:
				return "Person Wearing Mask";
			case FAKE_ID:
				return "Peron with a fake ID";
			case OWNER:
				return "Owner";
			default:
				return "Unkown";
		
		}
	}
	
	private void generatePerson() {
		currentPerson = peoplePool.get(rand.nextInt(peoplePool.size()));
		lblPerson.setText("Person at door: " + getDisplayName(currentPerson));
		
	}
	
	private void handleDecision(boolean allow) {
		boolean correct = evaluateDecision(allow);
		if (correct) {
			score += 10;
			gameLog.append("Good Job! + 10 points!\n");
			
		} else {
			score -= 5;
			gameLog.append("Nope, sorry! - 5 point.\n");
		}
		
		if (!useInclusionRules && (currentPerson.equals("minor")|| currentPerson.equals("wearing ski-mask") || currentPerson.equals("Fake ID")) && allow) {
			gameLog.append("You let in an attacker!!!\n");
		
		}
		
		enableDecisionButtons(false);
		btnNext.setEnabled(true);
		updateLabels();
		
		
	}
	
	private boolean evaluateDecision(boolean allow) {
		if (!useInclusionRules) {
			
			if(currentPerson == PersonType.MINOR || currentPerson == PersonType.WEARING_MASK
					|| currentPerson == PersonType.FAKE_ID){
						
				return !allow;
					}
			return allow;
	}	else {
		if (currentPerson == PersonType.ADULT || currentPerson == PersonType.VIP || currentPerson == PersonType.OWNER) {
			return allow;
		}
		return !allow;
	}
	}
	
	private void nextRound() {
		round++;
		lblRound.setText("Round: " + round);
		btnNext.setEnabled(false);
		enableDecisionButtons(true);
		if (round == 4 && !useInclusionRules) {
			useInclusionRules = true;
			lblRules.setText("Rules: Now using inclusion (fail-safe) rules!");
			gameLog.append("\nA virus got in! Switching to inclusion rules (default deny/explicit allow).\n\n");
			
		}
		
		generatePerson();
		updateLabels();
		
		
	}
	
	private void enableDecisionButtons(boolean enable) {
		btnAllow.setEnabled(enable);
		btnDeny.setEnabled(enable);
		btnUnsure.setEnabled(enable);
		
	}
	
	private void updateLabels() {
		lblScore.setText("score: " + score);
		lblRound.setText("round: " + round);
		
	}
			
}
