package controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {
//tetsiranje
	    @FXML
	    public TextField txt_user;
	    @FXML
	    public TextField txt_pass;
	    
	    @FXML
	    public Button btn_prijava;
	    @FXML
	    public Button btn_izlaz;
	    

	    // Reference to the main application.
	    private Main main;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public Login() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	if (!(new File("Postavke.txt").isFile())){
    			postaviDefault();
    		}
    	
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(Main main) {
	        this.main = main;

	    }
	    
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
	    	    } catch (IOException k) {
	    	        k.printStackTrace();
	    	    }  
           	
		}
}
