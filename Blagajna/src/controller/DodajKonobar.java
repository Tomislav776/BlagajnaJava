package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import dataClass.Konobar;

/**
 * Razred <code>DodajKonobar</code> inicijalizira novi prozor za dodavanje konobara te postavlja sve vizualne elemente. Daje nam moguènosti za
 * brisanje i dodavanje konobara u bazu podataka.
 * 
 */
public class DodajKonobar extends Main{
	
	@FXML
	private TextField filterKonobar;
	
	@FXML
	private TextField txt_nazivKonobar;
	
	@FXML
	private Button btnDodajKonobar;
	
	@FXML
	private Button btnObrisikonobar;
	
	@FXML
	private TableView<Konobar> tableViewKonobari;
	
	@FXML
	private TableColumn<Konobar, String> tableColumnNazivKonobar;
	
	private ObservableList<Konobar> konobari = FXCollections.observableArrayList();

	private String naziv;
	
	private Locale locale = RootLayout.getLocale();
	private ResourceBundle bundle = RootLayout.getBundle();

	
	// Reference to the main application.
    private Main main;

    /**
     * Konstruktor.
     * Konstruktor se poziva prije initialize() metode.
     */
    public DodajKonobar() {
    }
    

    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * što se uèita fxml datoteka.
     */
    @FXML
    private void initialize() {

    	// inicijalizacija stupaca i prikaz
    	tableColumnNazivKonobar.setCellValueFactory(new PropertyValueFactory<Konobar,String>("naziv"));
    	tableColumnNazivKonobar.setText(bundle.getString("tableColumnNazivKonobar"));
    	tableViewKonobari.getSelectionModel().setCellSelectionEnabled(true);
    	tableViewKonobari.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	tableViewKonobari.setItems(getKonobari());
    	
    	filterKonobar.setPromptText(bundle.getString("filter"));
    	
        // wrepamo ObservableList u FilteredList
        FilteredList<Konobar> filtriraniKonobari = new FilteredList<>(konobari);
        // Listener za svaki put kada se filter (textfield) promjeni (upise ili obrise neko slovo)
        filterKonobar.textProperty().addListener((observable, oldValue, newValue) -> {
        	filtriraniKonobari.setPredicate(konobar -> {
                // Ako je filter prazan, prikaži sve artikle
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                 //Usporedi naziv artikla s nazivom iz filtera
                String lowerCaseFilter = newValue.toLowerCase();
               	if (konobar.getNaziv().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // vrati true ako neki artikl sadrzi tekst iz filtera
                }
                return false; // ako ne sadrzi vrati false
            });
       });
        // wrepaj FilteredList u SortedList
        SortedList<Konobar> sortiraniKonobari = new SortedList<>(filtriraniKonobari);
        // Poveži SortedList comparator sa TableView comparatorom
        sortiraniKonobari.comparatorProperty().bind(tableViewKonobari.comparatorProperty());
        
        // Stavi samo sortirane (i filtirane) artikle u tablicu
    	tableViewKonobari.setItems(sortiraniKonobari);
    	
    	
    	btnDodajKonobar.setText(bundle.getString("btnDodajKonobar"));
    	btnObrisikonobar.setText(bundle.getString("btnObrisikonobar"));
    	txt_nazivKonobar.setPromptText(bundle.getString("txt_nazivKonobar"));
    	filterKonobar.setPromptText(bundle.getString("filterKonobar"));

}
    
    /**
	 * Ova metoda se poziva kada se klikne na gumb za dodati konobara. Ukoliko je prozor za unošenje imena konobara prazan
	 * iskaèe alert, inaæe se spaja na bazu i u nju dodaje novog konobara.
	 */
    	@FXML
    	private void handleClickDodaj()
    	{
    		if(txt_nazivKonobar.getText().isEmpty())
    		{
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Pripazite");
        		alert.setHeaderText(null);
        		alert.setContentText("Niste unijeli ime konobara.");

        		alert.showAndWait();
    		}
    		else{
    			naziv = txt_nazivKonobar.getText();

            	bazaBlagajna b = new bazaBlagajna();
            	if(b.dodaj_konobar(naziv) == true)
            	{
            		txt_nazivKonobar.clear();

            		osvjezi();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Uspješno dodan konobar");
            		alert.setHeaderText(null);
            		alert.setContentText("Uspješno ste dodali konobara.");

            		alert.showAndWait();
            		
            		//Refresh Main Screena
            		refreshMainScreen();
            	}
    		}
        	
    	}
    	
    	/**
    	 * Klikom na gumb obriši briše se oznaèeni konobar iz baze.
    	 */
    	@FXML
    	private void obrisi()
    	{
    		if(txt_nazivKonobar.getText().isEmpty())
    		{
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Pripazite");
        		alert.setHeaderText(null);
        		alert.setContentText("Niste oznaèili konobara za brisanje.");

        		alert.showAndWait();
    		}
    		else{
    		ObservableList<Konobar> konobarii = FXCollections.observableArrayList();
    		konobarii.add(tableViewKonobari.getSelectionModel().getSelectedItem());
    		
    		//brisanje iz baze
    		    bazaBlagajna.obrisi_konobar(tableViewKonobari.getSelectionModel().getSelectedItem().getId());
    		
    		
    		//brisanje iz liste koja se u tom trenutku prikazuje na tablici
    		konobarii.forEach(konobari::remove);
    		osvjezi();
    		
    		refreshMainScreen();
    		}
    	}
    	
    	/**
    	 * Osvježava prikaz konobara u tablici.
    	 */
    	@FXML
    	private void osvjezi()
    	{
    		konobari.removeAll(konobari);
    		tableColumnNazivKonobar.setCellValueFactory(new PropertyValueFactory<Konobar,String>("naziv"));

        	
        	tableViewKonobari.setItems(getKonobari());
    	}

    	/**
         * Poziva se iz Maina da bi se referencirao na samog sebe.
         * @param main
         */
    public void setMainApp(Main main) {
        this.main = main;

    }
    
    /**
	 * Ovom metodom se spajamo na bazu podataka i išèitavamo konobare u listu.
	 * @return Vraæa ObservableList konobara koji se nalaze u bazi.
	 */
    private ObservableList<Konobar> getKonobari(){
		List<Konobar> konobariIzBaze = new ArrayList<Konobar>();
		konobariIzBaze = bazaBlagajna.bazaCitajKonobar();
				
		for (int i =0;i < konobariIzBaze.size();i++){
			konobari.add(konobariIzBaze.get(i));
		}

		return konobari;
	}

}