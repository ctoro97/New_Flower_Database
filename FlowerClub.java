import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;


public class FlowerClub{
//Main method
	public static void main (String[] args){
		startScreen();
		mainMenu();
	}

//Simple Command Line Startup Screen
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
		//Connect to database
		SqliteDB db = new SqliteDB();
		//Create Scanner
		Scanner input = new Scanner(System.in);
		int uIn;
		int fIn;
		int tin;
		//LOOP FOR MENU
		while (true){
			System.out.println("  PLEASE SELECT AN OPTION:\n\n\t1) FLOWER SIGHTINGS\n\t2) UPDATE FLOWER INFORMATION\n\t3) NEW FLOWER SIGHTING\n\t4) TERMINATE PROGRAM\n"); 
			uIn = userIn();
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
				int flor = userIn();
				ArrayList list = db.getFlowers();
				if(flor > 0 && flor < list.size()){
					String flo = list.get(flor - 1).toString();
					//USER CHOOSES A LOCATION FROM FEATURES TABLES
					System.out.println("\n  SELECT LOCATION SIGHTED:\n");
					db.listLocations();
					int loc = userIn();
					ArrayList arr = db.getLocations();
					if(loc > 0 && loc < arr.size()){
						String place = arr.get(loc - 1).toString();
						//USER SUBMITS DATE FLOWER WAS SEEN
						System.out.println("\n  YEAR SIGHTED: \n");
						int y = userIn();
						System.out.println("\n  MONTH SIGHTED: \n");
						int m = userIn();
						System.out.println("\n  DAY SIGHTED: \n");
						int d = userIn();
						String date = y + "-" + m + "-" + d;
						//USER SUBMITS NAME
						System.out.println("\n  WHAT IS YOUR NAME:\n");
						String name = input.nextLine();
						name = input.nextLine();
						db.insertSighting(name, place, date, flo);
					}else{
						System.out.println("\n OPTION DOES NOT EXIST!!  \n");
					}
				}
				else{
					System.out.println("\n  OPTION DOES NOT EXIST!! \n");
				}
				
			}
			//UPDATE FLOWER INFORMATION
			else if(uIn == 2){
				System.out.println("\n  SELECT TABLE TO UPDATE: \n\n\t1) SIGHTINGS\n\t2) FEATURES\n\t3) FLOWERS\n");
				tin = userIn();
				if(tin == 1){
					System.out.println("\n  SELECT FLOWER TO UPDATE: \n");
					db.listFlowers();
					int upSightO = userIn();
					ArrayList flows = db.getFlowers();
					if(upSightO > 0 && upSightO < flows.size()){
						String dsighto = flows.get(upSightO - 1).toString();
						System.out.println("\n  SELECT LOCATION TO UPDATE: \n");
						db.listLocations();
						int upSightT = userIn();
						ArrayList nloc = db.getLocations();
						if(upSightT > 0 && upSightT < nloc.size()){
							String dsightt = nloc.get(upSightT - 1).toString();
							System.out.println("\n  SIGHTED BY: \n");
							String sname = input.nextLine();
							//sname = input.nextLine();
							System.out.println("\n  YEAR SIGHTED: \n");
							int ye = userIn();
							System.out.println("\n  MONTH SIGHTED: \n");
							int mo = userIn();
							System.out.println("\n  DAY SIGHTED: \n");
							int da = userIn();
							String date = ye + "-" + mo + "-" + da;
							db.upSightings(dsighto, dsightt, sname, date);
						}else{
							System.out.println("\n  OPTION DOES NOT EXIST!! \n");
						}
					}else{
						System.out.println("\n  OPTION DOES NOT EXIST!! \n");
					}
				}else if(tin == 2){
					System.out.println("\n  SELECT LOCATION TO UPDATE: \n");
					db.listLocations();
					int upfeat = userIn();
					ArrayList lugar = db.getLocations();
					if(upfeat > 0 && upfeat < lugar.size()){
						String dfeat = lugar.get(upfeat - 1).toString();
						System.out.println("\n  CLASS NAME: \n");
						String cname = input.next();
						System.out.println("\n  LATITUDE: \n");
						String lname = input.next();
						System.out.println("\n  LONGITUDE: \n");
						String loname = input.next();
						System.out.println("\n  MAP NAME: \n");
						String mname = input.next();
						System.out.println("\n  ELEVATION: \n");
						String elname = input.next();
						db.upFeatures(dfeat, cname, lname, loname, mname, elname);
					}else{
						System.out.println("\n  OPTION DOES NOT EXIST!! \n");
					}

				}else if(tin == 3){
					System.out.println("\n  SELECT FLOWER TO UPDATE: \n");
					db.listFlowers();
					int upflor = userIn();
					ArrayList flores = db.getFlowers();
					if(upflor > 0 && upflor < flores.size()){
						String dflow = flores.get(upflor - 1).toString();
						System.out.println("\n  GENUS NAME: \n");
						String gen = input.next();
						System.out.println("\n  SPECIES NAME: \n");
						String nam = input.next();											
						db.upFlower(dflow, gen, nam);
					}else{
						System.out.println("\n  OPTION DOES NOT EXIST!! \n");
					}
				}
			}
			//SELECT A FLOWER
			else if(uIn == 1){
				System.out.println("\n  SELECT A FLOWER: \n");
				db.listFlowers();
				ArrayList list = db.getFlowers();
				fIn = userIn();
				if(fIn > 0 && fIn < list.size()){
					db.dispSightings(list.get(fIn - 1).toString());
				}
				else{
					System.out.println("\n  OPTION DOES NOT EXIST!! \n");
				}
			}

			else{
				System.out.println("\n  OPTION NOT VALID!! PLEASE TRY AGAIN: \n");
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


//METHOD FOR USER INPUTING INTEGERS
	public static int userIn(){
		Scanner x = new Scanner(System.in);
		int y = -1;
		boolean loop = true;
		do{
			try{
				y = x.nextInt();
				loop = false;
			}catch(Exception e){
				System.out.println("\nINVALID INPUT!! PLEASE ENTER VALUE AGAIN: \n");
				x.nextLine();
			}
		}while(loop);
		return y;
	}
		

}