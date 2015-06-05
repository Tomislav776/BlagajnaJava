package controller;

import dataClass.Konobar;
import dataClass.Promet;
import dataClass.Racun;

import java.util.*;

import dataClass.Artikli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mysql.jdbc.Driver;

/**
 * Razred <code>bazaBlagajna</code> omogu�ava rad sa bazom podataka.
 * Unutar razreda napravljene su metode i funkcionalnosti sa kojima se aplikacija spaja sa bazom podataka te stavlja i uzima potrebne podatke iz tablica unutar baze podataka.
 */
public class bazaBlagajna {
	Connection conn = null;
	static String connectionUrl = "jdbc:mysql://92.222.18.69:3306/blagajna?characterEncoding=utf8";
	
	/**
	 * Ovom metodom se spajamo na bazu podataka.
	 * @param connectionUser Prvi parametar korisni�ko ime koje se koristi pri konekciji s bazom.
	 * @param connectionPassword Drugi parametar lozinka koja se koristi za konekciju s bazom.
	 * @return <code>true</code> ako je konekcija s bazom uspje�na, ina�e vra�a <code>false</code>
	 */
	public boolean Connect(String connectionUser, String connectionPassword) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Vezanje na bazu
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		finally {
			try { if (conn != null) conn.close();  } catch (SQLException e) { e.printStackTrace(); }
		}
		return true;
	}
	
	/**
	 * Metoda prima 3 argumenta, argumente funkcija pohranjuje u bazu podataka unutar tablice artikli.
	 * @param naziv Ime artikla.
	 * @param kolicina Koliko artikala ima.
	 * @param cijena Cijena artikla.
	 * @return ukoliko je uspje�no pohranjen artikl vra�a <code>true</code> ina�e <code>false</code>.
	 */
	public boolean dodaj_artikl(String naziv, int kolicina, double cijena) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO artikli (naziv, kolicina, cijena) VALUES(?, ?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
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
	 * Metoda prima 3 argumenta, argumente funkcija pohranjuje u bazu podataka unutar tablice racuni.
	 * Iako tablica ra�un ima i stupac datum, ne treba ga proslijediti funkciji ve� samo unutar baze postaviti da taj stupac automatski postavlja vrijednost sa CURRENT_TIMESTAMP.
	 * @param iznos Ukupan iznos ra�una.
	 * @param nazivLokala Naziv va�eg lokala.
	 * @param konobar Ime konobara.
	 * @return ukoliko je uspje�no pohranjen ra�un vra�a <code>true</code> ina�e <code>false</code>.
	 */
	public boolean dodaj_Racun(double iznos, String nazivLokala, String konobar) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO racuni (iznos, nazivLokala, konobar) VALUES(?, ?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				stmt = conn.prepareStatement(SQL);
			
				stmt.setDouble(1, iznos);
				stmt.setString(2, nazivLokala);
				stmt.setString(3, konobar);
				//ipak netreba noobe
			
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
	
	/**
	 * Metoda bri�e artikl iz baze podataka. Kako bi znala koji atikl treba izbrisati predajemo joj primarni klju�.
	 * @param id Primarni klju� artikla.
	 * @return Ukoliko je uspje�no izbrisan artikl vra�a <code>true</code> ina�e <code>false</code>.
	 */
	public static boolean obrisi_artikl(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		  String SQL = "DELETE FROM artikli WHERE id = ?;";
		 
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
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
	/**
	 * Metoda vra�a unutar liste sve artikle iz tablice artikli koja se nalazi u bazi podataka.
	 * @return <code>List<Artikli></code> Lista sa svim artiklima.
	 */
	public static List<Artikli> bazaCitajArtikle() {
	    List<Artikli> artikli = new ArrayList<Artikli>();
	    PreparedStatement stmt = null;
		Connection conn = null;


	    try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				conn = DriverManager.getConnection(connectionUrl, "root", "");
	        
				stmt = conn.prepareStatement("SELECT * FROM artikli;");
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
	/**
	 * Metoda vra�a unutar liste sve ra�une iz tablice racuni koja se nalazi u bazi podataka.
	 * @return <code>List<Racun></code> Lista sa svim ra�unima.
	 */
	public static List<Racun> bazaCitajRacune() {
	    List<Racun> racuni = new ArrayList<Racun>();
	    PreparedStatement stmt = null;
		Connection conn = null;

	    try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
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
	
	//Cita promete iz baze te ih vraca kao listu
	/**
	 * Metoda prima po�etak i kraj radoblja kao String te vra�a ukupan promet to jest ukupan iznos svih napla�enih ra�una u navedenom razdoblju.
	 * @param pocetakRazdoblja Po�etni datum od kojeg �elimo saznati promet.
	 * @param krajRazdoblja Zavr�ni datum do kojeg �elimo saznati promet.
	 * @return List<Promet> vra�a listu koja u sebi sadr�i ukupan promet i navedeno razdoblje. 
	 */
		public static List<Promet> bazaCitajPromet(String pocetakRazdoblja, String krajRazdoblja) {
		    List<Promet> promet = new ArrayList<Promet>();
		    PreparedStatement stmt = null;
			Connection conn = null;
			String razdoblje = "Od " + pocetakRazdoblja + " do " + krajRazdoblja;
			double ukupanIznosPrometa = 0;

		    try{
		    		Class.forName("com.mysql.jdbc.Driver").newInstance();
					//Vezanje na bazu
					conn = DriverManager.getConnection(connectionUrl, "root", "");
		        
					stmt = conn.prepareStatement("SELECT * FROM racuni WHERE datum BETWEEN ? AND ?");
					stmt.setString(1, pocetakRazdoblja);
					stmt.setString(2, krajRazdoblja);
					ResultSet resultSet = stmt.executeQuery();
		    
		        while (resultSet.next()) {
		        	ukupanIznosPrometa += resultSet.getDouble("iznos");
		        }
		        
		        Promet promet1 = new Promet(ukupanIznosPrometa, razdoblje);
	        	promet.add(promet1);
		    
		}catch(Exception e){
			
		}finally {
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		    return promet;
		}
	
	
	//Konobar upis i ispis iz baze
		/**
		 * Metoda vra�a unutar liste sve konobare iz tablice konobari koja se nalazi u bazi podataka.
		 * @return List<Konobar> Lista sa svim konobarima.
		 */
	public static List<Konobar> bazaCitajKonobar() {
	    List<Konobar> konobari = new ArrayList<Konobar>();
	    PreparedStatement stmt = null;
		Connection conn = null;


	    try{
	    		Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				Connection connection = DriverManager.getConnection(connectionUrl, "root", "");
	        
				stmt = connection.prepareStatement("SELECT * FROM konobar;");
				ResultSet resultSet = stmt.executeQuery();
	    
	        while (resultSet.next()) {
	        	Konobar konobar = new Konobar(resultSet.getInt("id"),resultSet.getString("naziv"),resultSet.getInt("satnica"));
	        	konobari.add(konobar);
	        }
	    
	}catch(Exception e){
		
	}finally {
		try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	    return konobari;
	}
	
	/**
	 * Metoda dodaje konobara u tablicu konobari unutar baze podatak.
	 * @param imeKonobar Ime konobara
	 * @return Ukoliko je uspje�no dodan konobar vra�a <code>true</code>, ina�e <code>false</code>.
	 */
	public boolean dodaj_konobar(String imeKonobar) {
		PreparedStatement stmt = null;
		  String SQL = "INSERT INTO konobar (naziv, satnica) VALUES(?, ?);";
		 
	
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				conn = DriverManager.getConnection(connectionUrl, "root", "");
				stmt = conn.prepareStatement(SQL);
			
				stmt.setString(1, imeKonobar);
				stmt.setInt(2, 3);
				
			
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
	
	/**
	 * Metoda bri�e konobara iz baze podataka po primarnom klju�u.
	 * @param id Primarni klju� konobara kojeg �elimo izbrisati.
	 * @return Ukoliko je konobar uspje�no izbrisan vra�a <code>true</code>, ina�e <code>false</code>.
	 */
	public static boolean obrisi_konobar(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		  String SQL = "DELETE FROM konobar WHERE id = ?;";
		 
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
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
	
	//Smanjuje kolicinu artikla
	/**
	 * Metoda smanjuje koli�inu artikala u tablici artikli iz baze podataka.
	 * @param id Primarni klju� artikla kojem �elimo smanjiti koli�inu.
	 * @param kolicina Za koliko �elimo smanjiti koli�inu odre�enog artikla.
	 * @return Ukoliko je koli�ina artikla promijenjena vra�a <code>true</code>, ina�e <code>false</code>.
	 */
	public boolean smanjiKolicinuArtikla(int id, int kolicina) {
		PreparedStatement stmt = null;
		  String SQL = " UPDATE artikli SET kolicina=kolicina-? WHERE id=?";
		 
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Vezanje na bazu
				conn = DriverManager.getConnection(connectionUrl, "root", "");
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, kolicina);
			stmt.setInt(2, id);
			
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

	
}