package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import dataClass.Artikli;
import dataClass.Konobar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.Node;
import javafx.print.*;

/**
 * Razred <code>MainScreen</code> postavlja i inicijalizira sve vizualne elemente na glavni zaslon.
 * Unutar razreda ostvarene su funkcionalnosti svih vizualnih elemenata.
 * 
 */
public class MainScreen extends Main{
	
	private  ObservableList<Artikli> artikli = FXCollections.observableArrayList();
	
	private List<Artikli> artikliBaza = new ArrayList<Artikli>(bazaBlagajna.bazaCitajArtikle());
	private List<Artikli> bazaArtikli = artikliBaza;
	
	private String valuta="Valuta";
	
	private int brojRedakaGrida=0;
	private int brojStupacaGrida=0;
	
	private static String nazivLokala="Lokal";
	
	@FXML
	private Label labelKonobar;
	
	@FXML
	private Label labelUkupno;
	
	@FXML
	private ChoiceBox<String> choiceBoxKonobar;
	
	@FXML
	private SplitPane splitPaneHorizontalni;
	
	@FXML
	private SplitPane splitPaneVertikalan;
	
	@FXML
	private Button btnObrisi;
	
	@FXML
	private Button btnObrisiSve;
	
	@FXML
	private Button btnNaplati;
	
	@FXML
	private TextField txt_field_Ukupno;
	
	@FXML
	private TextField txtFieldUnesiteArtikl;
	
	@FXML
	private GridPane grid_GumboviArtikl;
	
	@FXML
	private TableView<Artikli> tableViewRacun;
	
	@FXML
	private TableColumn<Artikli, Double> tableColumnCijena;
	
	@FXML
	private TableColumn<Artikli, String> tableColumnNaziv;
	
	@FXML
	private TableColumn<Artikli, Integer> tableColumnKolicina;
	
	@FXML
	private TableColumn<Artikli, Double> tableColumnUkupno;

    private Button[] btns = new Button[50];
    
    private Locale locale = RootLayout.getLocale();
	private ResourceBundle bundle = RootLayout.getBundle();

   private Main main;
   
   /**
    * Poziva se iz Maina da bi se referencirao na samog sebe.
    * @param main
    */
   public void setMainApp(Main main) {
        this.main = main;

    }
    
    //inicijalizira Main Screen
   /**
    * Inicijalizira controller klasu. Ova se metoda automatski poziva nakon
    * što se učita fxml datoteka.
    */
    @FXML
    public void initialize() {
    	ucitajPostavkeKorisnika();
    	
    	artikliBaza = bazaBlagajna.bazaCitajArtikle();
    	
    	
    	initGumboviUGridu(); 
    	initChoiceBox();
    	
    	//inicijalizira broj naplacenih artikala na 1
    	for (int i =0;i<artikliBaza.size();i++){
    	artikliBaza.get(i).setKolicina(1);
    	}
    	
    	//inicijalizira tablicu stupce, naziv je ime podatka u klasi Artikli
    	tableViewRacun.setPlaceholder(new Label(bundle.getString("tableViewRacun")));
    	tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Artikli,String>("naziv"));
    	tableColumnNaziv.setText(bundle.getString("tableColumnNaziv"));
    	tableColumnCijena.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("cijena"));
    	tableColumnCijena.setText(bundle.getString("tableColumnCijena"));
    	tableColumnKolicina.setCellValueFactory(new PropertyValueFactory<Artikli,Integer>("kolicina"));
    	tableColumnKolicina.setText(bundle.getString("tableColumnKolicina"));
    	tableColumnUkupno.setCellValueFactory(new PropertyValueFactory<Artikli,Double>("ukupno"));
    	tableColumnUkupno.setText(bundle.getString("tableColumnUkupno"));

    	
    	//Nezz dal da se povećava ili ne ako da ovo sređuje da svi stupci budu vidljivi, da popune prostor
    	tableViewRacun.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	btnNaplati.setText(bundle.getString("btnNaplati"));
    	btnObrisi.setText(bundle.getString("btnObrisi"));
    	btnObrisiSve.setText(bundle.getString("btnObrisiSve"));
    	
    	labelKonobar.setText(bundle.getString("labelKonobar"));
    	labelUkupno.setText(bundle.getString("labelUkupno"));

    	tableViewRacun.setItems(getArtikli(""));
    	
    	txt_field_Ukupno.setDisable(true);
    	
    	txtFieldUnesiteArtikl.setPromptText(bundle.getString("txtFieldUnesiteArtikl"));
        	
	        
    	//Brise sve artikle
	       btnObrisiSve.setOnAction (e -> {
	    	   enableButton("Svi");
	    	   artikli.removeAll(artikli);
	    	  txt_field_Ukupno.setText(ukupno());
	    	  
 		});
	       
	       
	       //Brise odabrani artikl u ispisu
	       btnObrisi.setOnAction (e -> {
	    	   Artikli podatakNaIspisuRacuna = tableViewRacun.getSelectionModel().getSelectedItem();
	    	   enableButton(tableViewRacun.getSelectionModel().getSelectedItem().getNaziv());
	    	   
	    	   artikli.remove(podatakNaIspisuRacuna);  
	    	   
	    	   txt_field_Ukupno.setText(ukupno()+valuta);
	    		});
	       
	       
	       btnNaplati.setOnAction (e -> {
	    	   	gumbNaplatiKlik(e);
	    		});
	       
	       //Da se artikli mogu unositi preko text fielda
	       txtFieldUnesiteArtikl.setOnKeyPressed(new EventHandler<KeyEvent>()
           	    {
           	        @Override
           	        public void handle(KeyEvent ke)
           	        {
           	            if (ke.getCode().equals(KeyCode.ENTER))
           	            {
           	            	String naziv=txtFieldUnesiteArtikl.getText();
           	            	//boolean daliPostojiArtikl=false;
           	            	
           	            for (int i =0;i<artikliBaza.size();i++)
           	            	if (naziv.equals(artikliBaza.get(i).getNaziv())){
		           	     		tableViewRacun.setItems(getArtikli(naziv));
		           	     		
		           	     		txt_field_Ukupno.setText(ukupno()+valuta);
		           	     		txtFieldUnesiteArtikl.clear();
		           	     		break;
           	            	}
           	            	
           	        } }
           	    });
	      
	       //Nemogu ovako dobit focus...
	       //txtFieldUnesiteArtikl.requestFocus();
	       
	       
        }
    
    
    //Izvodi se pritiskom gumba napalti dodaje u bazu racune
    /**
     * Metoda se izvršava kada korisnik pritisne gumb Naplati.
     * Provjerava da li je odabran konobar ako nije upozorava korisnika. Poziva metodu <code>promijeniKolicinu</code> te smanjuje količinu proizvoda za sve naplaćene artikle.
     * Mijenja vrijednost ukupnog iznosa, te šalje račun na printanje. Nakon što obavi sve na kraju briše artikle koji su prikazani unutar tablice za naplatu.
     * @param sender ActionEvent od gumba za naplatu.
     * @return vrača <code>true</code> ako se sve uspješno izvršilo, inače <code>false</code>.
     */
    public boolean gumbNaplatiKlik(ActionEvent sender){
    Alert alert = new Alert(AlertType.INFORMATION);
    
    	if(choiceBoxKonobar.getValue()==null){
    		alert.setTitle("Račun nije naplačen");
    		alert.setHeaderText(null);
    		alert.setContentText("Račun nije naplacen."+'\n'+"Odaberite konobara i pokušajte ponovno.");
    		alert.showAndWait();
    		return false;
    	}
    	
    	bazaBlagajna b = new bazaBlagajna();
    	
    	//Mjenjanje kolicine artikala u bazi
    	promijeniKolicinu(sender);
    	
    	if(b.dodaj_Racun(Double.parseDouble(ukupno()), nazivLokala, choiceBoxKonobar.getValue()) == true)
    	{
    		
    		
    		txt_field_Ukupno.setText(ukupno()+valuta);
    		
    		alert.setTitle("Racun");
    		alert.setHeaderText(null);
    		alert.setContentText("Račun je naplačen.");
    		alert.showAndWait();
    		if(doPrint(tableViewRacun))
    		{
    			alert.setTitle("Račun je isprintan!");
        		alert.setHeaderText(null);
        		alert.setContentText("Račun je isprintan!");
        		alert.showAndWait();
    		}
    		artikli.removeAll(artikli);
    		tableViewRacun.setPlaceholder(new Label(bundle.getString("tableViewRacun")));
    	}
    	return true;
    }
    
 	//Inicjalizira gumbove
    /**
     * Inicijalizira gumbove artikala koji su prikazani na glavnom zaslonu. Vrijednosti gumbova i koliko ih treba uzima iz baze podataka.
     * Zbog optimizacije radi sa  List<Artikli>bazaArtikli koja je static i unutar nje se nalaze svi artikli iz baze podataka.
     */
    public void initBtnsArray() {
		
        for(int i = 0; i < bazaArtikli.size(); i++) {
            btns[i] = new Button(bazaArtikli.get(i).getNaziv()); 
        }
    }
    
    /**
     * Printa račun kada korisnik pritisne gumb naplati.
     * @param n Layout koji želimo isprintati
     * @return vrača <code>true</code> ako se sve uspješno izvršilo, inače <code>false</code>
     */
    private boolean doPrint(Node n)
    {
    	Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
    	PrinterJob job = PrinterJob.createPrinterJob();
    	if(job == null) return false;
    	if(!job.printPage(n)) return false;
    	return job.endJob();
    }
    
    //Funkcija koja se izvodi kad se klikne na neki od gumbova s artiklima
    /**
     * Metoda se izvršava kada korisnik pritisne bilo koji gumb koji predstavlja artikl na glavnom zaslonu.
     * Postavlja pomoću metode <code>getArtikli</code> unutar tablice za naplatu željeni artikl te mijenja prikaz ukupne svote računa.
     * Mijenja količinu artikala.
     * @param sender ActionEvent od gumbova koji predstavljaju artikle.
     */
	public void gumbArtikliKlik(ActionEvent sender){
		Button btn=(Button) sender.getSource();
		String naziv=btn.getText();
		tableViewRacun.setItems(getArtikli(naziv));
		
		//Mjenja kolicinu
		promijeniKolicinu(sender);
		
		txt_field_Ukupno.setText(ukupno()+valuta); //Postavlja cijenu
		}
	
	
	//Vraæa zbroj artikala
	/**
	 * Metoda računa ukupan iznos svih artikala koji se nalaze unutar tablice za naplatu.
	 * @return Vrača String ukupan iznos za naplatu.
	 */
	public String ukupno(){
		double ukupno=0;
		
		for (int i =0; i<artikli.size();i++){
			ukupno+=artikli.get(i).getUkupno();
		}
		return String.valueOf(ukupno);
	}
	
	//mjenja kolicinu u bazi i deaktivira gumb
	/**
	 * Metoda mijenja količinu artikala u bazi samo ako je pozvana pritiskom gumba Naplati.
	 * Ako je pozvana pritiskom artikl gumbova privremeno mijenja količinu artikala te ako je neki artikl odabran onoliko puta kolika je njegova količina deaktivira taj gumb.
	 * Kada je gumb deaktiviran i funkcija se pozove s gumbom naplati taj artikl se briše iz baze podataka i njegov gumb se miče iz prikaza gumbova.
	 * @param sender ActionEvent od gumbova kojim je pozvana metoda.
	 */
	public void promijeniKolicinu (ActionEvent sender){
		int baza=0;
		Button btn=(Button) sender.getSource();
		String naziv;
		naziv=btn.getText();
				
		bazaBlagajna b = new bazaBlagajna();
		
		
		for (int j=0;j<bazaArtikli.size();j++){
			if (bazaArtikli.get(j).getNaziv().equals(naziv)){
				baza=j;		
				break;
			}
		}
		
		if(!(btn.getText().equals("Naplati")))
		{
			for (int i =0;i<artikli.size();i++){
				
				if (artikli.get(i).getNaziv().equals(naziv) && bazaArtikli.get(baza).getKolicina()<=artikli.get(i).getKolicina()){
					btn.setDisable(true);
					break;
				}
			}
		}
		else
		{
			for (int i=0;i<artikli.size();i++){
			b.smanjiKolicinuArtikla(artikli.get(i).getId(), artikli.get(i).getKolicina());
				
				for (int j=0;j<bazaArtikli.size();j++){
					if (bazaArtikli.get(j).getNaziv().equals(artikli.get(i).getNaziv()) && bazaArtikli.get(j).getKolicina()<=artikli.get(i).getKolicina()){
						bazaBlagajna.obrisi_artikl(artikli.get(i).getId());
						bazaArtikli.remove(j);
						artikliBaza.remove(j);
						refreshGrid();
						break;
					}		
				}
			}
		}	
	}
	
	/**
	 * Metoda aktivira jedan ili sve gumbove artikala koji su deaktivirani.
	 * Poziva se prilikom pritiska na gumb obriši ili obriši sve koji miču sve artikle iz tablice za naplatu pa je data potrebno ponovno aktivirati gumbove koji su u procesu postali neaktivni.
	 * @param naziv Prima ime gumba kojeg treba aktivirati ili String "Svi" ako primi Svi aktivirat će sve deaktivirane gumbove.
	 */
	public void enableButton (String naziv){
		
		for (int i=0;i<bazaArtikli.size();i++){
			if (naziv.equals(btns[i].getText()) || naziv.equals("Svi")){
				btns[i].setDisable(false);
			}
		}
	}
	
	//Radi observable list stavlja artikle u nju za prikaz u table view
	/**
	 * Pri dodavanju artikala unutar tablice za naplatu metoda sprema te artikle u Listu te ako ima istoimenih artikala povećava im količinu. Postavlja vrijednosti svih stupaca 
	 * unutar tablice za naplatu.
	 * @param naziv Ime artikla kojeg želimo dodati za naplatu.
	 * @return Vrača kao ObservableList sve artikle koji se nalaze u tablici za naplatu.
	 */
	public ObservableList<Artikli> getArtikli(String naziv){
	int pamti = -1;
	boolean kolProvjera=true;
	double ukupno=0;
	
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
				
				
				//Povecava kolicinu i novu cijenu
				for (int j = 0; j < artikli.size(); j++){
					
					if (naziv.equals(artikli.get(j).getNaziv())){
						artikli.get(j).setKolicina(artikli.get(j).getKolicina()+1);
						
						artikli.get(j).setUkupno(artikli.get(j).getKolicina()*artikliBaza.get(i).getCijena());
	
						osvjezi();
						kolProvjera=false;
						break;
					}
				}
				
				if (kolProvjera){
					ukupno=artikliBaza.get(i).getCijena();
					artikli.add(new Artikli(artikliBaza.get(i).getId(), artikliBaza.get(i).getNaziv(), artikliBaza.get(i).getKolicina(), artikliBaza.get(i).getCijena(), ukupno));
				}
				
				pamti = i;
			}
		}
		
		return artikli;
	}
	
	//refresha table view prikaz za račun
	/**
	 * Osvježava Tablicu za napaltu.
	 */
	public void osvjezi() { 
		tableViewRacun.setItems(null); 
		tableViewRacun.layout(); 
		tableViewRacun.setItems(FXCollections.observableList(artikli)); 
}
	
	
	//Inicijalizira gumbove u gridu
	/**
	 * Inicijalizira i postavlja gumbove artikala unutar grida.
	 */
	public void initGumboviUGridu (){
		initBtnsArray();
		
		List<ColumnConstraints> stupci = new ArrayList<ColumnConstraints>();
		List<RowConstraints> redovi = new ArrayList<RowConstraints>();
		int k=0,i=0,j=0;
		
		//grid_GumboviArtikl = new GridPane();
		
		grid_GumboviArtikl.getChildren().removeAll(grid_GumboviArtikl.getChildren());
		grid_GumboviArtikl.getChildren().clear();
		
		grid_GumboviArtikl.setPrefSize(669, 725);
	     // never size the gridpane larger than its preferred size:
		grid_GumboviArtikl.setMaxSize(10000000, 1000000);
	     
		for (int z=0;z<brojRedakaGrida;z++){
			redovi.add(new RowConstraints());
			redovi.get(z).setPercentHeight(50);
		}
		grid_GumboviArtikl.getRowConstraints().addAll(redovi);
		
		for (int z=0;z<brojStupacaGrida;z++){
			stupci.add(new ColumnConstraints());
			stupci.get(z).setPercentWidth(50);
		}
		grid_GumboviArtikl.getColumnConstraints().addAll(stupci);
		
        while (k!=bazaArtikli.size()) {    
        	
        		//btns[k].setMinSize(300, 100);	//Poveca gumbove da popune okvir
        		//btns[k].setMaxSize(1000, 1000);
        		
        	//A nezznam trebalo bi bit responzivnije al kaj ja znam jel je
        		AnchorPane pane = new AnchorPane();
        		
        		grid_GumboviArtikl.add(pane,j ,i);
        		
        		pane.getChildren().add(btns[k]);
        		pane.setBottomAnchor(btns[k], 0.0);
        		pane.setTopAnchor(btns[k], 0.0);
        		pane.setLeftAnchor(btns[k], 0.0);
        		pane.setRightAnchor(btns[k], 0.0);
        		
        		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
        		grid_GumboviArtikl.setValignment(btns[k], VPos.CENTER);
        		
        		
        		
        		//Postavlja Listenere na gumbove
        		btns[k].setOnAction (e -> {
        			gumbArtikliKlik(e);
        		});
        		
        	++k;
        	++j;
        	if (j%brojStupacaGrida==0){
        		j=0;
        		++i;
        	}
        }
    	
        
	}
	
	/**
	 * Osvježava Grid sa gumbovima artikala.
	 */
	public void refreshGrid(){
		int i=0;
		int k=0;
		int j=0;
		
		initBtnsArray();
		
		grid_GumboviArtikl.getChildren().removeAll(grid_GumboviArtikl.getChildren());
		
        while (k!=bazaArtikli.size()) {    
        	
    		
    		
    	//A nezznam trebalo bi bit responzivnije al kaj ja znam jel je
    		AnchorPane pane = new AnchorPane();
    		
    		grid_GumboviArtikl.add(pane,j ,i);
    		
    		pane.getChildren().add(btns[k]);
    		pane.setBottomAnchor(btns[k], 0.0);
    		pane.setTopAnchor(btns[k], 0.0);
    		pane.setLeftAnchor(btns[k], 0.0);
    		pane.setRightAnchor(btns[k], 0.0);
    		
    		grid_GumboviArtikl.setHalignment(btns[k], HPos.CENTER);  //Centrira gumbove
    		grid_GumboviArtikl.setValignment(btns[k], VPos.CENTER);
    		
    		
    		
    		//Postavlja Listenere na gumbove
    		btns[k].setOnAction (e -> {
    			gumbArtikliKlik(e);
    		});
    		
    	++k;
    	++j;
    	if (j%brojStupacaGrida==0){
    		j=0;
    		++i;
    	}
    }
		
		
	}
	//incijalizira choice box
	/**
	 * Inicijalizira prozor za odabir konobara te postavlja za moguće opcije sve konobare koji se nalaze u bazi podataka.
	 */
	public void initChoiceBox(){
		
		List<Konobar> konobarBaza = new ArrayList<Konobar>(bazaBlagajna.bazaCitajKonobar());
	
		for (int i=0;i<konobarBaza.size();i++)
		choiceBoxKonobar.getItems().add(konobarBaza.get(i).getNaziv()+" "+konobarBaza.get(i).getPrezime());
		
	}
	
	//inicijalizira varijable po zelji korisnika
	/**
	 * Čita iz datoteke Postavke.txt postavke korisnika te ih pridružuje varijablama koje se koriste unutar ovog razreda.
	 */
	public void ucitajPostavkeKorisnika(){ 
		List<String> postavke = new ArrayList<String>();
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
	    		nazivLokala=postavke.get(i+1);
	    	
	    	if (postavke.get(i).equals("Valuta:"))
	    		valuta=postavke.get(i+1);
	    	
	    	if (postavke.get(i).equals("Broj redaka:"))
	    		brojRedakaGrida=Integer.parseInt(postavke.get(i+1));
	    	
	    	if (postavke.get(i).equals("Broj stupaca:"))
	    		brojStupacaGrida=Integer.parseInt(postavke.get(i+1));
	    }
	}
	
}