package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;


public class Dodaj_artikl extends Main{
	
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
	private TableView<Artikli> tableViewArtikli;
	
	@FXML
	private TableColumn tableColumnCijena;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnNaziv;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnKolicina;
	
	@FXML
    private TextField filter;
	
	ObservableList<Artikli> artikli = FXCollections.observableArrayList();

	private String naziv;
	private String kolicina;
	private String cijena;
	
	private Locale locale = RootLayout.getLocale();
	private ResourceBundle bundle = RootLayout.getBundle();
	
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

    	filter.setPromptText(bundle.getString("filter"));
    	
    	// inicijalizacija stupaca i prikaz
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnNaziv.setText(bundle.getString("tableColumnNaziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,String>("cijena"));
    	tableColumnCijena.setText(bundle.getString("tableColumnCijena"));
    	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,String>("kolicina"));
    	tableColumnKolicina.setText(bundle.getString("tableColumnKolicina"));
    	tableViewArtikli.getSelectionModel().setCellSelectionEnabled(true);
		tableViewArtikli.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	tableViewArtikli.setItems(getArtikli());

        // wrepamo ObservableList u FilteredList
        FilteredList<Artikli> filtriraniArtikli = new FilteredList<>(artikli);

        // Listener za svaki put kada se filter (textfield) promjeni (upise ili obrise neko slovo)
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
        	filtriraniArtikli.setPredicate(artikl -> {
                // Ako je filter prazan, prika�i sve artikle
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                 //Usporedi naziv artikla s nazivom iz filtera
                String lowerCaseFilter = newValue.toLowerCase();
               	if (artikl.getNaziv().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // vrati true ako neki artikl sadrzi tekst iz filtera
                }
                return false; // ako ne sadrzi vrati false
            });
       });

        // wrepaj FilteredList u SortedList
        SortedList<Artikli> sortiraniArtikli = new SortedList<>(filtriraniArtikli);

        // Pove�i SortedList comparator sa TableView comparatorom
        sortiraniArtikli.comparatorProperty().bind(tableViewArtikli.comparatorProperty());

        // Stavi samo sortirane (i filtirane) artikle u tablicu
        tableViewArtikli.setItems(sortiraniArtikli);
        
        btn_unesi_artikl.setText(bundle.getString("btn_unesi_artikl"));
        btn_obrisi.setText(bundle.getString("btn_obrisi"));
        
        txt_naziv.setPromptText(bundle.getString("txt_naziv"));
        txt_kolicina.setPromptText(bundle.getString("txt_kolicina"));
        txt_cijena.setPromptText(bundle.getString("txt_cijena"));

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
            		txt_naziv.clear();
            		txt_kolicina.clear();
            		txt_cijena.clear();
            		osvjezi();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Uspje�no dodan artikl");
            		alert.setHeaderText(null);
            		alert.setContentText("Uspje�no ste dodali artikl.");

            		alert.showAndWait();
            		
            		//Refresh Main screna
            	    //primaryStage.close();
            	    //initRootLayout();
	                //initSredisnjiLayout();

            		//RADI al sam preumoran da razumijem kolko sam genijalan i zasto radi
            		refreshMainScreen();
            		
            	}
    		}
        	
    	}
    	
    	@FXML
    	private void obrisi()
    	{
    		ObservableList<Artikli> artikli1 = FXCollections.observableArrayList();
    		artikli1.add(tableViewArtikli.getSelectionModel().getSelectedItem());
    		
    		//brisanje iz baze
    		for(Artikli artikl : artikli1) {
    		    bazaBlagajna.obrisi_artikl(artikl.getId());
    		}
    		
    		//brisanje iz liste koja se u tom trenutku prikazuje na tablici
    		artikli1.forEach(artikli::remove);
    		osvjezi();
    		
    		//Refresh i tu dodan Main Screena
    		refreshMainScreen();
    	}
    	
    	@FXML
    	private void osvjezi()
    	{
    		artikli.removeAll(artikli);
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
		List<Artikli> artikliIzBaze = new ArrayList<Artikli>();
		artikliIzBaze = bazaBlagajna.bazaCitajArtikle();
				
		for (int i =0;i < artikliIzBaze.size();i++){
			artikli.add(artikliIzBaze.get(i));
		}

		return artikli;
	}
}
