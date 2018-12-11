import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

public class FlowerClub{
	public static void main (String[] args){
		startScreen();
		mainMenu();
	}

	public static void startScreen(){
		String disp = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX        WELCOME       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXX            TO  THE           XXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXX        SOUTHERN SIERRA       XXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXX             WILDFLOWER           XXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXX    XXXXXXX        CLUB      XXXXXXX    XXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXX        XXXXXXXXXXXXX       XXXXXXXXXXXX        XXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		System.out.println(disp);
	}

	public static void mainMenu(){
		SqliteDB db = new SqliteDB();
		Scanner input = new Scanner(System.in);
		int uIn;
		int fIn;
		int tin;
		while (true){
			System.out.println("  PLEASE SELECT AN OPTION:\n\n\t1) FLOWER SIGHTINGS\n\t2) UPDATE FLOWER INFORMATION\n\t3) NEW FLOWER SIGHTING\n\t4) TERMINATE PROGRAM\n"); 
			uIn = input.nextInt();
			//TERMINATE PROGRAM
			if (uIn == 4){
				closeScreen();
				break;
			}
			//NEW FLOWER SIGHTING
			else if(uIn == 3){
				System.out.println("\n  ADD A NEW FLOWER SIGHTING:\n");
				//USER CHOOSES A FLOWER FROM FLOWER TABLE
				System.out.println("\n  SELECT FLOWER SIGHTED:\n");
				db.listFlowers();
				int flor = input.nextInt();
				ArrayList list = db.getFlowers();
				String flo = list.get(flor - 1).toString();
				//USER CHOOSES A LOCATION FROM FEATURES TABLES
				System.out.println("\n  SELECT LOCATION SIGHTED:\n");
				db.listLocations();
				int loc = input.nextInt();
				ArrayList arr = db.getLocations();
				String place = arr.get(loc - 1).toString();
				//USER SUBMITS DATE FLOWER WAS SEEN
				System.out.println("\n  YEAR SIGHTED: \n");
				int y = input.nextInt();
				System.out.println("\n  MONTH SIGHTED: \n");
				int m = input.nextInt();
				System.out.println("\n  DAY SIGHTED: \n");
				int d = input.nextInt();
				String date = y + "-" + m + "-" + d;
				//USER SUBMITS NAME
				System.out.println("\n  WHAT IS YOUR NAME:\n");
				String name = input.nextLine();
				name = input.nextLine();
				db.insertSighting(name, place, date, flo);
			}
			//UPDATE FLOWER INFORMATION
			else if(uIn == 2){
				System.out.println("\n  SELECT TABLE TO UPDATE: \n\n\t1) SIGHTINGS\n\t2) FEATURES\n\t3) FLOWERS\n");
				tin = input.nextInt();
				if(tin == 1){

				}else if(tin == 2){

				}else if(tin == 3){

				}
			}
			//SELECT A FLOWER
			else if(uIn == 1){
				System.out.println("\n  SELECT A FLOWER: \n");
				db.listFlowers();
				ArrayList list = db.getFlowers();
				fIn = input.nextInt();
				db.dispSightings(list.get(fIn - 1).toString());
			}

		}
		//CLOSE SCANNER AND DATABASE
		input.close();
		db.closeConnection();
	}


	public static void closeScreen(){
		String disp = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX       THANK YOU      XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXX          FOR VISITING        XXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXX              THE             XXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXX        SOUTHERN SIERRA       XXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXX             WILDFLOWER           XXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXX    XXXXXXX        CLUB      XXXXXXX    XXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXX        XXXXXXXXXXXXX       XXXXXXXXXXXX        XXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		disp += "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
		System.out.println(disp);
	}
		

}