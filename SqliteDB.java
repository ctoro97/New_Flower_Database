import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.PreparedStatement;

public class SqliteDB{
	Connection c = null;
	Statement stmt = null;
	String url = "jdbc:sqlite:C:/Users/toroc/Database_Flower/flowers.db";

	//Method connects to the database
	SqliteDB(){
		try{
			c = DriverManager.getConnection(url);

		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	//Methods lists all the flowers by common name
	public void listFlowers(){
		try{
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COMNAME FROM FLOWERS ORDER BY COMNAME ASC");
			String oPut = "\n";
			int i = 1;
			while(rs.next()){
				//int id = rs.getInt("id");
				//String flor = rs.getString("COMNAME");
				oPut += "\t" + i + ")\t" + rs.getString("COMNAME") + "\n";
				i++;
				//String gen = rs.getString("GENUS");
				//String spec = rs.getString("SPECIES");
				//System.out.println(gen + " " + spec + " " + flor + "\n");
		
			}
			System.out.println(oPut);
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());	
		}
	}

	//Method returns arraylist for flowers
	public ArrayList getFlowers(){
		ArrayList al = new ArrayList();
		try{
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COMNAME FROM FLOWERS ORDER BY COMNAME ASC");
			while(rs.next()){
				al.add(rs.getString("COMNAME"));
			}
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return al;
	}

	//Method lists all the locations by location
	public void listLocations(){
		try{
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT LOCATION FROM FEATURES ORDER BY LOCATION ASC");
			String oPut = "\n";
			int i = 1;
			while(rs.next()){
				oPut += "\t" + i + ")\t" + rs.getString("LOCATION") + "\n";
				i++;		
			}
			System.out.println(oPut);
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());	
		}
	}

	//Method returns arraylist of locations
	public ArrayList getLocations(){
		ArrayList al = new ArrayList();
		try{
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT LOCATION FROM FEATURES ORDER BY LOCATION ASC");
			while(rs.next()){
				al.add(rs.getString("LOCATION"));
			}
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return al;
	}

	//Method displays the 10 most recent sightings of flower selected
	public void dispSightings(String x){
		try{
			this.stmt = c.createStatement();
			String sequel = "SELECT SIGHTED, LOCATION, PERSON FROM SIGHTINGS WHERE NAME = '" + x + "' ORDER BY SIGHTED DESC LIMIT 10;";
			ResultSet rs  = stmt.executeQuery(sequel);
			System.out.println("\t10 MOST RECENT SIGHTINGS FOR " + x.toUpperCase() + ": ");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("\tSIGHTED\t\tLOCATION\t\tPERSON");
			System.out.println("-------------------------------------------------------------------");
			while(rs.next()){
				System.out.println("\t" + rs.getString("SIGHTED") + "\t" + rs.getString("LOCATION") + "\t\t" + rs.getString("PERSON") + "\n");
			}
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	//Method updates tuple in flower table
	public void upFlower(String x, String gen, String spec){
		String sequel = "UPDATE FLOWERS SET GENUS = ? , SPECIES = ? WHERE COMNAME = '" + x + "'";
		try{
			this.stmt = c.createStatement();
			PreparedStatement pstmt = c.prepareStatement(sequel);
			pstmt.setString(1, gen);
			pstmt.setString(2, spec);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

	//Method updates tuple in features table
	public void upFeatures(String x, String cla, String lat, String lon, String map, String elev){
		String sequel = "UPDATE FEATURES SET CLASS = ?, LATITUDE = ?, LONGITUDE = ?, MAP = ?, ELEV = ? WHERE LOCATION = '" + x + "'";
		try{
			this.stmt = c.createStatement();
			PreparedStatement pstmt = c.prepareStatement(sequel);
			pstmt.setString(1, cla);
			pstmt.setString(2, lat);
			pstmt.setString(3, lon);
			pstmt.setString(4, map);
			pstmt.setString(5, elev);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

	//Method updates tuple in features table
	public void upSightings(String sn, String ln, String person, String sighted){
		String sequel = "UPDATE SIGHTINGS SET PERSON = ?, SIGHTED = ? WHERE NAME = '" + sn + "' AND LOCATION = '" + ln + "'";
		try{
			this.stmt = c.createStatement();
			PreparedStatement pstmt = c.prepareStatement(sequel);
			pstmt.setString(1, person);
			pstmt.setString(2, sighted);
			pstmt.executeUpdate();			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}

	//Method inserts new tuple into Sightings table
	public void insertSighting(String name, String location, String date, String flower){
		try{
			String sequel = "INSERT INTO SIGHTINGS (NAME, PERSON, LOCATION, SIGHTED) VALUES (?,?,?,?)";
			PreparedStatement pstmt = c.prepareStatement(sequel);
			pstmt.setString(1, flower);
			pstmt.setString(2, name);
			pstmt.setString(3, location);
			pstmt.setString(4, date);
			pstmt.execute();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	//Method closes the database
	public void closeConnection(){
		try{
			c.close();
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}