package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;


public class Obracun {
	
	@FXML
	public Label Label_pocetakRazdoblja;
	
	@FXML
	public Label Label_krajRazdoblja;
	
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
	
	private Locale locale = RootLayout.getLocale();
	private ResourceBundle bundle = RootLayout.getBundle();
	
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
    	
    	Label_pocetakRazdoblja.setText(bundle.getString("Label_pocetakRazdoblja"));
    	Label_krajRazdoblja.setText(bundle.getString("Label_krajRazdoblja"));
    	btn_napravi_obracun.setText(bundle.getString("btn_napravi_obracun"));
    	btn_graficki_prikaz.setText(bundle.getString("btn_graficki_prikaz"));

    	// inicijalizacija stupaca i prikaz
    	tableColumnPromet.setCellValueFactory(new PropertyValueFactory<Promet,Double>("iznos_prometa"));
    	tableColumnPromet.setText(bundle.getString("tableColumnPromet"));
    	tableColumnRazdoblje.setCellValueFactory(new PropertyValueFactory<Promet,String>("Razdoblje"));
    	tableColumnRazdoblje.setText(bundle.getString("tableColumnRazdoblje"));
    	tableViewObracun.setPlaceholder(new Label(bundle.getString("tableViewObracun")));
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
    		try {
    	        // Load the fxml file and create a new stage for the popup.
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(Main.class.getResource("../views/ObracunGraf.fxml"));
    	        AnchorPane page = (AnchorPane) loader.load();
    	        Stage dialogStage = new Stage();
    	        dialogStage.setTitle("Obracun prometa - graf");
    	        dialogStage.initModality(Modality.WINDOW_MODAL);
    	        Scene scene = new Scene(page);
    	        dialogStage.setScene(scene);

    	        // Set the promet into the controller.
    	        ObracunGraf controller = loader.getController();
    	        controller.setPrometPodaci(obracun);

    	        dialogStage.show();

    	    } catch (IOException ee) {
    	        ee.printStackTrace();
    	    }
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

