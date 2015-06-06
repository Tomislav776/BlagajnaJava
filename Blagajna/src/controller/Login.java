package controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Razred <code>Login</code> postavlja vizualne elemente na prozor Login te stvara datoteku s poèetnim postavkama korisnika.
 *
 */
public class Login {

	    @FXML
	    private TextField txt_user;
	    @FXML
	    private TextField txt_pass;
	    
	    @FXML
	    private Button btn_prijava;
	    @FXML
	    private Button btn_izlaz;
	    

	    // Reference to the main application.
	    private Main main;

	    /**
	     * Konstruktor.
	     * Konstruktor se poziva prije initialize() metode.
	     */
	    public Login() {
	    }

	    /**
	     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
	     * što se uèita fxml datoteka.
	     */
	    @FXML
	    private void initialize() {
	    	if (!(new File("Postavke.txt").isFile())){
    			postaviDefault();
    		}
    	
	    }

	    /**
	     * Poziva se iz Maina da bi se referencirao na samog sebe.
	     * @param main
	     */
	    public void setMainApp(Main main) {
	        this.main = main;

	    }
	    
	    public TextField user()
	    {
	    	return txt_user;
	    }
	    
	    public TextField pass()
	    {
	    	return txt_pass;
	    }
	    
	    public Button prijava()
	    {
	    	return btn_prijava;
	    }
	    
	    public Button izlaz()
	    {
	    	return btn_izlaz;
	    }
	    
	    /**
	     * Pri prvom pokretanju programa stvara Postvake.txt file te unutar njega postavlja poèetne postavke programa koje korisnik kasnije može mijenjati.
	     */
	    	public void postaviDefault(){
			
			try (BufferedWriter bw = new BufferedWriter(new PrintWriter("Postavke.txt"))) {
	                 
           		bw.write("Naziv lokala:");
	            bw.newLine();
           		bw.write("Lokal");
           		bw.newLine();
           		
           		bw.write("Valuta:");
	            bw.newLine();
           		bw.write(" kn");
           		bw.newLine();
           		
           		bw.write("Broj redaka:");
	                bw.newLine();
           		bw.write("7");
           		bw.newLine();
           		
           		bw.write("Broj stupaca:");
	                bw.newLine();
           		bw.write("4");
           		bw.newLine();
           		
           		bw.write("Vlasnik:");
                bw.newLine();
                bw.write("");
                bw.newLine();
       		
                bw.write("Adresa:");
                bw.newLine();
                bw.write("");
       			bw.newLine();
       		
       			bw.write("OIB:");
       			bw.newLine();
       			bw.write("");
       			bw.newLine();
       			
       			bw.write("WiFi:");
                bw.newLine();
                bw.write("");
                bw.newLine();
   		
   		
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }  
           	
		}
}
