package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

//import com.mysql.jdbc.Driver;

public class bazaBlagajna {
	
	public boolean Connect(String connectionUser, String connectionPassword) {
		Connection conn = null;
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
	
	public void izvediUpit() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//Vezanje na bazu
			String connectionUrl = "jdbc:mysql://localhost:3306/blagajna";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			
			//Rad s bazom
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM artikli");
			/*while (rs.next()) {
				int id = rs.getInt("id");
				String naziv = rs.getString("naziv");
				int kolicina = rs.getInt("kolicina");
				System.out.println("ID: " + id + ", Naziv: " + naziv+ ", Kolicina: " + kolicina);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}*/
	}
}