package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import dataClass.Artikli;

public class Promijeni_artikl {
	
	@FXML
	private TextField txt_naziv;
	
	@FXML
	private TextField txt_kolicina;
	
	@FXML
	private TextField txt_cijena;
	
	@FXML
	private Button btn_promijeni;
	
	@FXML
	private TableView<Artikli> tableViewArtikli;
	
	@FXML
	private TableColumn<Artikli, Double> tableColumnCijena;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnNaziv;
	
	@FXML
	private TableColumn<Artikli, Integer> tableColumnKolicina;
	
	List<Artikli> artikliIzBaze = new ArrayList<Artikli>(bazaBlagajna.bazaCitajArtikle());
	
	
	private ObservableList<Artikli> artikli2 = FXCollections.observableArrayList();

	private int id;
	private String naziv;
	private int kolicina;
	private Double cijena;
	
	private Locale locale = RootLayout.getLocale();
	private ResourceBundle bundle = RootLayout.getBundle();
	
	// Reference to the main application.
    private Main main;

    /**
     * Konstruktor.
     * Konstruktor se poziva prije initialize() metode.
     */
    public Promijeni_artikl() {
    }
    

    /**
     * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
     * što se uèita fxml datoteka.
     */
    @FXML
    private void initialize() {

    	
    	// inicijalizacija stupaca i prikaz
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnNaziv.setText(bundle.getString("tableColumnNaziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("cijena"));
    	tableColumnCijena.setText(bundle.getString("tableColumnCijena"));
    	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,Integer>("kolicina"));
    	tableColumnKolicina.setText(bundle.getString("tableColumnKolicina"));
    	tableViewArtikli.getSelectionModel().setCellSelectionEnabled(true);
		tableViewArtikli.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	tableViewArtikli.setItems(getArtikli());
    	
    	tableViewArtikli.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	tableViewArtikli.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                	txt_naziv.setText(tableViewArtikli.getSelectionModel().getSelectedItem().getNaziv());
                	txt_kolicina.setText(String.valueOf(tableViewArtikli.getSelectionModel().getSelectedItem().getKolicina()));
                	txt_cijena.setText(String.valueOf(tableViewArtikli.getSelectionModel().getSelectedItem().getCijena()));
                	id = new Integer(tableViewArtikli.getSelectionModel().getSelectedItem().getId());
                });
    	

}
    /**
	 * Ova metoda se poziva kada se klikne na gumb za promijeniti artikl.
	 */
    	@FXML
    	private void handleClickPromijeni()
    	{
    		if(txt_naziv.getText().isEmpty() || txt_kolicina.getText().isEmpty() || txt_cijena.getText().isEmpty())
    		{
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Pripazite");
        		alert.setHeaderText(null);
        		alert.setContentText("Niste oznaèili niti jedan artikl.");

        		alert.showAndWait();
    		}
    		else{
    			naziv = txt_naziv.getText();
            	kolicina = Integer.parseInt(txt_kolicina.getText());
            	cijena = Double.parseDouble(txt_cijena.getText());
            	bazaBlagajna b = new bazaBlagajna();
            	b.promijeni_artikl(id, naziv, kolicina, cijena);
            	
            		txt_naziv.clear();
            		txt_kolicina.clear();
            		txt_cijena.clear();
            		Osvjezi();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Uspješno promijenjen artikl");
            		alert.setHeaderText(null);
            		alert.setContentText("Uspješno ste promijenili artikl.");

            		alert.showAndWait();
            		
            		//refreshMainScreen();
            		
            	
    		}
        	
    	}
    	
    	
    	
    	/**
    	 * Osvježava prikaz artikla u tablici.
    	 */
    	@FXML
    	private void Osvjezi()
    	{
    		int b=id;
    		for (int i=0;i<artikli2.size();i++)
    		if (artikli2.get(i).getId()==b){
    			artikli2.remove(i); //ova linija iz nekog jebeno nepoznatog razloga bezveze baca exception
    			break;
    		}
    		tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
        	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("cijena"));
        	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,Integer>("kolicina"));
        	
        	artikliIzBaze=bazaBlagajna.bazaCitajArtikle();
        	for (int i =0;i < artikliIzBaze.size();i++){
        		if (artikliIzBaze.get(i).getId()==b){
    			artikli2.add(artikliIzBaze.get(i));
        		break;
        		}
    		}

    	}

    /**
     * Poziva se iz Maina da bi se referencirao na samog sebe.
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;

    }
    
    /**
	 * Ovom metodom se spajamo na bazu podataka i išèitavamo artikle u listu.
	 * @return Vraæa ObservableList artikla koji se nalaze u bazi.
	 */
    private ObservableList<Artikli> getArtikli(){
		
				
		for (int i =0;i < artikliIzBaze.size();i++){
			artikli2.add(artikliIzBaze.get(i));
		}

		return artikli2;
	}
}
