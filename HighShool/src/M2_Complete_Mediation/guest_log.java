package M2_Complete_Mediation;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class guest_log {
	Random ran = new Random();
	int randomNumber = ran.nextInt(8);
	String name = Guest_Names();
	int[] permissions = Guest_Ammenities();
	int[] dPerm = Database_Permissions(permissions, name);
	int access = Guest_Access();
	int pics = randomNumber;
	//String x = pr(permissions, dPerm, name, per);
	
	public String Guest_Names () {
		Random random = new Random();
		String[] fName = {"Ava", "Amelia", "Aiden", "Anthony", "Alex", "Avery", 
				"Bella", "Brooke", "Blake", "Brandon", "Bailey", "Blake", 
				"Chloe", "Claire", "Connor", "Carter", "Casey", "Cameron", 
				"Daisy", "Diana", "Daniel", "David", "Dakota", "Drew", 
				"Emma", "Evelyn", "Ethan", "Ezra", "Eden", "Emery", 
				"Fiona", "Felicity", "Felix", "Finley", "Frankie", 
				"Grace", "Geminitay", "Gemma", "George", "Gavin", "Gray", "Gale", 
				"Hannah", "Hazel", "Henry", "Hunter", "Harley", "Hayden",
				"Isabella", "Ivy", "Isaac", "Ivan", "Ivory", 
				"Julia", "Jasmine", "Joshua", "Jaxon", "Jade", "Jordan",
				"Kayla", "Kiara", "Kevin", "Kendall", 
				"Luna", "Layla", "Lucas", "Logan", "Lincoln", "Leslie",
				"Mia", "Maya", "Matthew", "Mason", "Morgan", "Micah", 
				"Natalie", "Nicole", "Noah", "Nora", "Noel", "Nova",
				"Olivia", "Owen", "Oakley", "Orion", 
				"Penelope", "Phoebe", "Patrick", "Preston", "Peyton",
				"Quinn", "Rachel", "Rebecca", "Ryan", "Robert", "Riley", 
				"Sophia", "Scarlett", "Sebastian", "Sydney", "Sam",
				"Taylor", "Thomas", "Trevor", "Terry", "Ursa", "Uriel", 
				"Victoria", "Vanessa", "Victor", "Vance",
				"Willow", "Wendy", "William", "Wyatt", 
				"Xena", "Xander", "Yvette", "York", 
				"Zara", "Zelda", "Zane"};
				//122 names
		int randomNumber = random.nextInt(121);
		return fName[randomNumber];
	}
	
	public int[] Guest_Ammenities () {
		Random random = new Random();
		String[] gAmmenities = {"Pool", "Tennis_Courts", "Pickleball", "Spa",
				"Deck", "Golf_Course", "Driving_Range", "Gym"};
		int[] gPerms = new int[8];
		int randomNumber = random.nextInt(256);
		int per = randomNumber % 2;
		int nex = randomNumber / 2;
		gPerms[0] = per;
		for (int i = 1; i < 8; i++) {
			per = nex % 2;
			nex = nex / 2;
			gPerms[i] = per;
		}
		return gPerms; //0 = deny, 1 = allow
	}
	
	public int[] Database_Permissions(int[] perm, String name) {
		/* TESTING
		System.out.println("Database_Permissions");
		System.out.println("Name: " + name);
		*/
		
		int[] per = perm.clone();
		//System.out.println("cloned array: " + per[0] + " " + per[1] + " " + per[2] + " " + per[3] + " " + per[4] + " " + per[5] + " " + per[6] + " " + per[7]);
		Random num = new Random();
		int c1 = 10;
		int c2 = 10;
		int c3 = 10;
		int c4 = 10;
		
		int numch = num.nextInt(5);
		if (numch == 0) {
			return per;
		}
		if (numch >= 1) {
			c1 = num.nextInt(8);
			if (per[c1] == 0) {
				per[c1] = 1;
			}
			else {
				per[c1] = 0;
			}
		}
		if (numch >= 2) {
			c2 = num.nextInt(8);
			if (per[c2] == 0) {
				per[c2] = 1;
			}
			else {
				per[c2] = 0;
			}
		}
		if (numch >= 3) {
			c3 = num.nextInt(8);
			if (per[c3] == 0) {
				per[c3] = 1;
			}
			else {
				per[c3] = 0;
			}
		}
		if (numch == 4) {
			c4 = num.nextInt(8);
			if (per[c4] == 1) {
				per[c4] = 0;
			}
		}
		/* TESTING
		System.out.println("after change: " + per[0] + " " + per[1] + " " + per[2] + " " + per[3] + " " + per[4] + " " + per[5] + " " + per[6] + " " + per[7]);
		System.out.println("the c's: " + c1 + " " + c2 + " " + c3);
		*/
		return per;
	}
	
	public int Guest_Access() {
		Random random = new Random();
		int randomNumber = random.nextInt(101);
		if (randomNumber > 30) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public Icon Guest_Photo(int p, int j) {
		if (p == 0) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman1,0-Picsart-BackgroundRemover.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman1.0.2.png"));
			}	
		}
		else if (p == 1) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman2.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman2.1.png"));
			}
		}
		else if (p == 2) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman3.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman3.1.png"));
			}
		}
		else if (p == 3) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman4.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman4.1.png"));
			}
		}
		else if (p == 4) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman5.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman5.1.png"));
			}
		}
		else if (p == 5) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman6.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman6.1.png"));
			}
		}
		else if (p == 6) {
			if (j == 0) {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman7.png"));
			}
			else {
				return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman7.1.png"));
			}
		}
		
		return new ImageIcon(guest_log.class.getResource("/GUI/Img/woman1.0.2.png"));
	}
	
	/*TESTING
	public String pr (int[] p, int[] dp, String name, int[] per) {
		System.out.println("TEST TEST TEST");
		System.out.println("Name: " + name);
		System.out.println("Permissions: " + p[0] + " " + p[1] + " " + p[2] + " " + p[3] + " " + p[4]);
		System.out.println("dPerm: " + dp[0] + " " + dp[1] + " " + dp[2] + " " + dp[3] + " " + dp[4]);
		System.out.println("TEST TEST TEST");
		return null;
	}
	*/
}

