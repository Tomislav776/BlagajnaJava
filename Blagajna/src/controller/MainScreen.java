package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataClass.Artikli;
import dataClass.Konobar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainScreen{
	
	private  ObservableList<Artikli> artikli = FXCollections.observableArrayList();
	
	private List<Artikli> artikliBaza = new ArrayList<Artikli>(bazaBlagajna.bazaCitajArtikle());
	
	private String valuta=" kn";
	
	/**
	 * NAZIV lokala napraviti
	 */
	private String nazivLokala="Lokal";
	
	@FXML
	public ChoiceBox<String> choiceBoxKonobar;
	
	@FXML
	public SplitPane splitPaneHorizontalni;
	
	@FXML
	public SplitPane splitPaneVertikalan;
	
	@FXML
	public Button btnObrisi;
	
	@FXML
	public Button btnObrisiSve;
	
	@FXML
	public Button btnNaplati;
	
	@FXML
	public TextField txt_field_Ukupno;
	
	@FXML
    public GridPane grid_GumboviArtikl;
	
	@FXML
    public TableView<Artikli> tableViewRacun;
	
	@FXML
    public TableColumn<Artikli, Double> tableColumnCijena;
	
	@FXML
    public TableColumn<Artikli, String> tableColumnNaziv;

    private Button[] btns = new Button[50];

   private Main main; 
   public void setMainApp(Main main) {
        this.main = main;

    }
    
    //popunjava Grid s gumbovima
    @FXML
    public void initialize() {
    	initBtnsArray();
    	initChoiceBox();
    	
    	//inicijalizira tablicu stupce, naziv je ime podatka u klasi Artikli
    	tableViewRacun.setPlaceholder(new Label("Unesite artikle za naplatu"));
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("cijena"));
    	tableViewRacun.setItems(getArtikli(""));
    	
    	initGumboviUGridu(); 
        	
	        
	       //Brise sve artikle
	       btnObrisiSve.setOnAction (e -> {
	    	  artikli.removeAll(artikli);
	    	  txt_field_Ukupno.setText(ukupno());
    		});
	       
	       
	       //Brise odabrani artikl u ispisu
	       btnObrisi.setOnAction (e -> {
	    	   Artikli podatakNaIspisuRacuna = tableViewRacun.getSelectionModel().getSelectedItem();
	    	   artikli.remove(podatakNaIspisuRacuna);  
	    	   
	    	   txt_field_Ukupno.setText(ukupno()+valuta);
	    		});
	       
	       
	       btnNaplati.setOnAction (e -> {
	    	   	gumbNaplatiKlik(e);
	    		});
	       

	       //Da fixira split pane i ne pokazuje ga
	       //splitPaneVertikalan.setDividerPositions(0.32);
	       //splitPaneVertikalan.lookupAll(".split-pane-divider").stream().forEach(div ->  div.setMouseTransparent(true));
        }
    
    
    //Izvodi se pritiskom gumba napalti dodaje u bazu racune
    /**
     * Mozda bi trebo printat racun
     * 
     */
    public boolean gumbNaplatiKlik(ActionEvent sender){
    Alert alert = new Alert(AlertType.INFORMATION);
    
    	if(choiceBoxKonobar.getValue()==null){
    		alert.setTitle("Racun nije naplacen");
    		alert.setHeaderText(null);
    		alert.setContentText("Racun nije naplacen."+'\n'+"Odaberite konobara i pokušajte ponovno.");
    		alert.showAndWait();
    		return false;
    	}
    	
    	bazaBlagajna b = new bazaBlagajna();
    	if(b.dodaj_Racun(Double.parseDouble(ukupno()), nazivLokala, choiceBoxKonobar.getValue()) == true)
    	{
    		artikli.removeAll(artikli);
    		tableViewRacun.setPlaceholder(new Label("Unesite artikle za naplatu"));
    		txt_field_Ukupno.setText(ukupno()+valuta);
    		
    		alert.setTitle("Racun");
    		alert.setHeaderText(null);
    		alert.setContentText("Racun je naplacen");
    		alert.showAndWait();
    	}
    	return true;
    }
    
 	//Inicjalizira gumbove
    public void initBtnsArray() {
		
        for(int i = 0; i < artikliBaza.size(); i++) {
            btns[i] = new Button(artikliBaza.get(i).getNaziv()); 
        }
    }
    
    //Funkcija koja se izvodi kad se klikne na neki od gumbova s artiklima
	public void gumbArtikliKlik(ActionEvent sender){
		
		Button btn=(Button) sender.getSource();
		String naziv=btn.getText();
		tableViewRacun.setItems(getArtikli(naziv));
		
		txt_field_Ukupno.setText(ukupno()+valuta); //Postavlja cijenu
		}
	
	
	//Vraæa zbroj artikala
	public String ukupno(){
		double ukupno=0;
		
		for (int i =0; i<artikli.size();i++){
			ukupno+=artikli.get(i).getCijena();
		}
		return String.valueOf(ukupno);
	}
	
	
	//Radi observable list stavlja artikle u nju za prikaz u table view
	public ObservableList<Artikli> getArtikli(String naziv){
	int pamti = -1;
	
		for (int i =0 ;i<artikliBaza.size();i++){
			if (naziv.equals(artikliBaza.get(i).getNaziv())){
				if (pamti!=-1){
					Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Više artikala s istim nazivom");
            		alert.setHeaderText(null);
            		alert.setContentText("U vašoj bazi podataka nalazi se više artikala s istim nazivom, "+'\n'+"Kako bi dodali artikl koji vi želite promijenite naziv u bazi jednome od njih.");

            		alert.showAndWait();
            		break;
				}
		
				artikli.add(artikliBaza.get(i));
				pamti = i;
			}
		}
			
		return artikli;
	}
	
	
	//Inicijalizira gumbove u gridu
	public void initGumboviUGridu (){
		int k=0,i=0,j=0;
        while (k!=artikliBaza.size()) {    
        	
        		btns[k].setMinSize(149, 100);	//Poveca gumbove da popune okvir
        		btns[k].setMaxSize(1000, 1000);
        		    
        		grid_GumboviArtikl.add(btns[k],j ,i);
        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
        		grid_GumboviArtikl.setValignment(btns[k], VPos.CENTER);
        		
        		
        		//Postavlja Listenere na gumbove
        		btns[k].setOnAction (e -> {
        			gumbArtikliKlik(e);
        		});
        		
        	++k;
        	++j;
        	if (j%4==0){
        		j=0;
        		++i;
        	}
        }
	}
	
	//incijalizira choice box
	public void initChoiceBox(){
		
		List<Konobar> konobarBaza = new ArrayList<Konobar>(bazaBlagajna.bazaCitajKonobar());
	
		for (int i=0;i<konobarBaza.size();i++)
		choiceBoxKonobar.getItems().add(konobarBaza.get(i).getNaziv());
		
	}
	
}
    
    
    


