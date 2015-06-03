package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dataClass.Artikli;
import dataClass.Konobar;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Postavke{

	    @FXML
	    public TextField txtFieldNazivLokala;
	    
	    @FXML
	    public ChoiceBox<String> choiceBoxValuta;
	    
	    @FXML
	    public ChoiceBox<Integer> choiceBoxBrojRedaka;
	    
	    @FXML
	    public ChoiceBox<Integer> choiceBoxBrojStupaca;
	    
	    @FXML
	    public Button btnSpremiPostavke;
	    
	    @FXML
	    public Button btnIzadi;
	    
		private List<String> postavke = new ArrayList<String>();

	    

	    // Reference to the main application.
	    private Main main;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public Postavke() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	postaviVrijednostiKorisnika();
	    	for (int i=0;i<postavke.size();i++)
	    		System.out.println(postavke.get(i));
	    	
	    	/*btnSpremiPostavke.setOnAction (pok -> {
       	            
       	            	try (BufferedWriter bw = new BufferedWriter(new PrintWriter("Postavke.txt"))) {
       	                 bw.write(txtFieldNazivLokala.getText());
       	                 bw.newLine();
       		    	    } catch (IOException k) {
       		    	        k.printStackTrace();
       		    	    }      	

	    		});*/
	    	
	    	/*
	    	choiceBoxBrojRedaka.getItems().add(1);
	    	choiceBoxBrojRedaka.getItems().add(2);
	*/
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(Main main) {
	        this.main = main;

	    }
	    
		public void postaviVrijednostiKorisnika(){ 
		    BufferedReader reader = null;

			    try {
			        File file = new File("Postavke.txt");
			        reader = new BufferedReader(new FileReader(file));
			        
			        String line;
			        while ((line = reader.readLine()) != null) {
			            postavke.add(line);
			        }
			        
			
			    } catch (IOException e) {
			        e.printStackTrace();
			    } finally {
			        try {
			            reader.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			}
}
