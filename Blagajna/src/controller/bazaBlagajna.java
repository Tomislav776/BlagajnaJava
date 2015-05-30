package controller;

import dataClass.Racun;
import java.util.*;
import dataClass.Artikli;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

//import com.mysql.jdbc.Driver;

public class bazaBlagajna {
	Connection conn = null;
	
	public boolean Connect(String connectionUser, String connectionPassword) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Vezanje na bazu
			String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}

		finally {
			try { if (conn != null) conn.close();  } catch (SQLException e) { e.printStackTrace(); }
		}
		return true;
	}
	
	public boolean dodaj_artikl(String naziv, int kolicina, double cijena) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO artikli (naziv, kolicina, cijena) VALUES(?, ?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
			 stmt = conn.prepareStatement(SQL);
			stmt.setString(1, naziv);
			stmt.setInt(2, kolicina);
			stmt.setDouble(3, cijena);
			
 
			stmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
			return true;
	}
	
	//Dodavanje racuna u bazu 
	/**
	  Popravi datum 
	 **/
	public boolean dodaj_Racun(double iznos, String nazivLokala, String konobar, String datum) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO racuni (iznos, nazivLokala, konobar, datum) VALUES(?, ?, ?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				stmt = conn.prepareStatement(SQL);
			
				stmt.setDouble(1, iznos);
				stmt.setString(2, nazivLokala);
				stmt.setString(3, konobar);
				stmt.setString(4, datum); /** Brijem da netreba **/
			
			stmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
			return true;
	}
	
	
	public static boolean obrisi_artikl(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		  String SQL = "DELETE FROM artikli WHERE id = ?;";
		 
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
			 stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
 
			stmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
			return true;
	}
	
	//Cita artikle iz baze te ih vraca kao listu
	public static List<Artikli> bazaCitajArtikle() {
	    List<Artikli> artikli = new ArrayList<Artikli>();
	    PreparedStatement stmt = null;
		Connection conn = null;


	    try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				Connection connection = DriverManager.getConnection(connectionUrl, "root", "");
	        
				stmt = connection.prepareStatement("SELECT * FROM artikli;");
				ResultSet resultSet = stmt.executeQuery();
	    
	        while (resultSet.next()) {
	        	Artikli artikl = new Artikli(resultSet.getInt("id"),resultSet.getString("naziv"),resultSet.getInt("kolicina"),resultSet.getDouble("Cijena"));
	        	artikli.add(artikl);
	        }
	    
	}catch(Exception e){
		
	}finally {
		try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	    return artikli;
	}
	
	
	//Cita Racune iz baze i vraca ih ko listu
	public static List<Racun> bazaCitajRacune() {
	    List<Racun> racuni = new ArrayList<Racun>();
	    PreparedStatement stmt = null;
		Connection conn = null;

	    try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna?characterEncoding=utf8";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				Connection connection = DriverManager.getConnection(connectionUrl, "root", "");
	        
				stmt = connection.prepareStatement("SELECT * FROM racuni;");
				ResultSet resultSet = stmt.executeQuery();
	    
	        while (resultSet.next()) {
	        	Racun racun = new Racun(resultSet.getInt("id"),resultSet.getDouble("iznos"),resultSet.getString("nazivLokala"),resultSet.getString("konobar"), resultSet.getString("datum"));
	        	racuni.add(racun);
	        }
	    
	}catch(Exception e){
		
	}finally {
		try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	    return racuni;
	}

	
}