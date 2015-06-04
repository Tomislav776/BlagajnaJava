package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import dataClass.Promet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
     * Konstruktor.
     * Konstruktor se poziva prije initialize() metode.
     */
    public Obracun() {
    }
    

    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * što se uèita fxml datoteka.
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
     * Poziva se iz Maina da bi se referencirao na samog sebe.
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;

    }
    
    /**
	 * Ovom metodom se spajamo na bazu podataka i išèitavamo promet za traženo razdoblje.
	 * @param pocetakRazdoblja Prvi parametar je datum u obliku Stringa koji oznaèava poèetak traženog razdoblja.
	 * @param krajRazdoblja Drugi parametar je datum u obliku Stringa koji oznaèava završetak traženog razdoblja.
	 * @return Vraæa ObservableList prometa iz baze.
	 */
    private ObservableList<Promet> getObracun(String pocetakRazdoblja, String krajRazdoblja){
		List<Promet> obracunIzBaze = new ArrayList<Promet>();
		obracunIzBaze = bazaBlagajna.bazaCitajPromet(pocetakRazdoblja, krajRazdoblja);
				
		for (int i = 0;i < obracunIzBaze.size();i++){
			obracun.add(obracunIzBaze.get(i));
		}

		return obracun;
	}
    
   
}

