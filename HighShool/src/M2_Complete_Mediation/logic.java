package M2_Complete_Mediation;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class logic {
	//String[] gAmmenities = {"Pool", "Tennis_Courts", "Pickleball", "Spa", "Deck", "Golf_Course", "Driving_Range", "Gym", "Restaurant"};
	public static int mRequest() {
		Random ran = new Random();
		int req = ran.nextInt(8);
		return req;
	}
	
	public int answer(int p, int[] perm, int acc, int choice) { //get whether guest has permission, get player answer
		
		return 0;
	}
	
	//just repeating most of guest_log to write over old guests
	public static String New_Names () {
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
	
	public static int[] New_Ammenities () {
		Random random = new Random();
		String[] gAmmenities = {"Pool", "Tennis_Courts", "Pickleball", "Spa",
				"Deck", "Golf_Course", "Driving_Range", "Gym"};
		int[] gPerms = new int[8];
		int randomNumber = random.nextInt(254);
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
	
	public static int[] New_Permissions(int[] perm, String name) {
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
	
	public static int New_Access() {
		Random random = new Random();
		int randomNumber = random.nextInt(100);
		if (randomNumber > 30) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	//do not need to redo Icon class, just need to change pics value
	public static int pi() {
		Random ran = new Random();
		int randomNumber = ran.nextInt(8);
		return randomNumber;
	}
}
