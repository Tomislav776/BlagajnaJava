package controller;

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
			String connectionUrl = "jdbc:mysql://localhost:3306/blagajna";
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
	
	public boolean dodaj_artikl(String naziv, int kolicina) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO artikli (naziv, kolicina) VALUES(?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
			 stmt = conn.prepareStatement(SQL);
			stmt.setString(1, naziv);
			stmt.setInt(2, kolicina);
			
 
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
	
	/*public void uzmi_artikl(int id) {
		Statement stmt = null;
		ResultSet res = null;
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				String connectionUrl = "jdbc:mysql://localhost:3306/blagajna";
				conn = DriverManager.getConnection(connectionUrl, "root", "");
			int i=0;
			
				do{
					String SQL = "SELECT * FROM artikli WHERE id="+i+";";
					++i;
					
					stmt = conn.createStatement();
					res=stmt.executeQuery();
					stmt.setString(1, naziv);
					stmt.setInt(2, kolicina);
				} while();
 
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
			return true;
	}*/
	
	//Funkcija koja prima bilo koji query ko string i daje ResultSet
	
	public ResultSet bazaCitaj(String query){
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			String connectionUrl = "jdbc:mysql://localhost:3306/blagajna";
			conn = DriverManager.getConnection(connectionUrl, "root", "");
		
				stmt = conn.createStatement();
				res=stmt.executeQuery(query);
	
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}*/
		
		return res;
	}
	
}