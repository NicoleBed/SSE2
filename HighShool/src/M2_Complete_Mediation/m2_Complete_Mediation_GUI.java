package M2_Complete_Mediation;

import javax.swing.JPanel;
import GUI.MainFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.util.Random;

public class m2_Complete_Mediation_GUI extends JPanel {
	int i = 0; //for determinng the guest at random
	int p = 0; //guest request
	int points = 0;

	private static final long serialVersionUID = 1L;
	private final MainFrame mainFrame;
	JButton btnDeny;
	JButton bAllow;

	/**
	 * Create the panel.
	 */
	public m2_Complete_Mediation_GUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(null);
		setBackground(new Color(128, 191, 255));
		
		//make 15 guests
		guest_log guest1 = new guest_log();
		guest_log guest2 = new guest_log();
		guest_log guest3 = new guest_log();
		guest_log guest4 = new guest_log();
		guest_log guest5 = new guest_log();
		guest_log guest6 = new guest_log();
		guest_log guest7 = new guest_log();
		guest_log guest8 = new guest_log();
		guest_log guest9 = new guest_log();
		guest_log guest10 = new guest_log();
		guest_log guest11 = new guest_log();
		guest_log guest12 = new guest_log();
		guest_log guest13 = new guest_log();
		guest_log guest14 = new guest_log();
		guest_log guest15 = new guest_log();
		//make 15 guests
		
		guest_log[] g = {guest1, guest2, guest3, guest4, guest5, guest6, guest7, guest8, guest9, guest10, guest11, guest12, guest13, guest14, guest15};

		JButton MainMenu = new JButton("Main Menu");
		MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("Main_Menu");
			}
		});
		MainMenu.setBounds(10, 447, 84, 20);
		add(MainMenu);
		
		JLabel nPoints = new JLabel("0");
		nPoints.setFont(new Font("Georgia", Font.PLAIN, 16));
		nPoints.setBounds(495, 57, 44, 20);
		add(nPoints);
		
		JLabel lPoints = new JLabel("Points: ");
		lPoints.setFont(new Font("Georgia", Font.PLAIN, 16));
		lPoints.setBounds(440, 57, 60, 20);
		add(lPoints);
		
		//text for the guest to say what they want
		JLabel lRequest = new JLabel("Request");
		lRequest.setFont(new Font("Georgia", Font.PLAIN, 14));
		lRequest.setBounds(290, 35, 177, 55);
		add(lRequest);
		//guest request
		
		//SCREEN
		JPanel Screen = new JPanel();
		Screen.setBackground(new Color(192, 192, 192));
		Screen.setBounds(290, 116, 200, 150);
		add(Screen);
		Screen.setLayout(null);
		
		//amenity list
		JLabel poolScreen = new JLabel("Pool");
		poolScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		poolScreen.setBounds(75, 20, 80, 15);
		Screen.add(poolScreen);
		
		JLabel tennisScreen = new JLabel("Tennis Courts");
		tennisScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		tennisScreen.setBounds(75, 35, 80, 15);
		Screen.add(tennisScreen);
		
		JLabel pickleScreen = new JLabel("Pickleball");
		pickleScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		pickleScreen.setBounds(75, 50, 80, 15);
		Screen.add(pickleScreen);
		
		JLabel spaScreen = new JLabel("Spa");
		spaScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		spaScreen.setBounds(75, 65, 80, 15);
		Screen.add(spaScreen);
		
		JLabel deckScreen = new JLabel("Deck");
		deckScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		deckScreen.setBounds(75, 80, 80, 15);
		Screen.add(deckScreen);
		
		JLabel golfScreen = new JLabel("Golf Course");
		golfScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		golfScreen.setBounds(75, 95, 80, 15);
		Screen.add(golfScreen);
		
		JLabel drivingScreen = new JLabel("Driving Range");
		drivingScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		drivingScreen.setBounds(75, 110, 80, 15);
		Screen.add(drivingScreen);
		
		JLabel gymScreen = new JLabel("Gym");
		gymScreen.setFont(new Font("Georgia", Font.PLAIN, 12));
		gymScreen.setBounds(75, 125, 80, 15);
		Screen.add(gymScreen);
		
		//the guest photo
		JLabel screenIDphoto = new JLabel("");
		screenIDphoto.setIcon(null);
		screenIDphoto.setBounds(10, 22, 57, 66);
		Screen.add(screenIDphoto);
		
		//make it look like a real application on a computer
		JLabel FakeExit = new JLabel("X");
		FakeExit.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(0, 0, 0)));
		FakeExit.setOpaque(true);
		FakeExit.setForeground(new Color(255, 255, 255));
		FakeExit.setBackground(new Color(255, 0, 0));
		FakeExit.setBounds(190, 0, 12, 12);
		Screen.add(FakeExit);
		
		JLabel lblNewLabel_7 = new JLabel("[]");
		lblNewLabel_7.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(new Color(185, 157, 2));
		lblNewLabel_7.setBounds(180, 0, 10, 12);
		Screen.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("-");
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setForeground(new Color(0, 0, 0));
		lblNewLabel_8.setBackground(new Color(242, 206, 2));
		lblNewLabel_8.setBounds(170, 0, 10, 12);
		Screen.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Member Access");
		lblNewLabel_9.setBackground(new Color(62, 158, 255));
		lblNewLabel_9.setOpaque(true);
		lblNewLabel_9.setBounds(0, 0, 200, 12);
		Screen.add(lblNewLabel_9);
		
		//see whether the guest should even be at the club
		JLabel lblMem = new JLabel("Member");
		lblMem.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblMem.setBounds(10, 96, 50, 15);
		Screen.add(lblMem);
		
		JLabel statusScreen = new JLabel("Status");
		statusScreen.setFont(new Font("Georgia", Font.PLAIN, 16));
		statusScreen.setBounds(10, 111, 50, 20);
		Screen.add(statusScreen);
		
		//screen
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(m2_Complete_Mediation_GUI.class.getResource("/GUI/Img/ScreenBack.jpg")));
		lblNewLabel_10.setBounds(285, 115, 245, 160);
		add(lblNewLabel_10);
		//SCREEN
		
		//COMPUTER IMAGE
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(m2_Complete_Mediation_GUI.class.getResource("/GUI/Img/Monitor3.png")));
		lblNewLabel_5.setBounds(274, 86, 276, 251);
		add(lblNewLabel_5);
		//picture of a computer
		
		//the 'guest'
		JLabel img_guest = new JLabel("");
		img_guest.setBounds(40, 35, 230, 265);
		add(img_guest);
		
		//MEMBER CARD
		JPanel memberCard = new JPanel();
		memberCard.setBorder(new LineBorder(new Color(0, 0, 0)));
		memberCard.setOpaque(false);
		memberCard.setBackground(new Color(121, 241, 251));
		memberCard.setBounds(82, 316, 230, 120);
		add(memberCard);
		memberCard.setLayout(null);
		
		JLabel poolId = new JLabel("Pool");
		poolId.setForeground(new Color(0, 0, 0));
		poolId.setFont(new Font("Georgia", Font.PLAIN, 12));
		poolId.setBounds(80, 40, 50, 15);
		memberCard.add(poolId);
		
		JLabel tennisId = new JLabel("Tennis Courts");
		tennisId.setFont(new Font("Georgia", Font.PLAIN, 12));
		tennisId.setBounds(80, 55, 80, 15);
		memberCard.add(tennisId);
		
		JLabel pickleId = new JLabel("Pickleball");
		pickleId.setFont(new Font("Georgia", Font.PLAIN, 12));
		pickleId.setBounds(80, 70, 55, 15);
		memberCard.add(pickleId);
		
		JLabel spaId = new JLabel("Spa");
		spaId.setFont(new Font("Georgia", Font.PLAIN, 12));
		spaId.setBounds(170, 100, 50, 15);
		memberCard.add(spaId);
		
		JLabel deckId = new JLabel("Deck");
		deckId.setFont(new Font("Georgia", Font.PLAIN, 12));
		deckId.setBounds(170, 85, 50, 15);
		memberCard.add(deckId);
		
		JLabel golfId = new JLabel("Golf Course");
		golfId.setFont(new Font("Georgia", Font.PLAIN, 12));
		golfId.setBounds(80, 85, 90, 15);
		memberCard.add(golfId);
		
		JLabel drivingId = new JLabel("Driving Range");
		drivingId.setFont(new Font("Georgia", Font.PLAIN, 12));
		drivingId.setBounds(80, 100, 90, 15);
		memberCard.add(drivingId);
		
		JLabel gymId = new JLabel("Gym");
		gymId.setFont(new Font("Georgia", Font.PLAIN, 12));
		gymId.setBounds(170, 70, 50, 15);
		memberCard.add(gymId);
		
		JLabel cardIDphoto = new JLabel("");
		cardIDphoto.setIcon(null);
		cardIDphoto.setBounds(10, 45, 57, 66);
		memberCard.add(cardIDphoto);
		
		JLabel nameId = new JLabel("Name");
		nameId.setFont(new Font("Georgia", Font.PLAIN, 16));
		nameId.setBounds(10, 0, 100, 40);
		memberCard.add(nameId);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(192, 192, 192));
		lblNewLabel_4.setBounds(0, 0, 230, 120);
		memberCard.add(lblNewLabel_4);
		//MEMBER CARD
		
		//TITLE
		JLabel lblNewLabel = new JLabel("Complete Mediation");
		lblNewLabel.setBounds(205, 5, 90, 13);
		add(lblNewLabel);
		//TITLE
		Random gue = new Random();
		//next guest
		JButton nexBtn = new JButton("Next");
		nexBtn.setBounds(440, 330, 84, 30);
		add(nexBtn);
		nexBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nexBtn.setEnabled(false);
				bAllow.setEnabled(true);
				btnDeny.setEnabled(true);
				cardIDphoto.setIcon(g[i].Guest_Photo(g[i].pics, 1));
				img_guest.setIcon(g[i].Guest_Photo(g[i].pics, 0));
				nameId.setText(g[i].name);
				screenIDphoto.setIcon(g[i].Guest_Photo(g[i].pics, 1));
				if (g[i].access == 0) {
					statusScreen.setText("Void");
				}
				else {
					statusScreen.setText("Active");
				}
				p = logic.mRequest();
				switch (p) {
				case 0:
					lRequest.setText("I want the pool");
					break;
				case 1:
					lRequest.setText("I want to play tennis");
					break;
				case 2:
					lRequest.setText("Pickleball please");
					break;
				case 3:
					lRequest.setText("I need the spa");
					break;
				case 4:
					lRequest.setText("I want to use the deck");
					break;
				case 5:
					lRequest.setText("Golf course quickly");
					break;
				case 6:
					lRequest.setText("The driving range");
					break;
				case 7:
					lRequest.setText("Time for the gym");
					break;
				}
				/* TESTING
				System.out.println("GUI");
				System.out.println("Name: " + g[i].name);
				System.out.println("Permissions: " + g[i].permissions[0] + " " + g[i].permissions[1] + " " + g[i].permissions[2] + " " + g[i].permissions[3] + " " + g[i].permissions[4]);
				System.out.println("dPerm: " + g[i].dPerm[0] + " " + g[i].dPerm[1] + " " + g[i].dPerm[2] + " " + g[i].dPerm[3] + " " + g[i].dPerm[4]);
				*/
				//POOL
				if (g[i].permissions[0] == 0) {
					poolId.setForeground(Color.RED);
				}
				else {
					poolId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[0] == 0) {
					poolScreen.setForeground(Color.RED);
				}
				else {
					poolScreen.setForeground(Color.GREEN);
				}
				//TENNIS
				if (g[i].permissions[1] == 0) {
					tennisId.setForeground(Color.RED);
				}
				else {
					tennisId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[1] == 0) {
					tennisScreen.setForeground(Color.RED);
				}
				else {
					tennisScreen.setForeground(Color.GREEN);
				}
				//PICKLEBALL
				if (g[i].permissions[2] == 0) {
					pickleId.setForeground(Color.RED);
				}
				else {
					pickleId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[2] == 0) {
					pickleScreen.setForeground(Color.RED);
				}
				else {
					pickleScreen.setForeground(Color.GREEN);
				}
				//SPA
				if (g[i].permissions[3] == 0) {
					spaId.setForeground(Color.RED);
				}
				else {
					spaId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[3] == 0) {
					spaScreen.setForeground(Color.RED);
				}
				else {
					spaScreen.setForeground(Color.GREEN);
				}
				//DECK
				if (g[i].permissions[4] == 0) {
					deckId.setForeground(Color.RED);
				}
				else {
					deckId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[4] == 0) {
					deckScreen.setForeground(Color.RED);
				}
				else {
					deckScreen.setForeground(Color.GREEN);
				}
				//GOLF COURSE
				if (g[i].permissions[5] == 0) {
					golfId.setForeground(Color.RED);
				}
				else {
					golfId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[5] == 0) {
					golfScreen.setForeground(Color.RED);
				}
				else {
					golfScreen.setForeground(Color.GREEN);
				}
				//DRIVING RANGE
				if (g[i].permissions[6] == 0) {
					drivingId.setForeground(Color.RED);
				}
				else {
					drivingId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[6] == 0) {
					drivingScreen.setForeground(Color.RED);
				}
				else {
					drivingScreen.setForeground(Color.GREEN);
				}
				//GYM
				if (g[i].permissions[7] == 0) {
					gymId.setForeground(Color.RED);
				}
				else {
					gymId.setForeground(Color.GREEN);
				}
				if (g[i].dPerm[7] == 0) {
					gymScreen.setForeground(Color.RED);
				}
				else {
					gymScreen.setForeground(Color.GREEN);
				}
			}
		});
		
		bAllow = new JButton("Allow");
		bAllow.setBorderPainted(false);
		bAllow.setEnabled(false);
		bAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nexBtn.setEnabled(true);
				//System.out.println(g[i].permissions[p]);
				//System.out.println(g[i].name);
				//scoring
				if (g[i].dPerm[p] == 0) {
					points = points - 1;
				}
				else {
					points = points + 1;
				}
				//because java swing is a sad sad program
				String pop = Integer.toString(points);
					nPoints.setText(pop);
				i = gue.nextInt(3); //randomize guest
				g[i].dPerm = logic.New_Permissions(g[i].permissions, g[i].name);
				//my roommate was testing and lost her mind spamming the button
				//to see how low she could get the score
				bAllow.setEnabled(false);
				btnDeny.setEnabled(false);
			}
		});
		bAllow.setFont(new Font("Georgia", Font.PLAIN, 16));
		bAllow.setBackground(new Color(0, 255, 64));
		bAllow.setBounds(322, 430, 100, 50);
		add(bAllow);
		
		
		//btn
		btnDeny = new JButton("Deny"); //just enables the next button for now
		btnDeny.setEnabled(false);
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nexBtn.setEnabled(true);
				//scoring
				if (g[i].dPerm[p] == 1) {
					points = points - 1;
				}
				else {
					points = points + 1;
				}
				//because java swing is a sad sad program
				String pop = Integer.toString(points);
					nPoints.setText(pop);
				i = gue.nextInt(3); //randomize guest
				g[i].dPerm = logic.New_Permissions(g[i].permissions, g[i].name);
				bAllow.setEnabled(false);
				btnDeny.setEnabled(false);
			}
		});
		btnDeny.setBackground(new Color(255, 0, 0));
		btnDeny.setBorderPainted(false);
		btnDeny.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnDeny.setBounds(430, 430, 100, 50);
		add(btnDeny);
		
		//for when the guest status is void
		//erase the guest from existence by writing over their existence
		//kind of dark
		JButton bSecurity = new JButton("Security");
		bSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//goodbye person
				g[i].name = logic.New_Names(); //new name
				g[i].permissions = logic.New_Ammenities(); //new list of permissions
				g[i].dPerm = logic.New_Permissions(g[i].permissions, g[i].name); //new list of database permissions
				g[i].pics = logic.pi(); //new random number for guest photo
				g[i].access = logic.New_Access(); //new guest access
				
				nexBtn.setEnabled(true); //have to re-enable the button to trigger it
				nexBtn.doClick();
			}
		});
		bSecurity.setFont(new Font("Georgia", Font.BOLD, 16));
		bSecurity.setBounds(435, 5, 105, 30);
		add(bSecurity);
		
		//DECORATION
		JLabel Desk = new JLabel("");
		Desk.setHorizontalTextPosition(SwingConstants.CENTER);
		Desk.setHorizontalAlignment(SwingConstants.CENTER);
		Desk.setAlignmentY(1.0f);
		Desk.setOpaque(true);
		Desk.setBackground(new Color(128, 64, 0));
		Desk.setBounds(0, 300, 550, 200);
		add(Desk);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(166, 83, 0));
		lblNewLabel_2.setBounds(430, 0, 120, 300);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(m2_Complete_Mediation_GUI.class.getResource("/GUI/Img/ParkBack.jpg")));
		lblNewLabel_6.setBounds(2, 0, 465, 326);
		add(lblNewLabel_6);
		//DECORATION
		
		
		JLabel label = new JLabel("New label");
		label.setBounds(477, 185, 44, 12);
		add(label);
	}
}
