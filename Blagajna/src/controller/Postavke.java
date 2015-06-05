package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Razred <code>Postavke</code> postavlja i inicijalizira vizualne elemente koji se nalaze unutar prozora Postavke.
 *
 */
public class Postavke extends Main{

	    @FXML
	    private TextField txtFieldNazivLokala;
	    
	    @FXML
	    private TextField txtFieldVlasnik;
	    
	    @FXML
	    private TextField txtFieldAdresa;
	    
	    @FXML
	    private TextField txtFieldOIB;
	    
	    @FXML
	    private TextField txtFieldWiFi;
	    
	    @FXML
	    private ChoiceBox<String> choiceBoxValuta;
	    
	    @FXML
	    private ChoiceBox<Integer> choiceBoxBrojRedaka;
	    
	    @FXML
	    private ChoiceBox<Integer> choiceBoxBrojStupaca;
	    
	    @FXML
	    private Button btnSpremiPostavke;
	    
	    @FXML
	    private Label labelNaziv;
	    
	    @FXML
	    private Label labelValuta;
	    
	    @FXML
	    private Label labelBrStupaca;
	    
	    @FXML
	    private Label labelBrRedaka;
	    
	    private Locale locale = RootLayout.getLocale();
		private ResourceBundle bundle = RootLayout.getBundle();
	  
	    
		private List<String> postavke = new ArrayList<String>();

	    

	    // Reference to the main application.
	    private Main main;

	    /**
	     * Konstruktor.
	     * Konstruktor se poziva prije initialize() metode.
	     */
	    public Postavke() {
	    }

	    /**
	     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
	     * što se uèita fxml datoteka.
	     */
	    @FXML
	    private void initialize() {
	    	
	    	labelNaziv.setText(bundle.getString("labelNaziv"));
	    	labelValuta.setText(bundle.getString("labelValuta"));
	    	labelBrStupaca.setText(bundle.getString("labelBrStupaca"));
	    	labelBrRedaka.setText(bundle.getString("labelBrRedaka"));
	    	btnSpremiPostavke.setText(bundle.getString("btnSpremiPostavke"));
	    	
	    	choiceBoxValuta.getItems().add(" kn");
	    	choiceBoxValuta.getItems().add(" usd");
	    	choiceBoxValuta.getItems().add(" €");
	    	
	    	for (int i=0;i<10;i++){
	    	choiceBoxBrojRedaka.getItems().add(i+1);
	    	choiceBoxBrojStupaca.getItems().add(i+1);
	    	}
	    	
	    	postaviVrijednostiKorisnika();
	    	
	    	btnSpremiPostavke.setOnAction (pok -> {
       	            	try (BufferedWriter bw = new BufferedWriter(new PrintWriter("Postavke.txt"))) {
       	                 
       	            		bw.write("Naziv lokala:");
          	                bw.newLine();
       	            		bw.write(txtFieldNazivLokala.getText());
       	            		bw.newLine();
       	            		
       	            		bw.write("Valuta:");
          	                bw.newLine();
       	            		bw.write(choiceBoxValuta.getValue());
       	            		bw.newLine();
       	            		
       	            		bw.write("Broj redaka:");
          	                bw.newLine();
       	            		bw.write(String.valueOf(choiceBoxBrojRedaka.getValue()));
       	            		bw.newLine();
       	            		
       	            		bw.write("Broj stupaca:");
          	                bw.newLine();
       	            		bw.write(String.valueOf(choiceBoxBrojStupaca.getValue()));
       	            		bw.newLine();
       	            		
       	            		bw.write("Vlasnik:");
       	            		bw.newLine();
       	            		bw.write(txtFieldVlasnik.getText());
       	            		bw.newLine();
       	        		
       	            		bw.write("Adresa:");
       	            		bw.newLine();
       	            		bw.write(txtFieldAdresa.getText());
       	        			bw.newLine();
       	        		
       	        			bw.write("OIB:");
       	        			bw.newLine();
       	        			bw.write(txtFieldOIB.getText());
       	        			bw.newLine();
       	        			
       	        			bw.write("WiFi:");
       	        			bw.newLine();
       	        			bw.write(txtFieldWiFi.getText());
       	        			bw.newLine();


       		    	    } catch (IOException k) {
       		    	        k.printStackTrace();
       		    	    }  
       	            	
       	            	Alert alert = new Alert(AlertType.CONFIRMATION);
       	        		alert.setTitle("Postavke");
       	        		alert.setHeaderText(null);
       	        		alert.setContentText("Trenutne postavke spremljene.");
       	        		refreshMainScreen();
       	        		alert.showAndWait();

	    		});
	    	
	
	    }

	    /**
	     * Poziva se iz Maina da bi se referencirao na samog sebe.
	     * @param main
	     */
	    public void setMainApp(Main main) {
	        this.main = main;

	    }
	    
	    /**
	     * Metoda èita iz datoteke Postavke.txt opcije korisnika te ih pridružuje vizualnim elementima na zaslonu.
	     */
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
			    
			    for (int i =0;i<postavke.size();i++){
			    	if (postavke.get(i).equals("Naziv lokala:"))
			    		txtFieldNazivLokala.setText(postavke.get(i+1));
			    	
			    	if (postavke.get(i).equals("Valuta:"))
			    		choiceBoxValuta.setValue(postavke.get(i+1));
			    	
			    	if (postavke.get(i).equals("Broj redaka:"))
			    		choiceBoxBrojRedaka.setValue(Integer.parseInt(postavke.get(i+1)));
			    	
			    	if (postavke.get(i).equals("Broj stupaca:"))
			    		choiceBoxBrojStupaca.setValue(Integer.parseInt(postavke.get(i+1)));
			    	
			    	if (postavke.get(i).equals("Vlasnik:"))
			    		txtFieldVlasnik.setText(postavke.get(i+1));
			    	
			    	if (postavke.get(i).equals("Adresa:"))
			    		txtFieldAdresa.setText(postavke.get(i+1));
			    	
			    	if (postavke.get(i).equals("OIB:"))
			    		txtFieldOIB.setText(postavke.get(i+1));
			    	
			    	if (postavke.get(i).equals("WiFi:"))
			    		txtFieldWiFi.setText(postavke.get(i+1));
			    	
			    	
			    }
			}
		
}
