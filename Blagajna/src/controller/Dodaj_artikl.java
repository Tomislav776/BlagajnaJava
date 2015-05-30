package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataClass.Artikli;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Dodaj_artikl {
	@FXML
	private TextField txt_naziv;
	
	@FXML
	private TextField txt_kolicina;
	
	@FXML
	private TextField txt_cijena;
	
	@FXML
	private Button btn_unesi_artikl;
	
	@FXML
	private Button btn_obrisi;
	
	@FXML
	private Button btn_osvjezi;
	
	@FXML
	private TableView<Artikli> tableViewArtikli;
	
	@FXML
	private TableColumn tableColumnCijena;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnNaziv;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnKolicina;

	private String naziv;
	private String kolicina;
	private String cijena;
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Dodaj_artikl() {
    }
    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,String>("cijena"));
    	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,String>("kolicina"));
    	tableViewArtikli.setItems(getArtikli());
    }
    
    	@FXML
    	private void handleClickDodaj()
    	{
    		if(txt_naziv.getText().isEmpty() || txt_kolicina.getText().isEmpty() || txt_cijena.getText().isEmpty())
    		{
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Pripazite");
        		alert.setHeaderText(null);
        		alert.setContentText("Niste unijeli sve potrebne informacije za unos novog artikla.");

        		alert.showAndWait();
    		}
    		else{
    			naziv = txt_naziv.getText();
            	kolicina = txt_kolicina.getText();
            	cijena = txt_cijena.getText();
            	int kolicina_INT = Integer.parseInt(kolicina);
            	double cijena_Double = Double.parseDouble(cijena);
            	bazaBlagajna b = new bazaBlagajna();
            	if(b.dodaj_artikl(naziv, kolicina_INT, cijena_Double) == true)
            	{
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Uspje�no dodan artikl");
            		alert.setHeaderText(null);
            		alert.setContentText("Uspje�no ste dodali artikl.");

            		alert.showAndWait();
            	}
    		}
        	
    	}
    	
    	@FXML
    	private void osvjezi()
    	{
    		tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
        	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,String>("cijena"));
        	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,String>("kolicina"));
        	tableViewArtikli.setItems(getArtikli());
    	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;

    }
    
    private ObservableList<Artikli> getArtikli(){
		ObservableList<Artikli> artikli = FXCollections.observableArrayList();
		
		List<Artikli> artikliIzBaze = new ArrayList<Artikli>();
		artikliIzBaze = bazaBlagajna.bazaCitajArtikle();
				
		for (int i =0;i < artikliIzBaze.size();i++){
			artikli.add(artikliIzBaze.get(i));
		}

		return artikli;
	}
}
