package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dataClass.Artikli;
import dataClass.Promet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;


public class Obracun {
	
	@FXML
	public DatePicker pocetakRazdoblja;
	
	@FXML
	public DatePicker krajRazdoblja;
	
	@FXML
	public Button btn_napravi_obracun;
	
	@FXML
	public Button btn_graficki_prikaz;
	
	@FXML
	public TableView tableViewObracun;
	
	@FXML
	public TableColumn tableColumnRazdoblje;
	
	@FXML
	public TableColumn tableColumnPromet;
	
	ObservableList<Promet> obracun = FXCollections.observableArrayList();

	private String naziv;
	private String kolicina;
	private String cijena;
	private String formatiranPocetakRazdoblja;
	private String formatiranKrajRazdoblja;
	
	// Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Obracun() {
    }
    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    	// inicijalizacija stupaca i prikaz
    	tableColumnPromet.setCellValueFactory(new PropertyValueFactory<Promet,Double>("iznos_prometa"));
    	tableColumnRazdoblje.setCellValueFactory(new PropertyValueFactory<Promet,String>("Razdoblje"));
    	tableViewObracun.getSelectionModel().setCellSelectionEnabled(true);
		tableViewObracun.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	
    	pocetakRazdoblja.setOnAction(event -> {
    	    LocalDate datum = pocetakRazdoblja.getValue();
    	    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	formatiranPocetakRazdoblja = datum.format(format);
    	});
    	
    	krajRazdoblja.setOnAction(event -> {
    		LocalDate datum = krajRazdoblja.getValue();
     	    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         	formatiranKrajRazdoblja = datum.format(format);
    	});
    	
    	btn_napravi_obracun.setOnAction(e -> {
    		tableViewObracun.setItems(getObracun(formatiranPocetakRazdoblja, formatiranKrajRazdoblja));
     	   });
    	
    	btn_graficki_prikaz.setOnAction(e -> {
    		//TO-DO aka za sutra :P
     	   });
}
    
    	

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;

    }
    
    private ObservableList<Promet> getObracun(String pocetakRazdoblja, String krajRazdoblja){
		List<Promet> obracunIzBaze = new ArrayList<Promet>();
		obracunIzBaze = bazaBlagajna.bazaCitajPromet(pocetakRazdoblja, krajRazdoblja);
				
		for (int i = 0;i < obracunIzBaze.size();i++){
			obracun.add(obracunIzBaze.get(i));
		}

		return obracun;
	}
    
   
}

